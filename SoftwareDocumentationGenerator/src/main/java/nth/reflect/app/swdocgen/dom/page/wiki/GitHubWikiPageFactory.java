package nth.reflect.app.swdocgen.dom.page.wiki;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.app.swdocgen.dom.documentation.GitHubWikiInfo;
import nth.reflect.app.swdocgen.dom.github.GitRepository;
import nth.reflect.app.swdocgen.dom.html.AttributeName;
import nth.reflect.app.swdocgen.dom.html.ElementName;
import nth.reflect.app.swdocgen.dom.html.JSoupQuery;
import nth.reflect.app.swdocgen.dom.javadoc.JavaDocFactory;
import nth.reflect.app.swdocgen.dom.javafile.DocumentationFiles;
import nth.reflect.app.swdocgen.dom.javafile.ReferenceName;
import nth.reflect.app.swdocgen.dom.page.FileUtil;

public class GitHubWikiPageFactory {

	private final GitRepository gitRepository;

	public GitHubWikiPageFactory(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}

	public void createGitHubWikiPages(GitHubWikiInfo info) throws IOException {

		DocumentationFiles documentationFiles = new DocumentationFiles(info.getProjectsFolder());

		Document javaDoc = JavaDocFactory.getAllJavaDoc(info, documentationFiles);

		JavaDocFactory.updateAllReferences(javaDoc, new WikiPageReferenceFactory(javaDoc));

		List<WikiPage> webPages = createWebPages(info, javaDoc);

		gitRepository.deleteFolderContents(info.getGitHubWikiProjectLocation());

		writeWebPages(webPages);

		copyResources(info.getGitHubWikiProjectLocation(), documentationFiles, webPages);

		gitRepository.commitAndPush(info, info.getGitHubWikiProjectLocation());
	}

	private void copyResources(File destinationFolder, DocumentationFiles documentationFiles,
			List<WikiPage> wikiPages) {
		for (WikiPage wikiPage : wikiPages) {
			Document document = wikiPage.getContents();
			String query = new JSoupQuery().addElement(ElementName.IMG, AttributeName.SRC).toString();
			Elements imgElements = document.select(query);

			for (Element imgElement : imgElements) {
				String resourceReferenceName = imgElement.attr(AttributeName.SRC);
				ReferenceName referenceName = new ReferenceName(resourceReferenceName);
				Optional<File> result = documentationFiles.findResourceFile(referenceName);
				if (result.isPresent()) {
					File resourceFile = result.get();
					File target = new File(destinationFolder, referenceName.withoutPreFix());
					try {
						FileUtil.copyFolder(resourceFile, target);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void writeWebPages(List<WikiPage> wikiPages) throws IOException {
		for (WikiPage wikiPage : wikiPages) {
			wikiPage.write();
		}
	}

	private List<WikiPage> createWebPages(GitHubWikiInfo info, Document javaDoc) {
		List<WikiPage> webPages = new ArrayList<>();
		webPages.add(new WikiHomePage(info, javaDoc.clone()));
		Elements h1Elements = javaDoc.select(ElementName.H1);
		for (Element h1 : h1Elements) {
			webPages.add(new WikiContentsPage(info, javaDoc.clone(), h1));
		}

		return webPages;
	}
}

package nth.introspect.apps.docgenforjavaproj.dom.page.wiki;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nth.introspect.apps.docgenforjavaproj.dom.documentation.GitHubWikiInfo;
import nth.introspect.apps.docgenforjavaproj.dom.github.GitRepository;
import nth.introspect.apps.docgenforjavaproj.dom.javadoc.JavaDocFactory;
import nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag.HtmlLinkToReference;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFile;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFileFactory;
import nth.introspect.apps.docgenforjavaproj.dom.page.FileUtil;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GitHubWikiPageFactory {

	private final GitRepository gitRepository;

	public GitHubWikiPageFactory(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}

	public void createGitHubWikiPages(GitHubWikiInfo info) throws IOException {

		Map<String, JavaFile> javaFiles = JavaFileFactory
				.findAllJavaFilesInFolder(info.getProjectsFolder());

		Document javaDoc = JavaDocFactory.getAllJavaDoc(info, javaFiles);

		List<WikiPage> webPages = createWebPages(info, javaDoc);

		gitRepository.deleteFolderContents(info.getGitHubWikiProjectLocation());

		writeWebPages(webPages);

		copyResources(info.getGitHubWikiProjectLocation(), javaFiles, webPages);

		gitRepository.commitAndPush(info, info.getGitHubWikiProjectLocation());
	}


	private void copyResources(File destinationFolder,
			Map<String, JavaFile> javaFiles, List<WikiPage> wikiPages) {
		for (WikiPage wikiPage : wikiPages) {
			Document document = wikiPage.getContents();
			Elements elements = document.select("img[src],a[id^=Reference_]");

			String javaFileName = null;
			for (Element element : elements) {
				String elementName = element.nodeName();
				if (elementName.equals("a")
						&& element.id().startsWith(
								HtmlLinkToReference.REFERENCE)) {
					javaFileName = element.id().replace(
							HtmlLinkToReference.REFERENCE, "");
				} else if (elementName.equals("img")) {
					String src = element.attr("src");
					JavaFile javaFile = javaFiles.get(javaFileName);
					File source = javaFile.getResourcePath(src);
					File target = new File(destinationFolder, source.getName());
					try {
						FileUtil.copyFolder(source, target);
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
		Elements h1Elements = javaDoc.select("h1");
		for (Element h1 : h1Elements) {
			webPages.add(new WikiContentsPage(info, javaDoc.clone(), h1));
		}
		
		return webPages;
	}
}

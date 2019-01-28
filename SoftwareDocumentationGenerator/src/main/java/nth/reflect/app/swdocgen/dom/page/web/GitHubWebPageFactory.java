package nth.reflect.app.swdocgen.dom.page.web;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.app.swdocgen.dom.documentation.GitHubWebInfo;
import nth.reflect.app.swdocgen.dom.github.GitRepository;
import nth.reflect.app.swdocgen.dom.html.AttributeName;
import nth.reflect.app.swdocgen.dom.html.ElementName;
import nth.reflect.app.swdocgen.dom.html.JSoupQuery;
import nth.reflect.app.swdocgen.dom.javadoc.JavaDocFactory;
import nth.reflect.app.swdocgen.dom.javafile.DocumentationFiles;
import nth.reflect.app.swdocgen.dom.javafile.ReferenceName;
import nth.reflect.app.swdocgen.dom.page.FileUtil;
import nth.reflect.app.swdocgen.dom.page.WritableFile;

public class GitHubWebPageFactory {

	private final GitRepository gitRepository;

	public GitHubWebPageFactory(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}

	public void createGitHubWebPages(GitHubWebInfo info) throws IOException {

		DocumentationFiles documentationFiles = new DocumentationFiles(info.getProjectsFolder());

		Document javaDoc = JavaDocFactory.getAllJavaDoc(info, documentationFiles);

		JavaDocFactory.updateAllReferences(javaDoc, new WebPageRefenceFactory());

		List<WritableFile> webPages = createWebPages(info, javaDoc);

		gitRepository.deleteFolderContents(info.getGithubWebProjectLocation());

		writeWebPages(webPages);

		copyResources(info.getGithubWebProjectLocation(), documentationFiles, webPages);

		copyMenuFiles(info.getGithubWebProjectLocation());

		gitRepository.commitAndPush(info, info.getGithubWebProjectLocation());
	}

	private void copyMenuFiles(File destinationFolder) {
		try {
			URL sourceUrl = getClass().getResource("/mmenu");
			File sourceFolder = new File(sourceUrl.toURI());
			FileUtil.copyFolder(sourceFolder, destinationFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void copyResources(File destinationFolder, DocumentationFiles documentationFiles,
			List<WritableFile> webPages) {
		for (WritableFile page : webPages) {

			if (page instanceof WebPage) {
				WebPage webPage = (WebPage) page;
				Document document = webPage.getContents();
				String query = new JSoupQuery().addElement(ElementName.IMG, AttributeName.SRC).toString();
				Elements imgElements = document.select(query);

				for (Element imgElement : imgElements) {
					String elementName = imgElement.nodeName();
					if (elementName.equals(ElementName.IMG)) {
						String src = imgElement.attr(AttributeName.SRC);
						ReferenceName referenceName = new ReferenceName(src);
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
		}
	}

	private void writeWebPages(List<WritableFile> webPages) throws IOException {
		for (WritableFile webPage : webPages) {
			webPage.write();
		}
	}

	private List<WritableFile> createWebPages(GitHubWebInfo info, Document javaDoc) {

		FancyWebPage fancyWebPage = new FancyWebPage(info, javaDoc.clone());

		List<WritableFile> webPages = new ArrayList<>();
		webPages.add(fancyWebPage);
		webPages.add(new IndexWebPage(info, javaDoc.clone(), fancyWebPage.getFile()));
		webPages.add(new RobotsTxt(info.getGithubWebProjectLocation()));
		webPages.add(new SiteMap(info, fancyWebPage.getFile()));
		webPages.add(new PrintableWebPage(info, javaDoc.clone()));
		return webPages;
	}
}

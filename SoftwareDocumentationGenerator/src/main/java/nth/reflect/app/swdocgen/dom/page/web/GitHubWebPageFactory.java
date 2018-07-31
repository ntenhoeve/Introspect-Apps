package nth.reflect.app.swdocgen.dom.page.web;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.app.swdocgen.dom.documentation.GitHubWebInfo;
import nth.reflect.app.swdocgen.dom.github.GitRepository;
import nth.reflect.app.swdocgen.dom.javadoc.JavaDocFactory;
import nth.reflect.app.swdocgen.dom.javadoc.tag.HtmlLinkToReference;
import nth.reflect.app.swdocgen.dom.javafile.JavaFile;
import nth.reflect.app.swdocgen.dom.javafile.JavaFileFactory;
import nth.reflect.app.swdocgen.dom.page.FileUtil;
import nth.reflect.app.swdocgen.dom.page.WritableFile;

public class GitHubWebPageFactory {

	private final GitRepository gitRepository;

	public GitHubWebPageFactory(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}

	public void createGitHubWebPages(GitHubWebInfo info) throws IOException {

		Map<String, JavaFile> javaFiles = JavaFileFactory
				.findAllJavaFilesInFolder(info.getProjectsFolder());

		Document javaDoc = JavaDocFactory.getAllJavaDoc(info, javaFiles);

		List<WritableFile> webPages = createWebPages(info, javaDoc);

		gitRepository.deleteFolderContents(info.getGithubWebProjectLocation());

		writeWebPages(webPages);

		copyResources(info.getGithubWebProjectLocation(), javaFiles, webPages);

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

	private void copyResources(File destinationFolder,
			Map<String, JavaFile> javaFiles, List<WritableFile> webPages) {
		for (WritableFile page : webPages) {

			if (page instanceof WebPage) {
				WebPage webPage = (WebPage) page;
				Document document = webPage.getContents();
				Elements elements = document
						.select("img[src],a[id^=Reference_]");

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
						File target = new File(destinationFolder,
								source.getName());
						try {
							FileUtil.copyFolder(source, target);
						} catch (IOException e) {
							e.printStackTrace();
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

	private List<WritableFile> createWebPages(GitHubWebInfo info,
			Document javaDoc) {

		FancyWebPage fancyWebPage = new FancyWebPage(info, javaDoc.clone());

		List<WritableFile> webPages = new ArrayList<>();
		webPages.add(fancyWebPage);
		webPages.add(new IndexWebPage(info, javaDoc.clone(),fancyWebPage.getFile()));
		webPages.add(new RobotsTxt(info.getGithubWebProjectLocation()));
		webPages.add(new SiteMap(info, fancyWebPage.getFile()));
		webPages.add(new PrintableWebPage(info, javaDoc.clone()));
		return webPages;
	}
}

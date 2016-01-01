package nth.introspect.apps.docgenforjavaproj.dom.page;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nth.introspect.apps.docgenforjavaproj.dom.documentation.GitHubHtmlInfo;
import nth.introspect.apps.docgenforjavaproj.dom.github.GitRepository;
import nth.introspect.apps.docgenforjavaproj.dom.javadoc.JavaDocFactory;
import nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag.HtmlLinkToReference;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFile;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFileFactory;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GitHubWebPageFactory {

	private final GitRepository gitRepository;

	public GitHubWebPageFactory(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}

	public void createGitHubWebPages(GitHubHtmlInfo info) throws IOException {

		Map<String, JavaFile> javaFiles = JavaFileFactory
				.findAllJavaFilesInFolder(info.getProjectsFolder());

		Document javaDoc = JavaDocFactory.getAllJavaDoc(info, javaFiles);

		List<WebPage> webPages = createWebPages(info, javaDoc);

		gitRepository.deleteFolderContents(info.getGithubHtmlProjectLocation());

		writeWebPages(webPages);

		copyResources(info.getGithubHtmlProjectLocation(), javaFiles, webPages);

		copyMenuFiles(info.getGithubHtmlProjectLocation());

		gitRepository.commitAndPush(info, info.getGithubHtmlProjectLocation());
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
			Map<String, JavaFile> javaFiles, List<WebPage> webPages) {
		for (WebPage webPage : webPages) {
			Document document = webPage.getDocument();
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

	private void writeWebPages(List<WebPage> webPages) throws IOException {
		for (WebPage webPage : webPages) {
			webPage.write();
		}
	}

	private List<WebPage> createWebPages(GitHubHtmlInfo info, Document javaDoc) {
		List<WebPage> webPages = new ArrayList<>();
		webPages.add(new IndexWebPage(info, javaDoc.clone()));
		webPages.add(new FancyWebPage(info, javaDoc.clone()));
		webPages.add(new PrintableWebPage(info, javaDoc.clone()));
		return webPages;
	}
}

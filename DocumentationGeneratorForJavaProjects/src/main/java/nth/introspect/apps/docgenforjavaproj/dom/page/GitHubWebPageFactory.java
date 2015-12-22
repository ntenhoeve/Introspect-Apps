package nth.introspect.apps.docgenforjavaproj.dom.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nth.introspect.apps.docgenforjavaproj.dom.documentation.GitHubHtmlInfo;
import nth.introspect.apps.docgenforjavaproj.dom.github.GitRepository;
import nth.introspect.apps.docgenforjavaproj.dom.javadoc.JavaDocFactory;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFile;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFileFactory;

import org.jsoup.nodes.Document;


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
		
		// gitRepository.deleteFolderContents(info.getGithubHtmlProjectLocation());
		
		// TODO copyResources
		
		// TODO copyMenuFiles

		writeWebPages(webPages);

//		gitRepository.commitAndPush(info, info.getGithubHtmlProjectLocation());
	}

	private void writeWebPages(List<WebPage> webPages) throws IOException {
		for (WebPage webPage : webPages) {
			webPage.write();
		}
	}

	private List<WebPage> createWebPages(GitHubHtmlInfo info, Document javaDoc) {
		List<WebPage> webPages=new ArrayList<>();
		webPages.add(new IndexWebPage(info, javaDoc.clone()));
		webPages.add(new FancyWebPage(info,javaDoc.clone()));//TODO create a page for each chapter
		webPages.add(new PrintableWebPage(info,javaDoc.clone()));
		return webPages;
	}
}

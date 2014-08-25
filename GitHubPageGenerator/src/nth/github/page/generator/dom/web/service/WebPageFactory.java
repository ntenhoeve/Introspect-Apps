package nth.github.page.generator.dom.web.service;

import java.io.File;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.dom.git.GitRepository;
import nth.github.page.generator.model.pagefactories.HtmlIndexPageFactory;
import nth.github.page.generator.model.pagefactories.HtmlSmallPageFactory;
import nth.github.page.generator.model.text.TextDocument;
import nth.introspect.Introspect;

public class WebPageFactory {
	private GitRepository gitRepository;

	public WebPageFactory(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}
	
	public void updateWebPageFiles(Config config, TextDocument textDocument) {
		initWebFoldersInLocalGitRepository(config);

		HtmlIndexPageFactory.createFile(config, textDocument);
		
		HtmlSmallPageFactory.createFiles(config, textDocument);
		
		gitRepository.commitAndPushWebSiteRepository(config);
	}


	private void initWebFoldersInLocalGitRepository(Config config) {

		File htmlSmallFolder = PathFactory.createLocalGitHubWebSiteRepositorySmallPath(config);
		if (htmlSmallFolder.exists()) {
			htmlSmallFolder.delete();
		}
		htmlSmallFolder.mkdir();
		
		File htmlWideFolder = PathFactory.createLocalGitHubWebSiteRepositoryWidePath(config);
		if (htmlWideFolder.exists()) {
			htmlWideFolder.delete();
		}
		htmlWideFolder.mkdir();


	}
}

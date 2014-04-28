package nth.github.page.generator;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import nth.github.page.generator.model.html.element.page.content.PathFactory;
import nth.github.page.generator.model.pagefactories.HtmlSmallPageFactory;
import nth.github.page.generator.model.pagefactories.TextDocumentFactory;
import nth.github.page.generator.model.text.TextDocument;
import nth.github.page.generator.wiki.model.WikiPage;
import nth.github.page.generator.wiki.service.WikiPageService;

import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class GitHubPageService {

	public void createPages(Config config){
		initFolders(config);
		
		createHtmlFiles(config);
	}

	//TODO add description annotation
	private void createHtmlFiles(Config config) {
		try {
			//TODO move file to config
			File file = config.getWordDocumentLocation();
			FileInputStream fis = new FileInputStream(file);

			XWPFDocument wordDocument = new XWPFDocument(fis);

			//create our own model from the POI word model
			TextDocument textDocument=TextDocumentFactory.create(wordDocument);

			HtmlSmallPageFactory.createFiles(config, textDocument);

			WikiPageService.updateWikiFiles(config,textDocument);
//			for (WikiPage wikiPage : wikiPages) {
//				System.out.println(wikiPage.toString());
//				System.err.println("=================================");
//			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		
	}

	private void initFolders(Config config) {
		
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

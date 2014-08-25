package nth.github.page.generator;

import nth.github.page.generator.dom.text.service.TextDocumentFactory;
import nth.github.page.generator.dom.web.service.WebPageFactory;
import nth.github.page.generator.dom.wiki.service.WikiPageFactory;
import nth.github.page.generator.model.text.TextDocument;
import nth.introspect.Introspect;


public class GitHubPageService {

	private WikiPageFactory wikiPageFactory;
	private WebPageFactory webPageFactory;
	private TextDocumentFactory textDocumentFactory;
	
	public GitHubPageService(WikiPageFactory wikiPageFactory,
			WebPageFactory webPageFactory,
			TextDocumentFactory textDocumentFactory) {
		this.wikiPageFactory = wikiPageFactory;
		this.webPageFactory = webPageFactory;
		this.textDocumentFactory = textDocumentFactory;
	}



	public void createPages(Config config){
		
		TextDocument textDocument=textDocumentFactory.createTextDocument(config);
		
		wikiPageFactory.updateWikiPageFiles(config, textDocument);
		
		webPageFactory.updateWebPageFiles(config, textDocument);
		
	}

}
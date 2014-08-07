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

	
	public void createPages(Config config){
		//TODO in DomainProvider: getBackEndServiceObject(..)
		//TODO or even better: implement basic form of dependency injection
		textDocumentFactory= (TextDocumentFactory) Introspect.getDomainProvider().getServiceObject(TextDocumentFactory.class);
		webPageFactory= (WebPageFactory) Introspect.getDomainProvider().getServiceObject(WebPageFactory.class);
		wikiPageFactory = (WikiPageFactory) Introspect.getDomainProvider().getServiceObject(WikiPageFactory.class);

		
		TextDocument textDocument=textDocumentFactory.createTextDocument(config);
		
		wikiPageFactory.updateWikiPageFiles(config, textDocument);
		
		webPageFactory.updateWebPageFiles(config, textDocument);
		
	}

}
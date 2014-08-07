package nth.github.page.generator.model.pagefactories;

import java.io.File;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.html.element.page.IndexPage;
import nth.github.page.generator.model.text.TextDocument;

public class HtmlIndexPageFactory {

	public static void createFile(Config config, TextDocument textDocument) {
		IndexPage indexPage=new IndexPage(config, textDocument);
		//remove current index file
		File indexFile=new File(indexPage.getPath());
		indexFile.delete();
		//create a new index file
		indexPage.createPageFile();
	}

}

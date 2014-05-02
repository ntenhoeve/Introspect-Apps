package nth.github.page.generator.model.pagefactories;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.html.element.page.IndexPage;

public class HtmlIndexPageFactory {

	public static void createFile(Config config) {
		IndexPage indexPage=new IndexPage(config);
		//remove current index file
		indexPage.getFile().delete();
		//create a new index file
		indexPage.createPageFile();
	}

}

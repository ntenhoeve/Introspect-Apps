package nth.reflect.app.swdocgen.dom.page.web;

import java.io.File;

import nth.introspect.generic.util.StringUtil;
import nth.reflect.app.swdocgen.dom.page.Page;

import org.jsoup.nodes.Document;

public abstract class WebPage extends Page {

	public WebPage(File destinationFolder, String javaDocClass, Document javaDoc) {
		super(destinationFolder, javaDocClass, javaDoc);
	}


	@Override
	protected String createFileName(String title) {
		StringBuilder fileName=new StringBuilder();
		fileName.append(StringUtil.convertToCamelCase(title, true));
		fileName.append(".html");
		return fileName.toString();
	}

	

}

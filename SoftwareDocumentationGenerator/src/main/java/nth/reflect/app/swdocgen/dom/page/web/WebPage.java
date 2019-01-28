package nth.reflect.app.swdocgen.dom.page.web;

import java.io.File;

import org.jsoup.nodes.Document;

import nth.reflect.app.swdocgen.dom.page.Page;
import nth.reflect.fw.generic.util.StringUtil;

public abstract class WebPage extends Page {

	public WebPage(File destinationFolder, String javaDocClass, Document javaDoc) {
		super(destinationFolder, javaDocClass, javaDoc);
	}

	@Override
	protected String createFileName(String title) {
		StringBuilder fileName = new StringBuilder();
		fileName.append(StringUtil.convertToCamelCase(title, true));
		fileName.append(".html");
		return fileName.toString();
	}

}

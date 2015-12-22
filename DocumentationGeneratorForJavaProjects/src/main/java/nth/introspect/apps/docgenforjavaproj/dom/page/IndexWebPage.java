package nth.introspect.apps.docgenforjavaproj.dom.page;

import java.io.File;

import nth.introspect.apps.docgenforjavaproj.dom.documentation.GitHubHtmlInfo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Index files that redirects the user to the title page
 * 
 * @author nilsth
 *
 */
public class IndexWebPage extends WebPage {

	public IndexWebPage(GitHubHtmlInfo info, Document javaDoc) {
		super(info.getGithubHtmlProjectLocation(), info.getClassName(), javaDoc);
	}

	@Override
	protected Document createDocument(String title, Document javaDoc) {
		Document doc = Document.createShell("");
		StringBuilder content = new StringBuilder();
		content.append("0; url=");
		content.append(createFileName(title));
		doc.head().appendElement("meta").attr("http-equiv", "refresh")
				.attr("content", content.toString());
		return doc;
	};

	@Override
	protected File createFile(String title) {
		StringBuilder filePath = new StringBuilder();
		filePath.append(getDestinationFolder());
		filePath.append("/Index.html");
		File file = new File(filePath.toString());
		return file;
	}

	@Override
	protected String createReference(Element hElement) {
		return null;// not applicable
	}


}

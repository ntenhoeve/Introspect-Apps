package nth.reflect.app.swdocgen.dom.page.web;

import java.io.File;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import nth.reflect.app.swdocgen.dom.documentation.GitHubWebInfo;

/**
 * Index files that redirects the user to the MaterialAppBarTitle page
 * 
 * @author nilsth
 *
 */
public class IndexWebPage extends WebPage {

	private static final String INDEX_HTML = "index.html";
	private File homePage;

	public IndexWebPage(GitHubWebInfo info, Document javaDoc, File homePage) {
		super(info.getGithubWebProjectLocation(), info.getClassName(), javaDoc);
		this.homePage = homePage;
	}

	@Override
	public  Document createContents() {
		Document doc = Document.createShell("");
		StringBuilder content = new StringBuilder();
		content.append("0; url=");
		content.append(homePage.getName());
		doc.head().appendElement("meta").attr("http-equiv", "refresh")
				.attr("content", content.toString());
		return doc;
	}


	@Override
	protected File createFile(String title) {
		StringBuilder filePath = new StringBuilder();
		filePath.append(getDestinationFolder());
		filePath.append("/");
		filePath.append(INDEX_HTML);
		File file = new File(filePath.toString());
		return file;
	}

	@Override
	protected String createReference(Element hElement) {
		return null;// not applicable
	}


}

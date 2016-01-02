package nth.introspect.apps.docgenforjavaproj.dom.page.wiki;

import java.io.File;

import nth.introspect.apps.docgenforjavaproj.dom.documentation.GitHubWikiInfo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiHomePage extends WikiPage {

	public WikiHomePage(GitHubWikiInfo info, Document javaDoc) {
		super(info, javaDoc);
	}

	@Override
	public Document createContents() {
		Elements chapters = getJavaDoc().select("h1,h2");

		Document doc = new Document("");
		Element ul = null;
		for (Element chapter : chapters) {
			if (chapter.nodeName().equals("h1")) {
				Element h3 = doc.appendElement("h3");
				Element a = h3.appendElement("a");
				a.html(chapter.html());
				a.attr(HREF, createReference(chapter));
				ul = doc.appendElement("ul");
			} else if (chapter.nodeName().equals("h2")) {
				Element li = ul.appendElement("li");
				Element a = li.appendElement("a");
				a.html(chapter.html());
				a.attr(HREF, createReference(chapter));
			}
		}
		return doc;
	}

	@Override
	protected java.io.File createFile(String title) {
		StringBuilder filePath = new StringBuilder();
		filePath.append(getDestinationFolder());
		filePath.append("/Home.md");
		File file = new File(filePath.toString());
		return file;
	};
}

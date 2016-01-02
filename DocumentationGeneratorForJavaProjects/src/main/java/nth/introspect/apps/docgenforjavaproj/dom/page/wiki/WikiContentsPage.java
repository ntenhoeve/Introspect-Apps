package nth.introspect.apps.docgenforjavaproj.dom.page.wiki;

import java.util.List;

import nth.introspect.apps.docgenforjavaproj.dom.documentation.GitHubWikiInfo;
import nth.introspect.apps.docgenforjavaproj.dom.page.ElementUtil;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class WikiContentsPage extends WikiPage {

	private Element h1;

	public WikiContentsPage(GitHubWikiInfo info, Document javaDoc, Element h1) {
		super(info, javaDoc);
		this.h1 = h1;
	}

	@Override
	public Document createContents() {
		Document doc = new Document("");

		Element divContent = doc.appendElement("div").attr("id", "content");
		List<Node> chapterNodes = ElementUtil.findChapterNodes(h1);
		ElementUtil.addAllNodes(divContent, chapterNodes);
		return doc;
	}

	@Override
	public String getTitle() {
		return h1.html();
	};

}

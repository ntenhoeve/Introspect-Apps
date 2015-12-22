package nth.introspect.apps.docgenforjavaproj.dom.page;

import java.io.File;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPage {

	private final File file;
	private Element h1;
	private Elements chapterElements;

	public WikiPage(Element h1, File gitHubWikiProjectLocation) {
		this.h1 = h1;
		this.chapterElements = getChapterElements(h1);
		this.file = createFile(h1, gitHubWikiProjectLocation);
	}

	private Elements getChapterElements(Element h1) {
		Elements chapterElements=new Elements();
		Element parent = h1.parent();
		if (parent==null) {
			return chapterElements;
		}
		
		Elements siblings = parent.children();
		int startIndex=siblings.indexOf(h1)+1;
		
		for (int index = startIndex; index < siblings.size(); index++) {
		    Element sibling = siblings.get(index);
			if ("h1".equals(sibling.tagName())) {
				return chapterElements;
			} else {
				chapterElements.add(sibling);
			}
		}
		return siblings;
	}

	private File createFile(Element h1, File gitHubWikiProjectLocation) {
		StringBuilder wikiPageFile = new StringBuilder();
		wikiPageFile.append(gitHubWikiProjectLocation);
		wikiPageFile.append("/");
		String title = h1.html();
		wikiPageFile.append(title.replace(" ", "-"));
		wikiPageFile.append(".md");
		File file = new File(wikiPageFile.toString());
		return file;
	}
	
	
	@Override
	public String toString() {
		StringBuilder wikiPage=new StringBuilder();
		wikiPage.append("---------------------------------------------------------------\n");
		wikiPage.append("file:");
		wikiPage.append(file.getAbsolutePath());
		wikiPage.append("\nh1:");
		wikiPage.append(h1);
		wikiPage.append(chapterElements);
		return wikiPage.toString();
	}
}

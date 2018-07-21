package nth.reflect.app.swdocgen.dom.page.wiki;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.app.swdocgen.dom.documentation.GitHubWikiInfo;
import nth.reflect.app.swdocgen.dom.page.ElementUtil;
import nth.reflect.app.swdocgen.dom.page.Page;

public abstract class WikiPage extends Page {


	private static final String MD_EXTENSION = ".md";

	public WikiPage(GitHubWikiInfo info, Document javaDoc) {
		super(info.getGitHubWikiProjectLocation(), info.getClassName(), javaDoc);
	}


	@Override
	protected String createFileName(String title) {
		Elements chapters=getJavaDoc().select("h1");
		int chapterNumber=0;
		for (Element chapter : chapters) {
			chapterNumber++;
			if (chapter.html().equals(title)) {
				StringBuilder wikiPageFile = new StringBuilder();
				wikiPageFile.append(String.format("%02d", chapterNumber));
				wikiPageFile.append("-");
				wikiPageFile.append(title.replace(" ", "-"));
				wikiPageFile.append(MD_EXTENSION);
				return wikiPageFile.toString();
			}
		}
		return null;		
	}


	@Override
	protected String createReference(Element hElement) {
		if (hElement.nodeName().equals("h1")) {
			return createH1Reference(hElement);
		} else {
			return createH2orH3Reference(hElement);
		}
	}

	private String createH2orH3Reference(Element h) {
		Element h1=ElementUtil.findPreviousH1Element(h);
		StringBuilder reference=new StringBuilder();
		reference.append(createH1Reference(h1));
		reference.append("#");
		reference.append(h.html().replace(" ", "-").toLowerCase());
		return reference.toString();
	}

	private String createH1Reference(Element h1) {
		return createFileName(h1.html()).replace(MD_EXTENSION, "");
	}
}

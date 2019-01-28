package nth.reflect.app.swdocgen.dom.page.wiki;

import org.jsoup.nodes.Document;

import nth.reflect.app.swdocgen.dom.documentation.GitHubWikiInfo;
import nth.reflect.app.swdocgen.dom.page.Page;

public abstract class WikiPage extends Page {

	public WikiPage(GitHubWikiInfo info, Document javaDoc) {
		super(info.getGitHubWikiProjectLocation(), info.getClassName(), javaDoc);
	}

	// @Override
	// protected String createFileName(String title) {
	// Elements chapters=getJavaDoc().select("h1");
	// int chapterNumber=0;
	// for (Element chapter : chapters) {
	// chapterNumber++;
	// if (chapter.html().equals(title)) {
	// StringBuilder wikiPageFile = new StringBuilder();
	// wikiPageFile.append(String.format("%02d", chapterNumber));
	// wikiPageFile.append("-");
	// wikiPageFile.append(title.replace(" ", "-"));
	// wikiPageFile.append(MD_EXTENSION);
	// return wikiPageFile.toString();
	// }
	// }
	// return null;
	// }

}

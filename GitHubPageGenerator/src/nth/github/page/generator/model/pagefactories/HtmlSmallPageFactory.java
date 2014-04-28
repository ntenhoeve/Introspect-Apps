package nth.github.page.generator.model.pagefactories;

import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.page.Page;
import nth.github.page.generator.model.html.element.page.SmallPage;
import nth.github.page.generator.model.html.element.page.content.ContentFactory;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.Node;
import nth.github.page.generator.model.text.TextDocument;

public class HtmlSmallPageFactory {

	public static List<Page> create(Config config,
			TextDocument textDocument) {
		List<Page> smallPages = new ArrayList<>();

		List<Node> chapters = textDocument
				.findChilderenOfType(TextChapterLevel1.class);

		for (Node chapter : chapters) {
			TextChapterLevel1 chapterLevel1 = (TextChapterLevel1) chapter;

			// create page for the chapter
			Element content = ContentFactory.create(config,chapterLevel1, null);
			SmallPage smallPage = new SmallPage(config, content, chapterLevel1,null);
			smallPages.add(smallPage);

			// create pages for sub chapters
			List<Node> subChapters = chapterLevel1
					.findChilderenOfType(TextChapterLevel2.class);
			for (Node node : subChapters) {
				TextChapterLevel2 chapterLevel2 = (TextChapterLevel2) node;
				content = ContentFactory.create(config, chapterLevel1, chapterLevel2);
				smallPage = new SmallPage(config, content, chapterLevel1, chapterLevel2);
				smallPages.add(smallPage);
			}
		}
		return smallPages;
	}

	public static void createFiles(Config config,
			TextDocument textDocument) {
		List<Page> pages = create(config, textDocument);

		for (Page page : pages) {
			page.createPageFile();
		}

	}

}

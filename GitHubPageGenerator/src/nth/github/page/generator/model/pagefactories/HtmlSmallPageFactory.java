package nth.github.page.generator.model.pagefactories;

import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.dom.wiki.model.WikiPage;
import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.page.Page;
import nth.github.page.generator.model.html.element.page.SmallPage;
import nth.github.page.generator.model.html.element.page.content.ContentFactory;
import nth.github.page.generator.model.html.element.page.content.HomePageContent;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextNode;
import nth.github.page.generator.model.text.TextDocument;

public class HtmlSmallPageFactory {

	public static List<Page> createPages(Config config,
			TextDocument textDocument) {
		List<Page> smallPages = new ArrayList<>();

		List<TextNode> chapters = textDocument
				.findChilderenOfType(TextChapterLevel1.class);

		Element homePageContent = new HomePageContent(config, textDocument);
		TextChapterLevel1 dummyChapter=new TextChapterLevel1("Home");
		Page homePage = new SmallPage(config, homePageContent, dummyChapter, null);
		smallPages.add(homePage);

		for (TextNode chapter : chapters) {
			TextChapterLevel1 chapterLevel1 = (TextChapterLevel1) chapter;

			// create page for the chapter
			Element content = ContentFactory
					.create(config, chapterLevel1, null);
			SmallPage smallPage = new SmallPage(config, content, chapterLevel1,
					null);
			smallPages.add(smallPage);

			// create pages for sub chapters
			List<TextNode> subChapters = chapterLevel1
					.findChilderenOfType(TextChapterLevel2.class);
			for (TextNode node : subChapters) {
				TextChapterLevel2 chapterLevel2 = (TextChapterLevel2) node;
				content = ContentFactory.create(config, chapterLevel1,
						chapterLevel2);
				smallPage = new SmallPage(config, content, chapterLevel1,
						chapterLevel2);
				smallPages.add(smallPage);
			}
		}

		// set previous home and next pages;
		for (int index = 0; index < smallPages.size(); index++) {
			Page page = smallPages.get(index);
			page.setHomePage(homePage);
			if (index > 0) {
				Page previousPage = smallPages.get(index - 1);
				page.setPreviousPage(previousPage);
			}
			if (index < smallPages.size() - 1) {
				Page nextPage = smallPages.get(index + 1);
				page.setNextPage(nextPage);
			}
		}

		return smallPages;
	}

	public static void createFiles(Config config, TextDocument textDocument) {
		List<Page> pages = createPages(config, textDocument);

		for (Page page : pages) {
			page.createPageFile();
		}

	}

}

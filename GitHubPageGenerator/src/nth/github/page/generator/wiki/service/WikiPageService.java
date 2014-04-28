package nth.github.page.generator.wiki.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.html.element.page.content.PathFactory;
import nth.github.page.generator.model.text.Node;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextDocument;
import nth.github.page.generator.wiki.model.WikiPage;
import nth.github.page.generator.wiki.model.WikiPageChapter;
import nth.github.page.generator.wiki.model.WikiPageHome;
import nth.github.page.generator.wiki.model.WikiPageSubChapter;

public class WikiPageService {

	
	public static void updateWikiFiles(Config config, TextDocument textDocument) {
		removeOldWikiFiles(config);
		
		List<WikiPage> wikiPages = createPages(config, textDocument);
		
		for (WikiPage wikiPage : wikiPages) {
			wikiPage.createFile();
		}
	}
	
	private static void removeOldWikiFiles(Config config) {
		File wikiFolder = PathFactory.createLocalGitHubWikiRepositoryPath(config);
		FilenameFilter filter=new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".md"); 
			}
		};
		File[] wikiFiles = wikiFolder.listFiles(filter);
		if (wikiFiles!=null) {
			for (File wikiFile : wikiFiles) {
				wikiFile.delete();
			}
		}
	}

	public static List<WikiPage> createPages(Config config,
			TextDocument textDocument) {
		List<WikiPage> wikiPages = new ArrayList<>();

		List<Node> chapters = textDocument
				.findChilderenOfType(TextChapterLevel1.class);
		chapters.remove(0);//Remove index chapter
		
		WikiPageHome wikiHomePage = new WikiPageHome(config, chapters);
		wikiPages.add(wikiHomePage);

		
		for (Node chapter : chapters) {
			TextChapterLevel1 textChapterLevel1 = (TextChapterLevel1) chapter;
				WikiPage wikiPage = new WikiPageChapter(config,
						textChapterLevel1);
				wikiPages.add(wikiPage);

				List<Node> subChapters = textChapterLevel1
						.findChilderenOfType(TextChapterLevel2.class);
				for (Node subChapter : subChapters) {
					TextChapterLevel2 textChapterLevel2 = (TextChapterLevel2) subChapter;
					wikiPage = new WikiPageSubChapter(config,
							textChapterLevel1, textChapterLevel2);
					wikiPages.add(wikiPage);
				}
		}

		// set previous home and next pages;
		WikiPage homePage = wikiPages.get(0);
		for (int index = 0; index < wikiPages.size(); index++) {
			WikiPage wikiPage = wikiPages.get(index);
			wikiPage.setHomePage(homePage);
			if (index > 0) {
				WikiPage previousPage = wikiPages.get(index - 1);
				wikiPage.setPreviousPage(previousPage);
			}
			if (index < wikiPages.size() - 1) {
				WikiPage nextPage = wikiPages.get(index + 1);
				wikiPage.setNextPage(nextPage);
			}
		}

		return wikiPages;
	}

}

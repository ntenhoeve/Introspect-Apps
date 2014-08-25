package nth.github.page.generator.dom.wiki.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.dom.git.GitRepository;
import nth.github.page.generator.dom.wiki.model.WikiPage;
import nth.github.page.generator.dom.wiki.model.WikiPageChapter;
import nth.github.page.generator.dom.wiki.model.WikiPageHome;
import nth.github.page.generator.dom.wiki.model.WikiPageSubChapter;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextDocument;
import nth.github.page.generator.model.text.TextNode;

public class WikiPageFactory {
	private GitRepository gitRepository;

	public WikiPageFactory(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}

	public void updateWikiPageFiles(Config config, TextDocument textDocument) {
		removeWikiFilesInLocalGitRepository(config);

		createWikiFilesInLocalGitRepository(config, textDocument);

		gitRepository.commitAndPushWebWiki(config);
	}

	private void createWikiFilesInLocalGitRepository(Config config,
			TextDocument textDocument) {
		List<WikiPage> wikiPages = createWikiPages(config, textDocument);

		for (WikiPage wikiPage : wikiPages) {
			wikiPage.createFile();
		}
	}

	private List<WikiPage> createWikiPages(Config config,
			TextDocument textDocument) {
		List<WikiPage> wikiPages = new ArrayList<>();

		List<TextNode> chapters = textDocument
				.findChilderenOfType(TextChapterLevel1.class);

		WikiPageHome wikiHomePage = new WikiPageHome(config, chapters);
		wikiPages.add(wikiHomePage);

		for (TextNode chapter : chapters) {
			TextChapterLevel1 textChapterLevel1 = (TextChapterLevel1) chapter;
			WikiPage wikiPage = new WikiPageChapter(config, textDocument, textChapterLevel1);
			wikiPages.add(wikiPage);

			List<TextNode> subChapters = textChapterLevel1
					.findChilderenOfType(TextChapterLevel2.class);
			for (TextNode subChapter : subChapters) {
				TextChapterLevel2 textChapterLevel2 = (TextChapterLevel2) subChapter;
				wikiPage = new WikiPageSubChapter(config, textDocument, textChapterLevel1,
						textChapterLevel2);
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

	private void removeWikiFilesInLocalGitRepository(Config config) {
		File wikiFolder = PathFactory
				.createLocalGitHubWikiRepositoryPath(config);
		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".md");
			}
		};
		File[] wikiFiles = wikiFolder.listFiles(filter);
		if (wikiFiles != null) {
			for (File wikiFile : wikiFiles) {
				wikiFile.delete();
			}
		}

	}
}

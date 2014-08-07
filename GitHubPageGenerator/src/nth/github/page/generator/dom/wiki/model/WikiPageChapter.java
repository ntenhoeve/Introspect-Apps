package nth.github.page.generator.dom.wiki.model;

import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.model.text.TextDocument;
import nth.github.page.generator.model.text.TextNode;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextChapterLevel3;
import nth.github.page.generator.model.text.TextHyperLink;
import nth.github.page.generator.model.text.TextText;

public class WikiPageChapter extends WikiPage {
	private TextChapterLevel1 textChapterLevel1;
	private TextDocument textDocument;

	public WikiPageChapter(Config config, TextDocument textDocument, TextChapterLevel1 textChapterLevel1) {
		super(config);
		this.textDocument = textDocument;
		this.textChapterLevel1 = textChapterLevel1;
	}

	@Override
	public void addContent() {
		
		addChapterText();		
		
		List<TextNode> subChapters = textChapterLevel1.findChilderenOfType(TextChapterLevel2.class);

		for (TextNode node : subChapters) {
			TextChapterLevel2 textChapterLevel2 = (TextChapterLevel2) node;
			getChilderen().add(
					new WikiHyperlink(textChapterLevel2.getTitle(), PathFactory
							.createRemoteGitHubWikiPath(getConfig(), textChapterLevel1,
									textChapterLevel2)));
			getChilderen().add(new WikiText("<br>\n"));//new line
		}

	}

	private void addChapterText() {
		for (TextNode textNode : textChapterLevel1.getChilderen()) {
			if (textNode.getClass() == TextText.class) {
				TextText textText = (TextText) textNode;
				WikiText wikiText = new WikiText(textText);
				getChilderen().add(wikiText);
			} else if (textNode.getClass() == TextHyperLink.class) {
				TextHyperLink textHyperLink = (TextHyperLink) textNode;
				WikiHyperlink wikiHyperlink = new WikiHyperlink(getConfig(), textDocument, textHyperLink);
				getChilderen().add(wikiHyperlink);
			}
		}
	}

	@Override
	public String getPath() {
		return PathFactory.createRemoteGitHubWikiPath(getConfig(), textChapterLevel1, null);
	}

	@Override
	public String getTitle() {
		return textChapterLevel1.getTitle();
	}
}

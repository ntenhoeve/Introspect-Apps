package nth.github.page.generator.wiki.model;

import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.html.element.page.content.PathFactory;
import nth.github.page.generator.model.text.Node;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextChapterLevel3;
import nth.github.page.generator.model.text.TextHyperLink;
import nth.github.page.generator.model.text.TextText;

public class WikiPageChapter extends WikiPage {
	private TextChapterLevel1 textChapterLevel1;

	public WikiPageChapter(Config config, TextChapterLevel1 textChapterLevel1) {
		super(config);
		this.textChapterLevel1 = textChapterLevel1;
	}

	@Override
	public void addContent() {
		
		addChapterText();		
		
		List<Node> subChapters = textChapterLevel1.findChilderenOfType(TextChapterLevel2.class);

		for (Node node : subChapters) {
			TextChapterLevel1 textChapterLevel1 = (TextChapterLevel1) node;
			getChilderen().add(
					new WikiHyperlink(textChapterLevel1.getTitle(), PathFactory
							.createRemoteGitHubWikiPath(getConfig(), textChapterLevel1,
									null)));
			getChilderen().add(new WikiText("<br>\n"));//new line
		}

	}

	private void addChapterText() {
		for (Node textNode : textChapterLevel1.getChilderen()) {
			if (textNode.getClass() == TextText.class) {
				TextText textText = (TextText) textNode;
				WikiText wikiText = new WikiText(textText);
				getChilderen().add(wikiText);
			} else if (textNode.getClass() == TextHyperLink.class) {
				TextHyperLink textHyperLink = (TextHyperLink) textNode;
				WikiHyperlink wikiHyperlink = new WikiHyperlink(textHyperLink);
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

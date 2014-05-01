package nth.github.page.generator.wiki.model;

import nth.github.page.generator.model.text.TextNode;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextChapterLevel3;
import nth.github.page.generator.model.text.TextHyperLink;
import nth.github.page.generator.model.text.TextListItem;
import nth.github.page.generator.model.text.TextNodeContainer;
import nth.github.page.generator.model.text.TextText;

public class WikiPageFactory {

	public static void populate(WikiNodeContainer wikiNodeContainer,
			TextNode textNode) {

		if (textNode.getClass() == TextChapterLevel1.class) {
			TextChapterLevel1 textChapterLevel1 = (TextChapterLevel1) textNode;
			WikiChapterLevel1 wikiChapterLevel1 = new WikiChapterLevel1(
					textChapterLevel1);
			wikiNodeContainer.getChilderen().add(wikiChapterLevel1);
			wikiNodeContainer = wikiChapterLevel1;
		} else if (textNode.getClass() == TextChapterLevel2.class) {
			TextChapterLevel2 textChapterLevel2 = (TextChapterLevel2) textNode;
			WikiChapterLevel2 wikiChapterLevel2 = new WikiChapterLevel2(
					textChapterLevel2);
			wikiNodeContainer.getChilderen().add(wikiChapterLevel2);
			wikiNodeContainer = wikiChapterLevel2;
		} else if (textNode.getClass() == TextChapterLevel3.class) {
			TextChapterLevel3 textChapterLevel3 = (TextChapterLevel3) textNode;
			WikiChapterLevel3 wikiChapterLevel3 = new WikiChapterLevel3(
					textChapterLevel3);
			wikiNodeContainer.getChilderen().add(wikiChapterLevel3);
			wikiNodeContainer = wikiChapterLevel3;
		} else if (textNode.getClass() == TextListItem.class) {
			TextListItem textListItem = (TextListItem) textNode;
			WikiListItem wikiListItem = new WikiListItem(textListItem);
			
			
			wikiNodeContainer.getChilderen().add(wikiListItem);
		} else if  (textNode.getClass() == TextText.class) {
			TextText textText = (TextText) textNode;
			WikiText wikiText = new WikiText(textText);
			wikiNodeContainer.getChilderen().add(wikiText);
		} else if (textNode.getClass() == TextHyperLink.class) {
			TextHyperLink textHyperLink = (TextHyperLink) textNode;
			WikiHyperlink wikiHyperlink = new WikiHyperlink(textHyperLink);
			wikiNodeContainer.getChilderen().add(wikiHyperlink);
		}

		if (textNode instanceof TextNodeContainer) {
			TextNodeContainer textNodeContainer = (TextNodeContainer) textNode;
			for (TextNode child : textNodeContainer.getChilderen()) {
				// recursive call to populate children
				populate(wikiNodeContainer, child);
			}

		}

	}
}

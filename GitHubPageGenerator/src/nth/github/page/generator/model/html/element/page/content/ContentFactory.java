package nth.github.page.generator.model.html.element.page.content;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.html.element.AttributeType;
import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.ElementType;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextHyperLink;
import nth.github.page.generator.model.text.TextListItem;
import nth.github.page.generator.model.text.TextNode;
import nth.github.page.generator.model.text.TextText;
import nth.github.page.generator.model.text.TextList;

public class ContentFactory {

	public static Element create(Config config, TextChapterLevel1 chapterLevel1,
			TextChapterLevel2 chapterLevel2) {

		Element div = new Element(ElementType.DIV);

		Element h1 = div.addElement(ElementType.H1);
		h1.addText(chapterLevel1.getTitle());

		if (chapterLevel2 == null) {
			addNodesToElement(div, chapterLevel1.getChilderen());

			// add links for all sub chapters
			List<TextNode> subChapters = chapterLevel1
					.findChilderenOfType(TextChapterLevel2.class);
			Element list = div.addElement(ElementType.UL);

			for (TextNode node : subChapters) {
				Element item = list.addElement(ElementType.LI);
				;
				Element link = item.addElement(ElementType.A);
				TextChapterLevel2 subChapter = (TextChapterLevel2) node;
				link.addText(subChapter.getTitle());
				link.addAttribute(AttributeType.HREF, PathFactory
						.createSmallHtmlPagePath(config, chapterLevel1,
								subChapter));
			}
		} else {
			addNodesToElement(div, chapterLevel2.getChilderen());
		}
		return div;
	}

	private static void addNodesToElement(Element element, List<TextNode> children) {
		for (TextNode node : children) {
			if (node instanceof TextText) {
				TextText textNode = (TextText) node;
				String text = textNode.getText();
				if (text == null) {
					element.addText("<br>");
				} else {
					element.addText(text);
				}
			} else if (node instanceof TextHyperLink) {
				TextHyperLink hyperLink = (TextHyperLink) node;
				Element link = element.addElement(ElementType.A);
				link.addText(hyperLink.getText());
				String uri = hyperLink.getUri();
				if (uri != null) {
					link.addAttribute(AttributeType.HREF, hyperLink.getUri());
				}
			} else if (node instanceof TextList) {
				TextList textList = (TextList) node;
				Element ul = element.addElement(ElementType.UL);
				for (TextNode item : textList.getChilderen()) {
					TextListItem listItem = (TextListItem) item;
					Element li = ul.addElement(ElementType.LI);
					addNodesToElement(li, listItem.getChilderen());
				}
			}
		}
	}

}

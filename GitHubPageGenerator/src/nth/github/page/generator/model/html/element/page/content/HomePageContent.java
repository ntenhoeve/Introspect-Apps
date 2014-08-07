package nth.github.page.generator.model.html.element.page.content;

import java.util.List;

import org.apache.poi.xssf.usermodel.TextFontAlign;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.model.html.element.AttributeType;
import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.ElementType;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextDocument;
import nth.github.page.generator.model.text.TextNode;

public class HomePageContent extends Element {

	public HomePageContent(Config config, TextDocument textDocument) {
		super(ElementType.DIV);

		List<TextNode> chapters = textDocument.findChilderenOfType(TextChapterLevel1.class);
		for (TextNode chapter : chapters) {
			TextChapterLevel1 chapterLevel1=(TextChapterLevel1) chapter;
			
			chapterLevel1.getTitle();
			Element h2 = addElement(ElementType.H2);
			Element a = h2.addElement(ElementType.A);
			//TODO!!!  a.addAttribute(AttributeType.HREF,PathFactory.createRemoteGitHubWebSiteUri(config, page));
			a.addText(chapterLevel1.getTitle());
			
			Element list = addElement(ElementType.UL);
			List<TextNode> subChapters = chapterLevel1.findChilderenOfType(TextChapterLevel2.class);
			for (TextNode subChapter : subChapters) {
				TextChapterLevel2 chapterLevel2=(TextChapterLevel2) subChapter;
				Element listItem = list.addElement(ElementType.LI);
				a = listItem.addElement(ElementType.A);
				//TODO!!!  a.addAttribute(AttributeType.HREF,PathFactory.createRemoteGitHubWebSiteUri(config, page));
				a.addText(chapterLevel2.getTitle());
			}
		}

	}

}

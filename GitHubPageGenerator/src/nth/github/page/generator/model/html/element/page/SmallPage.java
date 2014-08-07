package nth.github.page.generator.model.html.element.page;

import java.io.File;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.model.html.element.AttributeType;
import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.ElementType;
import nth.github.page.generator.model.html.element.style.HtmlColor;
import nth.github.page.generator.model.html.element.style.StyleBuilder;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;

public class SmallPage extends WidePage {


	public SmallPage(Config config,  Element content, TextChapterLevel1 chapterLevel1,TextChapterLevel2 chapterLevel2) {
		super(config, null, content, chapterLevel1, chapterLevel2);
	}

	@Override
	public String getPath() {
		String path = PathFactory.createSmallHtmlPagePath(config, chapterLevel1, chapterLevel2);
		return path;
	}

	
	@Override
	public Width getWidth() {
		return Width.SMALL;
	}

	@Override
	protected void createBody(Element html) {
		Element body = html.addElement(ElementType.BODY);
		createTitleBar(body);

		Element table = body.addElement(ElementType.TABLE);
		StyleBuilder styleBuilder = new StyleBuilder();
		styleBuilder.marginTop(TITLE_BAR_HEIGHT);
		styleBuilder.width(FULL_WIDTH);
		table.addAttribute(styleBuilder);

		Element tableRow = table.addElement(ElementType.TR);

		Element contentCell = tableRow.addElement(ElementType.TD);
		styleBuilder = new StyleBuilder();
		styleBuilder.width(FULL_WIDTH);
		styleBuilder.verticalAlign("top");
		contentCell.addAttribute(styleBuilder);

		Element contentDiv = contentCell.addElement(ElementType.DIV);
		contentDiv.addAttribute(AttributeType.ID, "content");
		styleBuilder = new StyleBuilder();
		styleBuilder.backGroundColor(HtmlColor.LIGHT_GRAY);
		styleBuilder.padding("7px");
		styleBuilder.borderRadius("10px");
		contentDiv.addAttribute(styleBuilder);
		contentDiv.addElement(contentElement);
	}


}

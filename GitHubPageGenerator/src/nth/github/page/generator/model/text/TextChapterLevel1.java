package nth.github.page.generator.model.text;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class TextChapterLevel1 extends TextNodeContainer {

	private String title;
	
	public TextChapterLevel1(XWPFParagraph paragraph) {
		title=ParagraphUtil.toString(paragraph);
	}

	public TextChapterLevel1(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}


	
}

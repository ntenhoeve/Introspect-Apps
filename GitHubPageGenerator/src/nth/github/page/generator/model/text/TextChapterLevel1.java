package nth.github.page.generator.model.text;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class TextChapterLevel1 extends TextNodeContainer {

	private String title;
	
	public TextChapterLevel1(XWPFParagraph paragraph) {
		List<XWPFRun> runs = paragraph.getRuns();
		title=ParagraphUtil.toString(paragraph);
	}

	public String getTitle() {
		return title;
	}


	
}

package nth.github.page.generator.model.text;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class ParagraphUtil {

	public static String toString(XWPFParagraph paragraph) {
		StringBuilder text = new StringBuilder();

		List<XWPFRun> runs = paragraph.getRuns();
		for (XWPFRun run : runs) {
			text.append(run.getText(0));
		}
		return text.toString();

	}

}

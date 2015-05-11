package nth.software.doc.generator.model;

import java.util.List;

import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.tokenizer.InlineTagName;

public class Paragraph extends Chapter {

	public Paragraph(String title, List<Node> children) {
		super(title,children);
	}

}

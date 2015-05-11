package nth.software.doc.generator.model;

import java.util.List;

import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.tokenizer.InlineTagName;

public class Chapter extends NodeContainer<Node> {

	private final String title;

	public Chapter(String title, List<Node> children) {
		super();
		this.title = title;
		getChildren().addAll(children);
	}

	public String getTitle() {
		return title;
	}

	public boolean containsBeginOfFileTag(String valueToFind) {
		for (Node child : getChildren()) {
			if (child instanceof InlineTag) {
				InlineTag inlineTag = (InlineTag) child;
				InlineTagName name = inlineTag.getName();
				String value = inlineTag.getValue();
				if (name == InlineTagName.BEGINOFFILE && value.equals(valueToFind)) {
					return true;
				}
			}
		}
		return false;
	}

}

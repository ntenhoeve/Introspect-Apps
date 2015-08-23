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
			if (containsBeginOfFileTag(child,valueToFind)) {
				return true;
			}
		}
		return false;
	}

	private boolean containsBeginOfFileTag(Node node, String valueToFind) {
		if (node instanceof InlineTag) {
			InlineTag inlineTag = (InlineTag) node;
			InlineTagName name = inlineTag.getName();
			String value = inlineTag.getValue();
			if (name == InlineTagName.BEGINOFFILE && value.equals(valueToFind)) {
				return true;
			}
		}

		if (node instanceof NodeContainer<?> && ! (node instanceof Chapter)) {
			NodeContainer<?> nodeContainer = (NodeContainer<?>) node;
			for (Node child : nodeContainer.getChildren()) {
				// recursive call
				if (containsBeginOfFileTag(child, valueToFind)) {
					return true;
				}

			}
		}
		return false;
	}

}

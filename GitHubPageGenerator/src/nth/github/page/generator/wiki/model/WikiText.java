package nth.github.page.generator.wiki.model;

import nth.github.page.generator.model.text.TextText;

public class WikiText extends WikiNode {

	private final String text;

	public WikiText(TextText textText) {
		this.text = textText.getText();
	}

	public WikiText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		if (text == null) {
			return "<br>";
		} else {
			return text;
		}
	}

}

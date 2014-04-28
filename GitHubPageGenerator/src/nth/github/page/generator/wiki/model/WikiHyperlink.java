package nth.github.page.generator.wiki.model;

import java.net.URI;

import nth.github.page.generator.model.text.TextHyperLink;

public class WikiHyperlink extends WikiNode {

	private final String text;
	private final String uri;

	public WikiHyperlink(TextHyperLink textHyperLink) {
		this.text = textHyperLink.getText();
		this.uri = textHyperLink.getUri();
	}

	public WikiHyperlink(String text, String uri) {
		this.text = text;
		this.uri = uri;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[");
		string.append(text.trim());
		string.append("]");
		if (uri != null && uri.trim().length() > 0) {
			string.append("(");
			string.append(uri.trim());
			string.append(")");
		}
		return string.toString();
	}

}

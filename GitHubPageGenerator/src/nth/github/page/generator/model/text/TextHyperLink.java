package nth.github.page.generator.model.text;

import java.net.URI;

public class TextHyperLink extends Node {

	private final String text;
	private final String uri;

	public TextHyperLink(String text, String uri) {
		this.text = text;
		this.uri = uri;
	}

	public String getText() {
		return text;
	}

	public String getUri() {
		return uri;
	}
	
	

}

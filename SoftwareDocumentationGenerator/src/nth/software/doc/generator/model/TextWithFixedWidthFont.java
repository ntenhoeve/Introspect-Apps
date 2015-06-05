package nth.software.doc.generator.model;

public class TextWithFixedWidthFont implements Node {

	private final String text;

	public TextWithFixedWidthFont(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}

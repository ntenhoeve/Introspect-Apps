package nth.software.doc.generator.model;

public class Text implements Node {

	private final String text;

	public Text(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}

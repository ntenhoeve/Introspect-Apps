package nth.software.doc.generator.model;

public class Hyperlink implements Node {

	private final String text;
	private final String href;

	public Hyperlink(String text, String href) {
		this.text = text;
		this.href = href;
	}

	public String getText() {
		return text;
	}

	public String getHref() {
		return href;
	}

}

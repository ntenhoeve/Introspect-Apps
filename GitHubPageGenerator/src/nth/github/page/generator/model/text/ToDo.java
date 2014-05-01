package nth.github.page.generator.model.text;

public class ToDo extends TextNode{

	private final String text;

	public ToDo(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}

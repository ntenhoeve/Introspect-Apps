package nth.software.doc.generator.model;

public class Image implements Node {

	private final String src;

	public Image(String src) {
		this.src = src;
	}

	public String getSrc() {
		return src;
	}


}

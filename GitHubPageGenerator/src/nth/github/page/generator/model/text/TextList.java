package nth.github.page.generator.model.text;

import java.math.BigInteger;


public class TextList extends TextNodeContainer {


	private final BigInteger id;

	public TextList(BigInteger id) {
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}




}

package nth.software.doc.generator.tokenizer;

public enum ElementName {
	// chapters
	H1, H2, H3,
	// paragraph
	P,
	// new line
	BR,
	// hyper link
	A,
	// bold
	B,
	// underline
	U,
	// list
	UL, LI,
	// image
	IMG,
	// table
	TABLE, TR, TH, TD;
	
	public String toLowerCase() {
		String name = this.toString();
		return name;
	}
}

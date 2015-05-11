package nth.software.doc.generator.tokenizer;

public enum TagName {
	AUTHOR, VERSION, PARAM, RETURN, EXCEPTION, SEE, SINCE, SERIAL, DEPRECATED;

	public String toLowerCase() {
		String name = this.toString();
		return name;
	}

}

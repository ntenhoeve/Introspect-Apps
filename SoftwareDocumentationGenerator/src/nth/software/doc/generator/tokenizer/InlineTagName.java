package nth.software.doc.generator.tokenizer;

public enum InlineTagName {
	// in-line tags for internal use
	BEGINOFFILE, ENDOFFILE,TODO,
	// in-line JavaDoc tags (tags between braces)
	CODE, DOCROOT, INHERITDOC, LINK, LINKPLAIN, VALUE, PROJECTS_FOLDER;

	public String toLowerCase() {
		String lowerCase = this.toString().toLowerCase();
		return lowerCase;
	}

}

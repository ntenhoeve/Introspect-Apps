package nth.software.doc.generator.model.inlinetag;

import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.tokenizer.InlineTagName;

public class InlineTag implements Node {

	private final String value;
	private final InlineTagName inlineTagName;

	public InlineTag(InlineTagName inlineTagName) {
		this(inlineTagName,null);
	}
	
	public InlineTag(InlineTagName inlineTagName, String value) {
		this.inlineTagName = inlineTagName;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public InlineTagName getName() {
		return inlineTagName;
	}

	
	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		reply.append("{@");
		reply.append(inlineTagName.toLowerCase());
		if (value != null) {
			reply.append(" ");
			reply.append(value);
		}
		reply.append("}");
		return reply.toString();
	}

	
}

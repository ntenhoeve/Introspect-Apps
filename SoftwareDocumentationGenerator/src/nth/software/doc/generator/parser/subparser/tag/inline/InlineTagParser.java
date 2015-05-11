package nth.software.doc.generator.parser.subparser.tag.inline;

import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.InlineTagName;
import nth.software.doc.generator.tokenizer.InlineTagToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;
import nth.software.doc.generator.tokenizer.TokenFactory;

public class InlineTagParser implements SubParser<InlineTag> {

	private final InlineTagName inlineTagName;
	private final InlineTagToken token;

	public InlineTagParser(InlineTagName inlineTagName) {
		this.inlineTagName = inlineTagName;
		token = new InlineTagToken(inlineTagName);
	}

	@Override
	public Token getStartToken() {
		return token;
	}

	@Override
	public InlineTag parse(JavaDocTokenizer javaDocTokenizer) {
		FoundToken foundToken = javaDocTokenizer.currentToken();
		String inlineTag = foundToken.getText();
		InlineTagName tagName = getTagName(inlineTag);
		String tagValue = getTagValue(inlineTag, tagName);
		InlineTag javaDocInlineTag = new InlineTag(tagName, tagValue);
		return javaDocInlineTag;
	}

	private String getTagValue(String inlineTag, InlineTagName tagName) {
		int startPos = inlineTag.indexOf(tagName.toLowerCase()) + tagName.toLowerCase().length();
		int endPos = inlineTag.indexOf("}", startPos);
		String tagValue = inlineTag.substring(startPos, endPos).trim();
		if (tagValue.length() == 0) {
			return null;
		} else {
			return tagValue;
		}

	}

	private InlineTagName getTagName(String inlineTag) {
		inlineTag = inlineTag.replace("}", " }");
		int startPos = inlineTag.indexOf(InlineTagToken.TAG_INDICATOR)
				+ InlineTagToken.TAG_INDICATOR.length();
		int endPos = inlineTag.indexOf(" ", startPos);
		String name = inlineTag.substring(startPos, endPos).trim()
				.toUpperCase();
		InlineTagName inlineTagName = InlineTagName.valueOf(name);
		return inlineTagName;
	}

}

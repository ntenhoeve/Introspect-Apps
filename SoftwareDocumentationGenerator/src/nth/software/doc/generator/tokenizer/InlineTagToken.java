package nth.software.doc.generator.tokenizer;

import java.util.regex.Pattern;

import nth.software.doc.generator.model.tag.Tag;
import nth.software.doc.generator.regex.Regex;
import nth.software.doc.generator.regex.Repetition;

public class InlineTagToken implements Token {

	public static final String TAG_INDICATOR = "@";
	private final InlineTagName tagName;
	private final Pattern pattern;

	public InlineTagToken(InlineTagName tagName) {
		this.tagName = tagName;
		pattern = new Regex().caseUnsensativeMode().literal("{")
				.whiteSpace(Repetition.zeroOrMoreTimes()).literal(TAG_INDICATOR, Repetition.zeroOrMoreTimes())
				.whiteSpace(Repetition.zeroOrMoreTimes()).literal(tagName.toLowerCase())
				.whiteSpace(Repetition.zeroOrMoreTimes())
				.anyCharacter(Repetition.zeroOrMoreTimes().reluctant()).literal("}")
				.asPattern();
	}
	
	public Pattern getPattern() {
		return pattern;
	}
	

	public InlineTagName getTagName() {
		return tagName;
	}
}

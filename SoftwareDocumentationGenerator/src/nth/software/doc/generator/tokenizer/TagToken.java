package nth.software.doc.generator.tokenizer;

import java.util.regex.Pattern;

import nth.software.doc.generator.regex.Regex;
import nth.software.doc.generator.regex.Repetition;

public class TagToken implements Token {

	private static final String TAG_INDICATOR = "@";
	private final TagName tagName;
	private final Pattern pattern;

	public TagToken(TagName tagName) {
		this.tagName = tagName;
		pattern = new Regex().caseUnsensativeMode()
				.whiteSpace(Repetition.zeroOrMoreTimes())
				.literal(TAG_INDICATOR, Repetition.oneOrMoreTimes())
				.whiteSpace(Repetition.zeroOrMoreTimes()).literal(tagName.toLowerCase())
				.asPattern();
	}

	public Pattern getPattern() {
		return pattern;
	}

	public TagName getTagName() {
		return tagName;
	}

}

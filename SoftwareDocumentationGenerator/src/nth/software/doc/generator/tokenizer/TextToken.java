package nth.software.doc.generator.tokenizer;

import java.util.regex.Pattern;

import nth.software.doc.generator.regex.Regex;
import nth.software.doc.generator.regex.Repetition;

public class TextToken implements Token {

	private final Pattern pattern;

	public TextToken() {
		pattern = new Regex().anyCharacter(Repetition.oneOrMoreTimes()).asPattern();
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}


}

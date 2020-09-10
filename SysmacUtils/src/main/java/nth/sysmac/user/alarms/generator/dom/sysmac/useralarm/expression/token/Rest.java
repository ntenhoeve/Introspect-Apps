package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class Rest implements TokenRule {

	private static final Regex REGEX_ANY_CHARACTER = new Regex().anyCharacter(Repetition.zeroOrMoreTimes());

	@Override
	public Regex getRegex() {
		return REGEX_ANY_CHARACTER;
	}

	@Override
	public boolean hasValue() {
		return true;
	}

}

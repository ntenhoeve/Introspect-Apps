package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule;

import nth.reflect.util.parser.token.parser.TokenRule;
import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class UnsignedInteger implements TokenRule {

	private static final int LENGTH = Integer.toString(Integer.MAX_VALUE).length();
	private static final Regex REGEX = new Regex().digit(Repetition.minMax(1, LENGTH));

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public boolean hasValue() {
		return true;
	}

}

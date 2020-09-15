package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule;

import nth.reflect.util.parser.token.parser.TokenRule;
import nth.reflect.util.regex.Regex;

public class Equal implements TokenRule {

	private static final Regex REGEX = new Regex().literal("=");

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public boolean hasValue() {
		return false;
	}

}

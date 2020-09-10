package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenRule;

public class WhiteSpace implements TokenRule {

	private static final Regex REGEX = new Regex().whiteSpace();

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public boolean hasValue() {
		return false;
	}

}

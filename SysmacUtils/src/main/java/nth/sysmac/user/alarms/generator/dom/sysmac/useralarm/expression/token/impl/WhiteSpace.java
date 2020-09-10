package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenDefinition;

public class WhiteSpace implements TokenDefinition {

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

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenDefinition;

public class Comma implements TokenDefinition {

	private static final Regex REGEX = new Regex().literal(",");

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public boolean hasValue() {
		return false;
	}

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token;

import nth.reflect.util.regex.Regex;
/**
 * Defines a {@link Token} with {@link Regex}
 * @author nilsth
 *
 */
public interface TokenDefinition {
	public Regex getRegex();

	public boolean hasValue(); 
}

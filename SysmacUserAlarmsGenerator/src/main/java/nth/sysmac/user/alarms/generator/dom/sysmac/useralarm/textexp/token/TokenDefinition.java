package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token;

import nth.reflect.util.regex.Regex;

/**
 * a {@link Token} is expressed in a {@link Regex}
 * 
 * @author nilsth
 *
 */
public interface TokenDefinition {

	public Regex getRegex();
}

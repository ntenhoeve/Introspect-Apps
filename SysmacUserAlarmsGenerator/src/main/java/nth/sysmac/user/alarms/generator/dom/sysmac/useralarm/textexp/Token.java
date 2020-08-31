package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp;

import nth.reflect.util.regex.Regex;

/**
 * A token is a sequence characters with an assigned and thus identified meaning (see all {@link Token} implementations)
 * Tokens are expressed in a {@link Regex}
 * 
 * @author nilsth
 *
 */
public interface Token {

	public Regex getRegex();
}

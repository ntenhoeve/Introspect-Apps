package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp;

import java.util.List;

/**
 * Converts a {@link Token} to an object that represents the information from the token
 * @author nilsth
 *
 * @param <T>
 */
public interface Parser<T> {
	
	public T parse(String expression);
}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token;

/**
 * Converts a {@link Token} to an object that represents the information from the {@link Token}
 * @author nilsth
 *
 * @param <T>
 */
public interface TokenParser<T> extends TokenDefinition {
	
	/**
	 * This method may only be called when {@link TokenDefinition#getRegex()} matches!!!
	 * @param token
	 * @return
	 */
	public T parse(String token);
}

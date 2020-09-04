package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.priority;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenDefinition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenParser;

/**
 * {prio=&gt;value&lt;}
 * <p>
 * {@insert Priority}
 * <p>
 * Note that the first priority takes president. TODO example
 * 
 * @author nilsth
 *
 */
public class PriorityToken implements TokenDefinition, TokenParser<Priority> {

	private static final Regex REGEX = new Regex().ignoreCase().literal("{").whiteSpace(Repetition.zeroOrOneTime())
			.literal("prio").whiteSpace(Repetition.zeroOrMoreTimes()).literal("=")
			.whiteSpace(Repetition.zeroOrMoreTimes()).or(Priority.valuesToRegex())
			.whiteSpace(Repetition.zeroOrOneTime()).literal("}");

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public Priority parse(String token) {
		// TODO Auto-generated method stub
		//TODO also first letter of priority will do
		return null;
	}

}

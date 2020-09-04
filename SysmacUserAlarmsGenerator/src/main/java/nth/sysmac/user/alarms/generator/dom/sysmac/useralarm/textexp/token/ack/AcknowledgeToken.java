package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.ack;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenDefinition;

public class AcknowledgeToken implements TokenDefinition {

	private Regex REGEX = new Regex().ignoreCase().literal("{").whiteSpace(Repetition.zeroOrMoreTimes()).literal("ack")
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal("}");;

	@Override
	public Regex getRegex() {
		return REGEX;
	}

}

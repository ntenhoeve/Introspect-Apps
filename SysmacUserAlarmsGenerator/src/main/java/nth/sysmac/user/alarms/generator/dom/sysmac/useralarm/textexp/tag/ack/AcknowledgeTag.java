package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.ack;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.Tag;

public class AcknowledgeTag implements Tag {

	private Regex REGEX = new Regex().ignoreCase().literal("{").whiteSpace(Repetition.zeroOrMoreTimes()).literal("ack")
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal("}");;

	@Override
	public Regex getRegex() {
		return REGEX;
	}

}

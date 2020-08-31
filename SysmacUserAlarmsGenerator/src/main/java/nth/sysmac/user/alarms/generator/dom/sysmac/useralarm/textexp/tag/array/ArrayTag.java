package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.array;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.Tag;

/**
 * Place holder for an array index number, e.g.:<br>
 * ARRAY[1..5] OF Cm\Vfd		{110U1 skec} Line drive unit {array}
 * @author nilsth
 *
 */
public class ArrayTag implements Tag {

	public static final Regex REGEX = new Regex().ignoreCase().literal("{").whiteSpace(Repetition.zeroOrMoreTimes()).literal("array").whiteSpace(Repetition.zeroOrMoreTimes()).literal("}");

	@Override
	public Regex getRegex() {
		return REGEX;
	}

}

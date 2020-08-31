package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.detail;

import java.util.Optional;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.Parser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.Tag;

/**
 * {details=Press and unlock the e-stop button or safety cord, before reseting
 * the safety system when a wiring problem has been solved.}
 * 
 * @author nilsth
 *
 */
public class DetailsTag implements Tag, Parser<String> {

	private static final Regex REGEX_PREFIX = new Regex().ignoreCase().literal("{")
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal("details=");
	private static final Regex REGEX_TEXT_VALUE = new Regex().anyCharacter(Repetition.oneOrMoreTimes());
	private static final Regex REGEX_SUFFIX = new Regex().literal("}");
	private static final Regex REGEX = new Regex().append(REGEX_PREFIX).group(REGEX_TEXT_VALUE).append(REGEX_SUFFIX);

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public String parse(String tag) {
		Optional<String> details = REGEX.findFirstMatchIn(tag);
		if (details.isPresent()) {
			return details.get().trim();
		} else {
			return "";
		}
	}

}

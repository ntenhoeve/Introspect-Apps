package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.detail;

import java.util.Optional;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenDefinition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenParser;

/**
 * {details=Press and unlock the e-stop button or safety cord, before reseting
 * the safety system when a wiring problem has been solved.}
 * 
 * @author nilsth
 *
 */
public class DetailsToken implements TokenDefinition, TokenParser<String> {

	private static final Regex REGEX_PREFIX = new Regex().ignoreCase().literal("{")
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal("details").whiteSpace(Repetition.zeroOrMoreTimes())
			.literal("=").whiteSpace(Repetition.zeroOrMoreTimes());
	private static final Regex REGEX_TEXT_VALUE = new Regex().anyCharacter(Repetition.oneOrMoreTimes());
	private static final Regex REGEX_SUFFIX = new Regex().literal("}");
	private static final Regex REGEX = new Regex().append(REGEX_PREFIX).group(REGEX_TEXT_VALUE).append(REGEX_SUFFIX);

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public String parse(String token) {
		Optional<String> details = REGEX.findFirstMatchIn(token);
		if (details.isPresent()) {
			return details.get().trim();
		} else {
			return "";
		}
	}

}

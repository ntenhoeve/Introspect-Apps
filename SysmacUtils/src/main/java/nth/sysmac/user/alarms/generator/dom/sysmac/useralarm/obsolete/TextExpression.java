package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete;

import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.TokenService;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.ComponentCodes;

public class TextExpression {

	private static final Regex REGEX_DOUBLE_WHITE_SPACES = new Regex().whiteSpace(Repetition.min(2));
	private static final Regex REGEX_TOKEN = new Regex().literal("{").anyCharacter(Repetition.oneOrMoreTimes()).literal("}");
	private final String message;
	private final ComponentCodes componentCodes;
	private final TokenService tokenService;

	/**
	 * FIXME: either use arrayIndex or use {@link #nextComponentCodes()}
	 * @param expression
	 * @param arrayIndex
	 */
	public TextExpression(String expression, int arrayIndex) {
		tokenService = new TokenService();
		message = createMessage(expression, arrayIndex);
		componentCodes = createComponentCodes(expression, arrayIndex);
	}

	private ComponentCodes createComponentCodes(String expression, int arrayIndex) {
//		ComponentCodes componentCodes = tokenService.createComponentCodes(expression);
//		return componentCodes;
		return null;
	}

	private String createMessage(String expression, int arrayIndex) {
		String result = tokenService.replaceArrayTokens(expression, arrayIndex);
		
		result = tokenService.removeAllTokensFrom(expression);

		verifyNoMoreTokensPresent(result);
		
		result = result.trim();

		result = REGEX_DOUBLE_WHITE_SPACES.removeAllFrom(result);

		return result;
	}

	private void verifyNoMoreTokensPresent(String expression) {
		if (REGEX_TOKEN.hasMatchIn(expression)) {
			throw new RuntimeException("Could not resolve token(s): "+REGEX_TOKEN.findMatches(expression));
		}
	}

	public String getMessage() {
		List<String> visibleComponentCodes = componentCodes.findVisible();
		if (visibleComponentCodes.isEmpty()) {
			return message;
		} else {
			return String.join(",", visibleComponentCodes) +" "+ message;
		}
	}

	public void nextComponentCodes() {
		componentCodes.next();
	}

}

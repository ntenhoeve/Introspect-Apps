package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp;

import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.TagService;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.ComponentCodes;

public class TextExpression {

	private static final Regex REGEX_DOUBLE_WHITE_SPACES = new Regex().whiteSpace(Repetition.min(2));
	private static final Regex REGEX_TAG = new Regex().literal("{").anyCharacter(Repetition.oneOrMoreTimes()).literal("}");
	private final String message;
	private final ComponentCodes componentCodes;
	private final TagService tagService;

	/**
	 * FIXME: either use arrayIndex or use {@link #nextComponentCodes()}
	 * @param expression
	 * @param arrayIndex
	 */
	public TextExpression(String expression, int arrayIndex) {
		tagService = new TagService();
		message = createMessage(expression, arrayIndex);
		componentCodes = createComponentCodes(expression, arrayIndex);
	}

	private ComponentCodes createComponentCodes(String expression, int arrayIndex) {
		ComponentCodes componentCodes = tagService.createComponentCodes(expression);
		return componentCodes;
	}

	private String createMessage(String expression, int arrayIndex) {
		String result = tagService.replaceArrayTags(expression, arrayIndex);
		
		result = tagService.removeAllTagsFrom(expression);

		verifyNoMoreTagsPresent(result);
		
		result = result.trim();

		result = REGEX_DOUBLE_WHITE_SPACES.removeAllFrom(result);

		return result;
	}

	private void verifyNoMoreTagsPresent(String expression) {
		if (REGEX_TAG.hasMatchIn(expression)) {
			throw new RuntimeException("Could not resolve tag(s): "+REGEX_TAG.findMatches(expression));
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

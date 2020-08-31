package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.Parser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.Tag;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule.ParsableSkipRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule.SkipRules;

public class ComponentCodeTagWithoutBrackets implements Tag, Parser<ComponentCode> {

	public static final Regex REGEX_SPACE_SEPARATOR = new Regex().whiteSpace(Repetition.zeroOrOneTime());
	public static final Regex REGEX_COMMA_SEPARATOR = new Regex().literal(",", Repetition.zeroOrOneTime());
	public static final Regex REGEX_DASH_SEPARATOR = new Regex().literal("-", Repetition.zeroOrOneTime());
	public static final Regex REGEX_SLASH_SEPARATOR = new Regex().literal("/", Repetition.zeroOrOneTime());
	public static final Regex REGEX_BACK_SLASH_SEPARATOR = new Regex().literal("\\", Repetition.zeroOrOneTime());
	public static final List<Regex> REGEX_SEPARATORS = Arrays.asList(REGEX_SPACE_SEPARATOR, REGEX_COMMA_SEPARATOR,
			REGEX_DASH_SEPARATOR, REGEX_SLASH_SEPARATOR, REGEX_BACK_SLASH_SEPARATOR);
	public static final Regex REGEX_SKIP_RULES = new Regex().literal("{").whiteSpace(Repetition.oneOrMoreTimes()).whiteSpace(Repetition.zeroOrOneTime()).literal("}");
	public static final Regex REGEX = new Regex().ignoreCase() .append(ComponentCode.REGEX).noneCapturingGroup(REGEX_SKIP_RULES,Repetition.zeroOrOneTime()).or(REGEX_SEPARATORS);
	private static final Regex REGEX_FIND_RULES =  new Regex().ignoreCase() .append(ComponentCode.REGEX).group(REGEX_SKIP_RULES,Repetition.zeroOrOneTime()).or(REGEX_SEPARATORS);;
	

	@Override
	public Regex getRegex() {
		return ComponentCode.REGEX;
	}

	/**
	 * This method should only be called when {@link #REGEX} matches componentCode
	 */
	@Override
	public ComponentCode parse(String tag) {
		int page = getPage(tag);
		char letter = getLetter(tag);
		int column = getColumn(tag);
		SkipRules rules = getSkipRules(tag);
		ComponentCode componentCode = new ComponentCode(page, letter, column, rules);
		return componentCode;
	}

	private SkipRules getSkipRules(String tag) {
		String skipRulesString = REGEX_FIND_RULES.findFirstMatchIn(tag).get();
		SkipRules skipRules = ParsableSkipRules.parse(skipRulesString);
		return skipRules;
	}

	private int getPage(String tag) {
		String value = ComponentCode.REGEX_FIND_PAGE.findFirstMatchIn(tag).get();
		int page = Integer.valueOf(value);
		return page;
	}

	private char getLetter(String tag) {
		String value = ComponentCode.REGEX_FIND_LETTER.findFirstMatchIn(tag).get();
		char ch = value.charAt(0);
		return ch;
	}

	private int getColumn(String tag) {
		String value = ComponentCode.REGEX_FIND_COLUMN.findFirstMatchIn(tag).get();
		int column = Integer.valueOf(value);
		return column;
	}
}

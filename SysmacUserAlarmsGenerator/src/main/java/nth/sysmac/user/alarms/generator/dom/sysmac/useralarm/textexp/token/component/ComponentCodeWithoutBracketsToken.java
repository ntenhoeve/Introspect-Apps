package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenDefinition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRuleTokens;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRules;

public class ComponentCodeWithoutBracketsToken implements TokenDefinition, TokenParser<ComponentCode> {

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
	public ComponentCode parse(String token) {
		int page = getPage(token);
		char letter = getLetter(token);
		int column = getColumn(token);
		SkipRules rules = getSkipRules(token);
		ComponentCode componentCode = new ComponentCode(page, letter, column, rules);
		return componentCode;
	}

	private SkipRules getSkipRules(String token) {
		String skipRulesString = REGEX_FIND_RULES.findFirstMatchIn(token).get();
		SkipRules skipRules = SkipRuleTokens.parse(skipRulesString);
		return skipRules;
	}

	private int getPage(String token) {
		String value = ComponentCode.REGEX_FIND_PAGE.findFirstMatchIn(token).get();
		int page = Integer.valueOf(value);
		return page;
	}

	private char getLetter(String token) {
		String value = ComponentCode.REGEX_FIND_LETTER.findFirstMatchIn(token).get();
		char ch = value.charAt(0);
		return ch;
	}

	private int getColumn(String token) {
		String value = ComponentCode.REGEX_FIND_COLUMN.findFirstMatchIn(token).get();
		int column = Integer.valueOf(value);
		return column;
	}
}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component;

import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenDefinition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRuleTokens;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRules;

/**
 * Almost all alarm messages start with one or component codes so that it can
 * easily be located in the electric schematic or in the field.<br>
 * The format of a component code is: 30M3, where:
 * <ul>
 * <li>30 = page number</li>
 * <li>M = Indicator of type of component, e.g.: F=Fuse, Q=Motor protector,
 * S=Switch, M=Motor, K=Relay, U=electronic device</li>
 * <li>3 = Column number</li>
 * </ul>
 * A {@link ComponentCodeWithBracketsToken} is a
 * {@link ComponentCodeWithBracketsToken} between curly brackets {}. In example:
 * <p>
 * <table border="2">
 * <tr>
 * <th colspan=3>Data Type example:</th>
 * </tr>
 * <tr>
 * <th align="left">Name</th>
 * <th align="left">Base Type</th>
 * <th align="left">Comment</th>
 * </tr>
 * <tr>
 * <td>sEvent</td>
 * <td>STRUCT</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>- AirPressure</td>
 * <td>BOOL</td>
 * <td>{110S3} Line tensioner out of position</td>
 * </tr>
 * <tr>
 * <th colspan=3>Results in: 110S3 Line tensioner out of position</th>
 * </tr>
 * </table>
 * 
 * 
 * TODO example {110S2 skuc skpc=110.4 skp=111} 110S2 110S6 110S8 112S2
 * 
 * <p>
 * 
 * @author nilsth
 *
 */
public class ComponentCodeWithBracketsToken implements TokenDefinition, TokenParser<ComponentCode> {

	private static final Regex REGEX_PREFIX = new Regex().literal("{").whiteSpace(Repetition.zeroOrMoreTimes());
	private static final Regex REGEX_SUFFIX = new Regex().whiteSpace(Repetition.zeroOrMoreTimes()).literal("}");
	public static final Regex REGEX = new Regex().ignoreCase().append(REGEX_PREFIX).append(ComponentCode.REGEX)
			.whiteSpace(Repetition.zeroOrMoreTimes()).or(SkipRuleTokens.ALL_REGEX, Repetition.zeroOrMoreTimes())
			.append(REGEX_SUFFIX);
	private static final Regex REGEX_FIND_PAGE = new Regex().ignoreCase().append(REGEX_PREFIX)
			.append(ComponentCode.REGEX_FIND_PAGE).whiteSpace(Repetition.zeroOrMoreTimes())
			.or(SkipRuleTokens.ALL_REGEX, Repetition.zeroOrMoreTimes()).append(REGEX_SUFFIX);
	private static final Regex REGEX_FIND_LETTER = new Regex().ignoreCase().append(REGEX_PREFIX)
			.append(ComponentCode.REGEX_FIND_LETTER).whiteSpace(Repetition.zeroOrMoreTimes())
			.or(SkipRuleTokens.ALL_REGEX, Repetition.zeroOrMoreTimes()).append(REGEX_SUFFIX);
	private static final Regex REGEX_FIND_COLUMN = new Regex().ignoreCase().append(REGEX_PREFIX)
			.append(ComponentCode.REGEX_FIND_COLUMN).whiteSpace(Repetition.zeroOrMoreTimes())
			.or(SkipRuleTokens.ALL_REGEX, Repetition.zeroOrMoreTimes()).append(REGEX_SUFFIX);
	private static final Regex REGEX_FIND_RULES = new Regex().append(REGEX_PREFIX)
			.append(ComponentCode.REGEX).group(new Regex().ignoreCase().or(SkipRuleTokens.ALL_REGEX,Repetition.zeroOrMoreTimes()))
			.append(REGEX_SUFFIX);
			
			//TODO simplify rules: get everything between brackets and throw error if unparsable rules are found (also in junit test!)

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public ComponentCode parse(String token) {
		int page = getPage(token);
		char letter = getLetter(token);
		int column = getColumn(token);

		SkipRules skipRules = getSkipRules(token);
		ComponentCode componentCode = new ComponentCode(page, letter, column, skipRules);
		return componentCode;
	}

	private SkipRules getSkipRules(String token) {
		List<String> groups = REGEX_FIND_RULES.findGroups(token);
		if (groups.size() == 2) {
			String skipRulesString = groups.get(1);
			SkipRules skipRules = SkipRuleTokens.parse(skipRulesString);
			return skipRules;
		} else {
			return new SkipRules();
		}
	}

	private int getPage(String token) {
		String value = REGEX_FIND_PAGE.findGroups(token).get(1);
		int page = Integer.valueOf(value);
		return page;
	}

	private char getLetter(String token) {
		String value = REGEX_FIND_LETTER.findGroups(token).get(1);
		char ch = value.charAt(0);
		return ch;
	}

	private int getColumn(String token) {
		String value = REGEX_FIND_COLUMN.findGroups(token).get(1);
		int column = Integer.valueOf(value);
		return column;
	}

}

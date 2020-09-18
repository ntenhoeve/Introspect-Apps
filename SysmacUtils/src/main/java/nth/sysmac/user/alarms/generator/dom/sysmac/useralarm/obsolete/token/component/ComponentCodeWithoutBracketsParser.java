package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.Token;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.SkipRules;

/**
 * <h3>Component codes without curly brackets</h3>
 * 
 * A {@link ComponentCodeWithBrackets} is a {@link Token} containing a {@link ComponentCodeNode}
 * Optionally followed with curly brackets {} containing {@link SkipRules}. In example:
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
 * <th colspan=3>MatchResults in: 110S3 Line tensioner out of position</th>
 * </tr>
 * </table>
 * 
 * 
 * TODO example {110S2 sc=u,110.4 sp=111} 110S2 110S6 110S8 112S2
 * 
 * <p>
 * 
 * @author nilsth
 *
 */
public class ComponentCodeWithoutBracketsParser {//implements TokenParser<ComponentCodeNode> {
//
//	public static final Regex REGEX_SPACE_SEPARATOR = new Regex().whiteSpace(Repetition.zeroOrOneTime());
//	public static final Regex REGEX_COMMA_SEPARATOR = new Regex().literal(",", Repetition.zeroOrOneTime());
//	public static final Regex REGEX_DASH_SEPARATOR = new Regex().literal("-", Repetition.zeroOrOneTime());
//	public static final Regex REGEX_SLASH_SEPARATOR = new Regex().literal("/", Repetition.zeroOrOneTime());
//	public static final Regex REGEX_BACK_SLASH_SEPARATOR = new Regex().literal("\\", Repetition.zeroOrOneTime());
//	public static final List<Regex> REGEX_SEPARATORS = Arrays.asList(REGEX_SPACE_SEPARATOR, REGEX_COMMA_SEPARATOR,
//			REGEX_DASH_SEPARATOR, REGEX_SLASH_SEPARATOR, REGEX_BACK_SLASH_SEPARATOR);
//	public static final Regex REGEX_SKIP_RULES = new Regex().literal("{").whiteSpace(Repetition.oneOrMoreTimes()).whiteSpace(Repetition.zeroOrOneTime()).literal("}");
//	public static final Regex REGEX = new Regex().ignoreCase() .append(ComponentCodeNode.REGEX).noneCapturingGroup(REGEX_SKIP_RULES,Repetition.zeroOrOneTime()).or(REGEX_SEPARATORS);
//	private static final Regex REGEX_FIND_RULES =  new Regex().ignoreCase() .append(ComponentCodeNode.REGEX).group(REGEX_SKIP_RULES,Repetition.zeroOrOneTime()).or(REGEX_SEPARATORS);
//	private final SkipRuleParsers skipRuleParsers;
//	
//	public ComponentCodeWithoutBracketsParser() {
//		skipRuleParsers = new SkipRuleParsers();
//	}
//
//	@Override
//	public Regex getRegex() {
//		return ComponentCodeNode.REGEX;
//	}
//
//	@Override
//	public ComponentCodeNode parse(String token) {
//		int page = getPage(token);
//		char letter = getLetter(token);
//		int column = getColumn(token);
//		SkipRules rules = parseSkipRules(token);
//		ComponentCodeNode componentCodeNode = new ComponentCodeNode(page, letter, column, rules);
//		return componentCodeNode;
//	}
//
//	private SkipRules parseSkipRules(String token) {
//		List<String> groups = REGEX_FIND_RULES.findGroups(token);
//		if (groups.size() == 2) {
//			String expression = groups.get(1);
//			SkipRules skipRules = new SkipRules( );
//			skipRules.addAll(skipRuleParsers.parse(expression));
//			return skipRules;
//		} else {
//			return new SkipRules();
//		}
//	}
//
//	private int getPage(String token) {
//		String value = ComponentCodeNode.REGEX_FIND_PAGE.findFirstMatchIn(token).get();
//		int page = Integer.valueOf(value);
//		return page;
//	}
//
//	private char getLetter(String token) {
//		String value = ComponentCodeNode.REGEX_FIND_LETTER.findFirstMatchIn(token).get();
//		char ch = value.charAt(0);
//		return ch;
//	}
//
//	private int getColumn(String token) {
//		String value = ComponentCodeNode.REGEX_FIND_COLUMN.findFirstMatchIn(token).get();
//		int column = Integer.valueOf(value);
//		return column;
//	}
}

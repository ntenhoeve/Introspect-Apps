package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.Parser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.Tag;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule.ParsableSkipRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule.SkipRules;

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
 * A {@link ComponentCodeTagWithBrackets}Tag is a {@link ComponentCodeTagWithBrackets} between curly
 * brackets {}. In example:
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
public class ComponentCodeTagWithBrackets implements Tag, Parser<ComponentCode> {

	private static final Regex REGEX_PREFIX = new Regex().literal("{").whiteSpace(Repetition.zeroOrMoreTimes());
	private static final Regex REGEX_SUFFIX = new Regex().whiteSpace(Repetition.zeroOrMoreTimes()).literal("}");
	public static final Regex REGEX = new Regex().ignoreCase().append(REGEX_PREFIX).append(ComponentCode.REGEX)
			.or(ParsableSkipRules.ALL_REGEX).append(REGEX_SUFFIX);
	private static final Regex REGEX_FIND_PAGE = new Regex().ignoreCase().append(REGEX_PREFIX)
			.append(ComponentCode.REGEX_FIND_PAGE).or(ParsableSkipRules.ALL_REGEX).append(REGEX_SUFFIX);
	private static final Regex REGEX_FIND_LETTER = new Regex().ignoreCase().append(REGEX_PREFIX)
			.append(ComponentCode.REGEX_FIND_LETTER).or(ParsableSkipRules.ALL_REGEX).append(REGEX_SUFFIX);
	private static final Regex REGEX_FIND_COLUMN = new Regex().ignoreCase().append(REGEX_PREFIX)
			.append(ComponentCode.REGEX_FIND_COLUMN).or(ParsableSkipRules.ALL_REGEX).append(REGEX_SUFFIX);
	private static final Regex REGEX_FIND_RULES = new Regex().append(REGEX_PREFIX)
			.append(ComponentCode.REGEX_FIND_COLUMN).group(new Regex().ignoreCase().or(ParsableSkipRules.ALL_REGEX))
			.append(REGEX_SUFFIX);

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public ComponentCode parse(String tag) {
		int page = getPage(tag);
		char letter = getLetter(tag);
		int column = getColumn(tag);

		SkipRules skipRules = getSkipRules(tag);
		ComponentCode componentCode = new ComponentCode(page, letter, column, skipRules);
		return componentCode;
	}

	private SkipRules getSkipRules(String tag) {
		String skipRulesString = REGEX_FIND_RULES.findFirstMatchIn(tag).get();
		SkipRules skipRules = ParsableSkipRules.parse(skipRulesString);
		return skipRules;
	}

	private int getPage(String tag) {
		String value = REGEX_FIND_PAGE.findFirstMatchIn(tag).get();
		int page = Integer.valueOf(value);
		return page;
	}

	private char getLetter(String tag) {
		String value = REGEX_FIND_LETTER.findFirstMatchIn(tag).get();
		char ch = value.charAt(0);
		return ch;
	}

	private int getColumn(String tag) {
		String value = REGEX_FIND_COLUMN.findFirstMatchIn(tag).get();
		int column = Integer.valueOf(value);
		return column;
	}

}

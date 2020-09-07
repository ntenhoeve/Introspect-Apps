package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRuleParser;

/**
 * <h3>Skipping columns:</h3>
 * <ul>
 * <li>sc=e: skips even columns (of all pages)</li>
 * <li>sc=u: skips un-even columns (of all pages)</li>
 * <li>sc=2: skips column 2 (of all pages)</li>
 * <li>sc=3-5: skips columns 3 until 5 (of all pages)</li>
 * <li>sc=30.2: skips column 2 of page 30</li>
 * <li>sc=30.2-31.5: skips column 2 of page 30 until column 5 of page 31</li>
 * </ul>
 * You can combine the rules above by separating them with a comma, e.g.:
 * <ul>
 * <li>sc=e,5: skips even columns and column 5 (of all pages)</li>
 * <li>sc=2,4: skips column 2 and column 4 (of all pages)</li>
 * <li>sc=2-4,8: skips column 2-4 and column 8 (of all pages)</li>
 * <li>sc=e,3-7,30.2-31.5: skips even columns, columns 3-7, and column 2 of page 30 until column 5 of page 31</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public class SkipColumnParser implements SkipRuleParser {

	private static final String EQUALS = "=";
	private static final String SKIP_COLUMN_ABBREVIATION = "sc";
	private static final Regex REGEX_VALUE_EVEN = new Regex().ignoreCase().literal("e");
	private static final Regex REGEX_VALUE_UNEVEN = new Regex().ignoreCase().literal("u");
	private static final List<Regex> REGEX_VALUES = Arrays.asList(REGEX_VALUE_EVEN, REGEX_VALUE_UNEVEN);
	private static final Regex REGEX = new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes()).literal(SKIP_COLUMN_ABBREVIATION)
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal(EQUALS).whiteSpace(Repetition.zeroOrMoreTimes())
			.or(REGEX_VALUES);
	private static final Regex REGEX_FIND_EVEN = new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes()).literal(SKIP_COLUMN_ABBREVIATION)
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal(EQUALS).whiteSpace(Repetition.zeroOrMoreTimes())
			.append(REGEX_VALUE_EVEN);
	private static final Regex REGEX_FIND_UNEVEN = new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes()).literal(SKIP_COLUMN_ABBREVIATION)
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal(EQUALS).whiteSpace(Repetition.zeroOrMoreTimes())
			.append(REGEX_VALUE_UNEVEN);

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public List<SkipRule> parse(String token) {
		
		List<SkipRule> skipRules=new ArrayList<>();
		if (REGEX_FIND_EVEN.hasMatchIn(token)) {
			SkipEvenColumnRule skipEvenColumnRule = new SkipEvenColumnRule();
			skipRules.add(skipEvenColumnRule);
		} else if (REGEX_FIND_UNEVEN.hasMatchIn(token)) {
			SkipUnevenColumnRule skipUnevenColumnRule = new SkipUnevenColumnRule();
			skipRules.add(skipUnevenColumnRule);			
		}

		throwErrorWhenNoRulesAreFound(skipRules);
		
		throwErrorWhenHasEvenAndUnevenRule(skipRules);
		
		return skipRules;
	}

	private void throwErrorWhenHasEvenAndUnevenRule(List<SkipRule> skipRules) {
		boolean hasSkipEvenRule = skipRules.stream().anyMatch(r->r instanceof SkipEvenColumnRule);
		boolean hasSkipUnevenRules = skipRules.stream().anyMatch(r->r instanceof SkipUnevenColumnRule);

		if (hasSkipEvenRule && hasSkipUnevenRules) {
			throw new RuntimeException("Skip rules may not contain both a: " + SkipEvenColumnRule.class.getSimpleName()
					+ " and a: " + SkipUnevenColumnRule.class.getSimpleName() + " rule.");
		}
	}

	private void throwErrorWhenNoRulesAreFound(List<SkipRule> skipRules) {
		if (skipRules.isEmpty()) {
			throw new RuntimeException("No valid page skip rules found");
		}
	}

	
}

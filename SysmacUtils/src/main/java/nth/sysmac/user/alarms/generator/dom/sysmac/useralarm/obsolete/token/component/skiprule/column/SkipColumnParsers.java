package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.DelegatingSkipRuleParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRuleParser;

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
 * <li>sc=e,3-7,30.2-31.5: skips even columns, columns 3-7, and column 2 of page
 * 30 until column 5 of page 31</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public class SkipColumnParsers extends DelegatingSkipRuleParser {

	private static final String EQUALS = "=";
	private static final String SKIP_COLUMN_ABBREVIATION = "sc";
	private static final Regex REGEX = new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal(SKIP_COLUMN_ABBREVIATION).whiteSpace(Repetition.zeroOrMoreTimes()).literal(EQUALS)
			.whiteSpace(Repetition.zeroOrMoreTimes()).group(new Regex().anyCharacter(Repetition.oneOrMoreTimes()));

	@Override
	protected List<SkipRuleParser> createSkipRuleParsers() {
		List<SkipRuleParser> skipRuleParsers = new ArrayList<>();
		skipRuleParsers.add(new SkipEvenColumnParser());
		skipRuleParsers.add(new SkipUnevenColumnParser());
		return skipRuleParsers;
	}

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public List<SkipRule> parse(String expression) {

		List<SkipRule> skipRules = super.parse(expression);

		throwErrorWhenNoRulesAreFound(skipRules);

		throwErrorWhenHasEvenAndUnevenRule(skipRules);

		return skipRules;
	}

	@Override
	protected List<String> split(String expression) {
		List<String> groups = REGEX.findGroups(expression);
		if (groups.size() == 2) {
			String valuesExpression = groups.get(1);
			List<String> expressions = Arrays.asList(valuesExpression.split(","));
			return expressions;
		} else {
			throw new RuntimeException("No valid page skip rule values found in: " + expression);
		}
	}

	private void throwErrorWhenHasEvenAndUnevenRule(List<SkipRule> skipRules) {
		boolean hasSkipEvenRule = skipRules.stream().anyMatch(r -> r instanceof SkipEvenColumnRule);
		boolean hasSkipUnevenRules = skipRules.stream().anyMatch(r -> r instanceof SkipUnevenColumnRule);

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

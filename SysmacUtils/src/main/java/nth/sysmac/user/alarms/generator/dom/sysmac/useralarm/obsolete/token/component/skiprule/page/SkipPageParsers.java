package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.DelegatingSkipRuleParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRuleParser;

/**
 * <h3>Skipping pages:</h3>
 * <ul>
 * <li>sp=e: skips even pages</li>
 * <li>sp=u: skips un-even pages</li>
 * <li>sp=20: skips page 20</li>
 * <li>sp=20-30: skips pages 20 until 30</li>
 * </ul>
 * You can combine the rules above by separating them with a comma, e.g.:
 * <ul>
 * <li>sp=e,21: skips even pages and page 21</li>
 * <li>sp=20,30: skips page 20 and page 30</li>
 * <li>sp=20-30,33: skips page 20-30 and page 33</li>
 * <li>sp=e,20-30,33,35: skips even pages, page 20-30 and page 33 and page
 * 35</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public class SkipPageParsers extends DelegatingSkipRuleParser {

	private static final String EQUALS = "=";
	private static final String SKIP_PAGE_ABBREVIATION = "sp";
	private static final Regex REGEX = new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal(SKIP_PAGE_ABBREVIATION).whiteSpace(Repetition.zeroOrMoreTimes()).literal(EQUALS)
			.whiteSpace(Repetition.zeroOrMoreTimes()).group(new Regex().anyCharacter(Repetition.oneOrMoreTimes()));

	@Override
	protected List<SkipRuleParser> createSkipRuleParsers() {
		List<SkipRuleParser> skipRuleParsers = new ArrayList<>();
		skipRuleParsers.add(new SkipEvenPageParser());
		skipRuleParsers.add(new SkipUnevenPageParser());
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
		boolean hasSkipEvenRule = skipRules.stream().anyMatch(r -> r instanceof SkipEvenPageRule);
		boolean hasSkipUnevenRules = skipRules.stream().anyMatch(r -> r instanceof SkipUnevenPageRule);

		if (hasSkipEvenRule && hasSkipUnevenRules) {
			throw new RuntimeException("Skip rules may not contain both a: " + SkipEvenPageRule.class.getSimpleName()
					+ " and a: " + SkipUnevenPageRule.class.getSimpleName() + " rule.");
		}
	}

	private void throwErrorWhenNoRulesAreFound(List<SkipRule> skipRules) {
		if (skipRules.isEmpty()) {
			throw new RuntimeException("No valid page skip rules found");
		}
	}

}

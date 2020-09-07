package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRuleParser;

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
 * <li>sp=e,20-30,33,35: skips even pages, page 20-30 and page 33 and page 35</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public class SkipPageParser implements SkipRuleParser {

	private static final String EQUALS = "=";
	private static final String SKIP_PAGE_ABBREVIATION = "sp";
	private static final Regex REGEX_VALUE_EVEN = new Regex().ignoreCase().literal("e");
	private static final Regex REGEX_VALUE_UNEVEN = new Regex().ignoreCase().literal("u");
	private static final List<Regex> REGEX_VALUES = Arrays.asList(REGEX_VALUE_EVEN, REGEX_VALUE_UNEVEN);
	private static final Regex REGEX = new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal(SKIP_PAGE_ABBREVIATION).whiteSpace(Repetition.zeroOrMoreTimes()).literal(EQUALS)
			.whiteSpace(Repetition.zeroOrMoreTimes()).or(REGEX_VALUES);
	private static final Regex REGEX_FIND_EVEN = new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal(SKIP_PAGE_ABBREVIATION).whiteSpace(Repetition.zeroOrMoreTimes()).literal(EQUALS)
			.whiteSpace(Repetition.zeroOrMoreTimes()).append(REGEX_VALUE_EVEN);
	private static final Regex REGEX_FIND_UNEVEN = new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal(SKIP_PAGE_ABBREVIATION).whiteSpace(Repetition.zeroOrMoreTimes()).literal(EQUALS)
			.whiteSpace(Repetition.zeroOrMoreTimes()).append(REGEX_VALUE_UNEVEN);

	@Override
	public Regex getRegex() {
		return REGEX;
	}

	@Override
	public List<SkipRule> parse(String token) {

		boolean hasSkipEvenRule = REGEX_FIND_EVEN.hasMatchIn(token);
		boolean hasSkipUnevenRules = REGEX_FIND_UNEVEN.hasMatchIn(token);

		List<SkipRule> skipRules = new ArrayList<>();

		if (hasSkipEvenRule) {
			SkipEvenPageRule skipEvenPageRule = new SkipEvenPageRule();
			skipRules.add(skipEvenPageRule);
		} else if (hasSkipUnevenRules) {
			SkipUnevenPageRule skipUnevenPageRule = new SkipUnevenPageRule();
			skipRules.add(skipUnevenPageRule);
		}

		throwErrorWhenHasEvenAndUnevenRule(skipRules);

		throwErrorWhenNoRulesAreFound(skipRules);

		return skipRules;
	}

	private void throwErrorWhenNoRulesAreFound(List<SkipRule> skipRules) {
		if (skipRules.isEmpty()) {
			throw new RuntimeException("No valid page skip rules found");
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

}

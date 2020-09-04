package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRuleToken;

public class SkipPageToken implements SkipRuleToken {

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
		List<SkipRule> skipRules = new ArrayList<>();
		if (REGEX_FIND_EVEN.hasMatchIn(token)) {
			SkipEvenPageRule skipEvenPageRule = new SkipEvenPageRule();
			skipRules.add(skipEvenPageRule);
		} else if (REGEX_FIND_UNEVEN.hasMatchIn(token)) {
			SkipUnEvenPageRule skipUnEvenPageRule = new SkipUnEvenPageRule();
			skipRules.add(skipUnEvenPageRule);
		}
		return skipRules;
	}

}

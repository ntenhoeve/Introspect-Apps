package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.page;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRuleParser;

public class SkipEvenPageParser implements SkipRuleParser {

	private static final String EVEN_ABBREVIATION = "e";

	@Override
	public Regex getRegex() {
		return new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes()).literal(EVEN_ABBREVIATION)
				.whiteSpace(Repetition.zeroOrMoreTimes());
	}

	@Override
	public List<SkipRule> parse(String token) {
		SkipEvenPageRule skipEvenPageRule = new SkipEvenPageRule();
		List<SkipRule> skipRules = Arrays.asList(skipEvenPageRule);
		return skipRules;
	}

}

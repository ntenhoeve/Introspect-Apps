package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRuleParser;

public class SkipEvenColumnParser implements SkipRuleParser {

	private static final String EVEN_ABBREVIATION = "e";

	@Override
	public Regex getRegex() {
		return new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes()).literal(EVEN_ABBREVIATION)
				.whiteSpace(Repetition.zeroOrMoreTimes());
	}

	@Override
	public List<SkipRule> parse(String token) {
		SkipEvenColumnRule skipEvenColumnRule = new SkipEvenColumnRule();
		List<SkipRule> skipRules = Arrays.asList(skipEvenColumnRule);
		return skipRules;
	}

}

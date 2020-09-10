package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.page;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRuleParser;

public class SkipUnevenPageParser implements SkipRuleParser {

	private static final String UNEVEN_ABBREVIATION = "u";

	@Override
	public Regex getRegex() {
		return new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes()).literal(UNEVEN_ABBREVIATION)
				.whiteSpace(Repetition.zeroOrMoreTimes());
	}

	@Override
	public List<SkipRule> parse(String token) {
		SkipUnevenPageRule skipUnevenPageRule = new SkipUnevenPageRule();
		List<SkipRule> skipRules = Arrays.asList(skipUnevenPageRule);
		return skipRules;
	}

}

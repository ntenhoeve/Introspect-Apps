package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRuleParser;

public class SkipUnevenColumnParser implements SkipRuleParser {

	private static final String UNEVEN_ABBREVIATION = "u";

	@Override
	public Regex getRegex() {
		return new Regex().ignoreCase().whiteSpace(Repetition.zeroOrMoreTimes()).literal(UNEVEN_ABBREVIATION)
				.whiteSpace(Repetition.zeroOrMoreTimes());
	}

	@Override
	public List<SkipRule> parse(String token) {
		SkipUnevenColumnRule skipUnevenColumnRule = new SkipUnevenColumnRule();
		List<SkipRule> skipRules = Arrays.asList(skipUnevenColumnRule);
		return skipRules;
	}

}

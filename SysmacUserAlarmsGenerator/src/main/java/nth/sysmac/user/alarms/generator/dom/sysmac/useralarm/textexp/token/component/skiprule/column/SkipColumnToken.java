package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRuleToken;

public class SkipColumnToken implements SkipRuleToken {

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
			SkipUnEvenColumnRule skipUnEvenColumnRule = new SkipUnEvenColumnRule();
			skipRules.add(skipUnEvenColumnRule);			
		}
		return skipRules;
	}

}

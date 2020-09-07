package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.SysmacUserAlarmsGenerator;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column.SkipColumnParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page.SkipPageParser;

public class SkipRuleParsers {

	public static List<SkipRuleParser> ALL;
	public static List<Regex> ALL_REGEX;
	private static Regex REGEX_WHITESPACE = new Regex().whiteSpace(Repetition.zeroOrMoreTimes());
	static {
		List<SkipRuleParser> skipRuleParsers = new ArrayList<>();
		skipRuleParsers.add(new SkipColumnParser());
		skipRuleParsers.add(new SkipPageParser());
		ALL = Collections.unmodifiableList(skipRuleParsers);
		ALL_REGEX = ALL.stream().map(r -> r.getRegex()).collect(Collectors.toUnmodifiableList());
	}

	public static SkipRules parse(String skipRulesString) {
		SkipRules result = new SkipRules();
		for (SkipRuleParser token : ALL) {
			Regex regex = token.getRegex();
			List<String> rules = regex.findMatches(skipRulesString);
			for (String rule : rules) {
				List<SkipRule> newRules = token.parse(rule);
				result.addAll(newRules);
			}
			skipRulesString = regex.removeAllFrom(skipRulesString);
		}
		if (containsInvalidRules(skipRulesString)) {
			throw new RuntimeException("Invalid skip rule(s): " + skipRulesString);
		}
		return result;
	}

	private static boolean containsInvalidRules(String skipRulesString) {
		boolean containsUnparsedText = REGEX_WHITESPACE.removeAllFrom(skipRulesString).length() > 0;
		return containsUnparsedText;
	}

}

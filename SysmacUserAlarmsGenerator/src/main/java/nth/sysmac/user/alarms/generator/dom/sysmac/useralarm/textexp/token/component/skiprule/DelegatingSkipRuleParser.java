package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule;

import java.util.ArrayList;
import java.util.List;

public abstract class DelegatingSkipRuleParser implements SkipRuleParser {

	private final List<SkipRuleParser> skipRuleParsers;

	public DelegatingSkipRuleParser() {
		skipRuleParsers = createSkipRuleParsers();
	}

	protected abstract List<SkipRuleParser> createSkipRuleParsers();

	public List<SkipRule> parse(String expression) {
		List<SkipRule> result = new ArrayList<>();

		List<String> subExpressions = split(expression);
		for (String subExpression : subExpressions) {
			SkipRuleParser parser =findParserFor(subExpression);
					List<SkipRule> newRules = parser.parse(subExpression);
					result.addAll(newRules);
		}
		return result;

	}

	protected abstract List<String> split(String expression);

	private SkipRuleParser findParserFor(String expression) {
		return skipRuleParsers.stream().filter(p -> p.getRegex().toMatcher(expression).matches()).findAny()
				.orElseThrow(() -> new RuntimeException("Invalid skip rule expression: " + expression));
	}



}

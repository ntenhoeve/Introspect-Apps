package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.testexpression;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.ComponentCodeSkipRule;

public class SkipRuleTestExpressions {

	private final String ruleAbbreviation;
	private final HashMap<String, ComponentCodeSkipRule> valuesAndRules;

	public SkipRuleTestExpressions(String ruleAbbreviation) {
		this.ruleAbbreviation = ruleAbbreviation;
		this.valuesAndRules = new HashMap<>();
	}

	public void add(String value, ComponentCodeSkipRule componentCodeSkipRule) {
		valuesAndRules.put(value, componentCodeSkipRule);
	}

	public Map<String, List<ComponentCodeSkipRule>> createExpressionsAndRules(StringConverters stringCoverters) {

		Map<String, List<ComponentCodeSkipRule>> singleExpressionsAndRules = createSingleExpressionsAndRules(stringCoverters);

		Map<String, List<ComponentCodeSkipRule>> combinedExpressionsAndRules = createCombinedExpressionsAndRules(stringCoverters);

		Map<String, List<ComponentCodeSkipRule>> allExpressionsAndRules = new HashMap<>();
		allExpressionsAndRules.putAll(singleExpressionsAndRules);
		allExpressionsAndRules.putAll(combinedExpressionsAndRules);

		return allExpressionsAndRules;
	}

	private Map<String, List<ComponentCodeSkipRule>> createCombinedExpressionsAndRules(StringConverters stringConverters) {
		Map<String, List<ComponentCodeSkipRule>> combinedExpressionsAndRules = new HashMap<>();

		List<String> values = createCombinedValues(stringConverters);

		for (StringConverter ruleAbbreviationConverter : stringConverters.getAll()) {
			for (StringConverter valueConverter : stringConverters.getAll()) {
				Map<String, List<ComponentCodeSkipRule>> expressionsAndRules = createCombinedExpressionsAndRules(
						ruleAbbreviationConverter, valueConverter, values);
				combinedExpressionsAndRules.putAll(expressionsAndRules);
			}
		}
		return combinedExpressionsAndRules;
	}

	private Map<String, List<ComponentCodeSkipRule>> createCombinedExpressionsAndRules(StringConverter ruleAbbreviationConverter,
			StringConverter valueConverter, List<String> values) {

		String expression = createCombinedExpression(ruleAbbreviationConverter, valueConverter, values);
		List<ComponentCodeSkipRule> rules = values.stream().map(e -> valuesAndRules.get(e)).collect(Collectors.toList());
		Map<String, List<ComponentCodeSkipRule>> expressionsAndRules = new HashMap<>();
		expressionsAndRules.put(expression, rules);

		return expressionsAndRules;
	}

	private List<String> createCombinedValues(StringConverters stringConverters) {
		List<String> values = valuesAndRules.keySet().stream().collect(Collectors.toList());
		if (StringConverters.VALID == stringConverters) {
			// remove uneven rules because having even and uneven rules is not allowed as
			// valid rules
			List<String> unevenValues = valuesAndRules.keySet().stream()
					.filter(v -> valuesAndRules.get(v).getClass().getSimpleName().contains("Uneven"))
					.collect(Collectors.toList());
			values.removeAll(unevenValues);
			return values;
		} else {
			return values;
		}
	}

	private Map<String, List<ComponentCodeSkipRule>> createSingleExpressionsAndRules(StringConverters stringConverters) {
		Map<String, List<ComponentCodeSkipRule>> singleExpressionsAndRules = new HashMap<>();

		for (StringConverter ruleAbbreviationConverter : stringConverters.getAll()) {
			for (StringConverter valueConverter : stringConverters.getAll()) {
				Map<String, List<ComponentCodeSkipRule>> expressionsAndRules = createSingleExpressionsAndRules(
						ruleAbbreviationConverter, valueConverter);
				singleExpressionsAndRules.putAll(expressionsAndRules);
			}
		}
		return singleExpressionsAndRules;
	}

	private Map<String, List<ComponentCodeSkipRule>> createSingleExpressionsAndRules(StringConverter ruleAbbreviationConverter,
			StringConverter valueConverter) {
		Map<String, List<ComponentCodeSkipRule>> expressionsAndRules = new HashMap<>();
		for (String value : valuesAndRules.keySet()) {
			String expression = createSingleExpression(ruleAbbreviationConverter, valueConverter, value);
			ComponentCodeSkipRule rule = valuesAndRules.get(value);
			List<ComponentCodeSkipRule> rules = Arrays.asList(rule);
			expressionsAndRules.put(expression, rules);
		}
		return expressionsAndRules;
	}

	private String createSingleExpression(StringConverter ruleAbbreviationConverter, StringConverter valueConverter,
			String value) {
		StringBuilder expression = new StringBuilder();
		expression.append(ruleAbbreviationConverter.convert(ruleAbbreviation));
		expression.append("=");
		expression.append(valueConverter.convert(value));
		return expression.toString();
	}

	private String createCombinedExpression(StringConverter ruleAbbreviationConverter, StringConverter valueConverter,
			List<String> values) {
		StringBuilder expression = new StringBuilder();
		expression.append(ruleAbbreviationConverter.convert(ruleAbbreviation));
		expression.append("=");
		expression.append(valueConverter.convert(String.join(",", values)));
		return expression.toString();
	}

	public Map<String, List<ComponentCodeSkipRule>> createValidExpressionsAndRules() {
		Map<String, List<ComponentCodeSkipRule>> expressionsAndRules = createExpressionsAndRules(StringConverters.VALID);
		return expressionsAndRules;
	}

	public Map<String, List<ComponentCodeSkipRule>> createInvalidExpressionsAndRules() {
		Map<String, List<ComponentCodeSkipRule>> expressionsAndRules = createExpressionsAndRules(StringConverters.INVALID);
		return expressionsAndRules;
	}

	public Set<String> createValidExpressions() {
		Set<String> validExpressions = createValidExpressionsAndRules().keySet();
		return validExpressions;
	}

	public Set<String> createInvalidExpressions() {
		Set<String> invalidExpressions = createInvalidExpressionsAndRules().keySet();
		return invalidExpressions;
	}

}

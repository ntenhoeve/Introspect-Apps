package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.testexpression;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;

public class SkipRuleTestExpressions {

	private final String ruleAbbreviation;
	private final HashMap<String, SkipRule> valuesAndRules;

	public SkipRuleTestExpressions(String ruleAbbreviation) {
		this.ruleAbbreviation = ruleAbbreviation;
		this.valuesAndRules = new HashMap<>();
	}

	public void add(String value, SkipRule skipRule) {
		valuesAndRules.put(value, skipRule);
	}

	public Map<String, List<SkipRule>> createExpressionsAndRules(StringConverters stringCoverters) {

		Map<String, List<SkipRule>> singleExpressionsAndRules = createSingleExpressionsAndRules(stringCoverters);

		Map<String, List<SkipRule>> combinedExpressionsAndRules = createCombinedExpressionsAndRules(stringCoverters);

		Map<String, List<SkipRule>> allExpressionsAndRules = new HashMap<>();
		allExpressionsAndRules.putAll(singleExpressionsAndRules);
		allExpressionsAndRules.putAll(combinedExpressionsAndRules);

		return allExpressionsAndRules;
	}

	private Map<String, List<SkipRule>> createCombinedExpressionsAndRules(StringConverters stringConverters) {
		Map<String, List<SkipRule>> combinedExpressionsAndRules = new HashMap<>();

		List<String> values = createCombinedValues(stringConverters);

		for (StringConverter ruleAbbreviationConverter : stringConverters.getAll()) {
			for (StringConverter valueConverter : stringConverters.getAll()) {
				Map<String, List<SkipRule>> expressionsAndRules = createCombinedExpressionsAndRules(
						ruleAbbreviationConverter, valueConverter, values);
				combinedExpressionsAndRules.putAll(expressionsAndRules);
			}
		}
		return combinedExpressionsAndRules;
	}

	private Map<String, List<SkipRule>> createCombinedExpressionsAndRules(StringConverter ruleAbbreviationConverter,
			StringConverter valueConverter, List<String> values) {

		String expression = createCombinedExpression(ruleAbbreviationConverter, valueConverter, values);
		List<SkipRule> rules = values.stream().map(e -> valuesAndRules.get(e)).collect(Collectors.toList());
		Map<String, List<SkipRule>> expressionsAndRules = new HashMap<>();
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

	private Map<String, List<SkipRule>> createSingleExpressionsAndRules(StringConverters stringConverters) {
		Map<String, List<SkipRule>> singleExpressionsAndRules = new HashMap<>();

		for (StringConverter ruleAbbreviationConverter : stringConverters.getAll()) {
			for (StringConverter valueConverter : stringConverters.getAll()) {
				Map<String, List<SkipRule>> expressionsAndRules = createSingleExpressionsAndRules(
						ruleAbbreviationConverter, valueConverter);
				singleExpressionsAndRules.putAll(expressionsAndRules);
			}
		}
		return singleExpressionsAndRules;
	}

	private Map<String, List<SkipRule>> createSingleExpressionsAndRules(StringConverter ruleAbbreviationConverter,
			StringConverter valueConverter) {
		Map<String, List<SkipRule>> expressionsAndRules = new HashMap<>();
		for (String value : valuesAndRules.keySet()) {
			String expression = createSingleExpression(ruleAbbreviationConverter, valueConverter, value);
			SkipRule rule = valuesAndRules.get(value);
			List<SkipRule> rules = Arrays.asList(rule);
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

	public Map<String, List<SkipRule>> createValidExpressionsAndRules() {
		Map<String, List<SkipRule>> expressionsAndRules = createExpressionsAndRules(StringConverters.VALID);
		return expressionsAndRules;
	}

	public Map<String, List<SkipRule>> createInvalidExpressionsAndRules() {
		Map<String, List<SkipRule>> expressionsAndRules = createExpressionsAndRules(StringConverters.INVALID);
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

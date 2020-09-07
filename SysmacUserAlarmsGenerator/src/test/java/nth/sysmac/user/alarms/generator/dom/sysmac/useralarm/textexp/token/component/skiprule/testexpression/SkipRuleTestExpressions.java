package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.testexpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

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
		
		Map<String, List<SkipRule>> singleExpressionsAndRules = createSingleExpressionsAndRules(stringCoverters );

		Map<String, List<SkipRule>> combinedExpressionsAndRules = createCombinedExpressionsAndRules(stringCoverters, singleExpressionsAndRules);
		
		Map<String, List<SkipRule>> allExpressionsAndRules=new HashMap<>();
		allExpressionsAndRules.putAll(singleExpressionsAndRules);
		allExpressionsAndRules.putAll(combinedExpressionsAndRules);
		
		return allExpressionsAndRules;
	}

	private Map<String, List<SkipRule>> createCombinedExpressionsAndRules(StringConverters stringConverters, Map<String, List<SkipRule>> singleExpressionsAndRules) {
		HashMap<String, List<SkipRule>> expressionsAndRulesToCombine = createExpressionAndRulesToCombine(stringConverters, singleExpressionsAndRules);
		
		StringBuilder combinedExpression=new StringBuilder();
		List<SkipRule> combinedRules=new ArrayList<>();
		for (String expression : expressionsAndRulesToCombine.keySet()) {
			if (combinedExpression.length()!=0) {
				combinedExpression.append(" ");
			}
			combinedExpression.append(expression);
			List<SkipRule> rules = expressionsAndRulesToCombine.get(expression);
			combinedRules.addAll(rules);
		}
		
		Map<String, List<SkipRule>> combinedExpressionsAndRules=new HashMap<>();
		combinedExpressionsAndRules.put(combinedExpression.toString(), combinedRules);
		
		return combinedExpressionsAndRules;
	}

	private HashMap<String, List<SkipRule>> createExpressionAndRulesToCombine(StringConverters stringConverters,
			Map<String, List<SkipRule>> singleExpressionsAndRules) {
		HashMap<String, List<SkipRule>> expressionsAndRulesToCombine = new HashMap<>();
		expressionsAndRulesToCombine.putAll(singleExpressionsAndRules); 

		if (StringConverters.VALID==stringConverters){
			//remove uneven rules because having even and uneven rules is not allowed as valid rules
			List<String> unevenRuleExpressions= expressionsAndRulesToCombine.keySet().stream().filter(
					expression -> expressionsAndRulesToCombine.get(expression).get(0).getClass().getSimpleName().contains("Uneven"))
					.collect(Collectors.toList());
			for (String unevenRuleExpression : unevenRuleExpressions) {
				expressionsAndRulesToCombine.remove(unevenRuleExpression);				
			}
		}
		return expressionsAndRulesToCombine;
	}

	private Map<String, List<SkipRule>> createSingleExpressionsAndRules(StringConverters stringConverters) {
		Map<String, List<SkipRule>> singleExpressionsAndRules=new HashMap<>();

		for (StringConverter ruleAbbreviationConverter : stringConverters.getAll()) {
			for (StringConverter valueConverter : stringConverters.getAll()) {
				Map<String, List<SkipRule>> expressionsAndRules = createExpressionsAndRules(ruleAbbreviationConverter,
						valueConverter);
				singleExpressionsAndRules.putAll(expressionsAndRules);
			}
		}
		return singleExpressionsAndRules;
	}

	private Map<String, List<SkipRule>> createExpressionsAndRules(StringConverter ruleAbbreviationConverter,
			StringConverter valueConverter) {
		Map<String, List<SkipRule>> expressionsAndRules = new HashMap<>();
		for (String value : valuesAndRules.keySet()) {
			String expression = createExpression(ruleAbbreviationConverter, valueConverter, value);
			SkipRule rule = valuesAndRules.get(value);
			List<SkipRule> rules = Arrays.asList(rule);
			expressionsAndRules.put(expression, rules);
		}

		return expressionsAndRules;
	}

	private String createExpression(StringConverter ruleAbbreviationConverter, StringConverter valueConverter,
			String value) {
		StringBuilder expression = new StringBuilder();
		expression.append(ruleAbbreviationConverter.convert(ruleAbbreviation));
		expression.append("=");
		expression.append(valueConverter.convert(value));
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

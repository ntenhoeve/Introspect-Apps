package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.UnsignedInteger;

public class ExpressionsAndTokens extends HashMap<String, TokenDefinition> {

	private static final long serialVersionUID = 833513786634963660L;
	private static final TokenDefinition REST_DEFINITION = new Rest();
	private static final String REST = "Rest";

	public Stream<Arguments> createTestArguments() {
		Map<String, List<Token>> testValues = createTestValues();
		return testValues.keySet().stream().map(expression -> Arguments.of(expression, testValues.get(expression)));
	}

	private Map<String, List<Token>> createTestValues() {
		Map<String, List<Token>> testValues = new HashMap<>();

		testValues.putAll(createSingleTestValues());

		testValues.putAll(createCombinedTestValues());

		return testValues;
	}

	private Map<String, List<Token>> createSingleTestValues() {
		Map<String, List<Token>> singleTestValues = new HashMap<>();
		for (String expression : keySet()) {
			TokenDefinition tokenDefinition = get(expression);
			Token token = new Token(tokenDefinition, expression);
			List<Token> tokens = Arrays.asList(token);
			singleTestValues.put(expression, tokens);
		}
		return singleTestValues;
	}

	private Map<String, List<Token>> createCombinedTestValues() {
		Map<String, List<Token>> combinedTestValues = new HashMap<>();
		StringBuilder combinedExpressions = new StringBuilder();
		List<Token> combinedTokens = new ArrayList<>();

		combinedExpressions.append(REST);
		Token token = new Token(REST_DEFINITION, REST);
		combinedTokens.add(token);

		for (String expression : keySet()) {
			TokenDefinition tokenDefinition = get(expression);
			if (!(tokenDefinition instanceof UnsignedInteger)) {
				combinedExpressions.append(expression);
				token = new Token(tokenDefinition, expression);
				combinedTokens.add(token);
				combinedTestValues.put(combinedExpressions.toString(), new ArrayList<>(combinedTokens));
			}
		}

		return combinedTestValues;
	}

}

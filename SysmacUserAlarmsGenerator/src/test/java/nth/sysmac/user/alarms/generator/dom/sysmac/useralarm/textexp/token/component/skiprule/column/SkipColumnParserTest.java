package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.testexpression.SkipRuleTestExpressions;

class SkipColumnParserTest {

	private static SkipColumnParser skipColumnParser;
	private static SkipRuleTestExpressions testExpressions;

	@BeforeAll
	static void beforeAll() {
		skipColumnParser = new SkipColumnParser();
		testExpressions = createTestExpressions();
	}

	private static SkipRuleTestExpressions createTestExpressions() {
		SkipRuleTestExpressions testExpression = new SkipRuleTestExpressions("sc");
		testExpression.add("e", new SkipEvenColumnRule());
		testExpression.add("u", new SkipUnevenColumnRule());
		return testExpression;
	}

	// TODO add combinations to valid and invalid expressions

	@Test
	void testGetRegex_givenValidExpressions_allMatch() {
		Set<String> validExpressions = testExpressions.createValidExpressions();
		assertThat(validExpressions).allSatisfy(expression -> skipColumnParser.getRegex().hasMatchIn(expression));
	}

	@Test
	void testGetRegex_givenInvalidExpressions_allNotMatch() {
		Set<String> invalidExpressions = testExpressions.createInvalidExpressions();
		assertThat(invalidExpressions).allSatisfy(expression -> skipColumnParser.getRegex().hasMatchIn(expression));
	}

	@Test
	void testParse_givenValidExpressions_returnCorrectRule() {
		Map<String, List<SkipRule>> validExpressionsAndRules = testExpressions.createValidExpressionsAndRules();
		assertThat(validExpressionsAndRules)
				.allSatisfy((expression, rules) -> skipColumnParser.parse(expression).containsAll(rules));
	}
	

	@Test
	void testParse_givenInvalidExpressions_allThrowSomeError() {
		Set<String> invalidExpressions = testExpressions.createInvalidExpressions();
		for (String invalidExpression : invalidExpressions) {
			assertThatThrownBy(() -> skipColumnParser.parse(invalidExpression));
		}
	}

}

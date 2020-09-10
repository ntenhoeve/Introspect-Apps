package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.column;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.testexpression.SkipRuleTestExpressions;

class SkipColumnParsersTest {

	private static SkipColumnParsers skipColumnParsers;
	private static SkipRuleTestExpressions testExpressions;

	@BeforeAll
	static void beforeAll() {
		skipColumnParsers = new SkipColumnParsers();
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
		assertThat(validExpressions).allSatisfy(expression -> skipColumnParsers.getRegex().hasMatchIn(expression));
	}

	@Test
	void testGetRegex_givenInvalidExpressions_allNotMatch() {
		Set<String> invalidExpressions = testExpressions.createInvalidExpressions();
		assertThat(invalidExpressions).allSatisfy(expression -> skipColumnParsers.getRegex().hasMatchIn(expression));
	}

	@Test
	void testParse_givenValidExpressions_returnCorrectRule() {
		Map<String, List<SkipRule>> validExpressionsAndRules = testExpressions.createValidExpressionsAndRules();
		assertThat(validExpressionsAndRules)
				.allSatisfy((expression, rules) -> skipColumnParsers.parse(expression).containsAll(rules));
	}

	@Test
	void testParse_givenInvalidExpressions_allThrowSomeError() {
		Set<String> invalidExpressions = testExpressions.createInvalidExpressions();
		assertThat(invalidExpressions).allSatisfy(invalidExpression -> {
			assertThatThrownBy(() -> skipColumnParsers.parse(invalidExpression));
		});
	}

}

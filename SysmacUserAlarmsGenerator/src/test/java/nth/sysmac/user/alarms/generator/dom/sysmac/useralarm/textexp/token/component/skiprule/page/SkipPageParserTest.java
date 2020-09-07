package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.testexpression.SkipRuleTestExpressions;

class SkipPageParserTest {

	private static SkipPageParser skipPageParser;
	private static SkipRuleTestExpressions testExpressions;

	@BeforeAll
	static void beforeAll() {
		skipPageParser = new SkipPageParser();
		testExpressions = createTestExpressions();
	}

	private static SkipRuleTestExpressions createTestExpressions() {
		SkipRuleTestExpressions testExpression = new SkipRuleTestExpressions("sp");
		testExpression.add("e", new SkipEvenPageRule());
		testExpression.add("u", new SkipUnevenPageRule());
		return testExpression;
	}

	// TODO add combinations to valid and invalid expressions

	@Test
	void testGetRegex_givenValidExpressions_allMatch() {
		Set<String> validExpressions = testExpressions.createValidExpressions();
		assertThat(validExpressions).allSatisfy(expression -> skipPageParser.getRegex().hasMatchIn(expression));
	}

	@Test
	void testGetRegex_givenInvalidExpressions_allNotMatch() {
		Set<String> invalidExpressions = testExpressions.createInvalidExpressions();
		assertThat(invalidExpressions).allSatisfy(expression -> skipPageParser.getRegex().hasMatchIn(expression));
	}

	@Test
	void testParse_givenValidExpressions_returnCorrectRule() {
		Map<String, List<SkipRule>> validExpressionsAndRules = testExpressions.createValidExpressionsAndRules();
		assertThat(validExpressionsAndRules)
				.allSatisfy((expression, rules) -> skipPageParser.parse(expression).containsAll(rules));
	}

	@Test
	void testParse_givenInvalidExpressions_allThrowSomeError() {
		Set<String> invalidExpressions = testExpressions.createInvalidExpressions();
		for (String invalidExpression : invalidExpressions) {
			assertThatThrownBy(() -> skipPageParser.parse(invalidExpression));
		}
	}

}

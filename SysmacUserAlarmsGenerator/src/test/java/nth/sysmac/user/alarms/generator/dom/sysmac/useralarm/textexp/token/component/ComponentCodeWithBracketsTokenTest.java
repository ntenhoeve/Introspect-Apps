package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column.SkipEvenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page.SkipEvenPageRule;

class ComponentCodeWithBracketsTokenTest {

	private static final int PAGE_110 = 110;
	private static final Character LETTER_U = 'U';
	private static final int COLUMN_2 = 2;
	private static final String COMPONENT_CODE = "" + PAGE_110 + LETTER_U + COLUMN_2;
	private static final String COMPONENT_CODE_WITH_BRACKETS = "{" + COMPONENT_CODE+"}";
	private static final String COMPONENT_CODE_WITH_2_SKIP_RULES_AND_INVALID_SKIP_RULE = "{" + COMPONENT_CODE
			+ " sc=u sp=e invalidSkipRule}";
	private static final String COMPONENT_CODE_WITH_2_SKIP_RULES_AND_WHITE_SPACES = "{  " + COMPONENT_CODE
			+ "  	 sc=u  sp=e  }";
	private static final String COMPONENT_CODE_WITH_2_SKIP_RULES = "{" + COMPONENT_CODE + " sc=u sp=e}";
	private static final String COMPONENT_CODE_WITH_1_SKIP_RULE = "{" + COMPONENT_CODE + " sc=e}";
	private static final String LOW_CASE_COMPONENT_CODE = "{" + COMPONENT_CODE.toLowerCase() + "}";
	private static final String INVALID_COMPONENT_CODE1 = "{Z" + COMPONENT_CODE + "}";
	private static final String INVALID_COMPONENT_CODE2 = "{U2}";

	private static ComponentCodeWithBracketsParser token;

	@BeforeAll
	private static void setup() {
		token = new ComponentCodeWithBracketsParser();
	}

	@Test
	void testGetRegex_givenInvalidComponentCode1_mustNotFind() {
		assertThat(token.getRegex().hasMatchIn(INVALID_COMPONENT_CODE1)).isFalse();
	}

	@Test
	void testGetRegex_givenInvalidComponentCode2_mustNotFind() {
		assertThat(token.getRegex().hasMatchIn(INVALID_COMPONENT_CODE2)).isFalse();
	}

	@Test
	void testGetRegex_givenCorrectTokenWithComponentCode_mustFind() {
		assertThat(token.getRegex().hasMatchIn(COMPONENT_CODE_WITH_BRACKETS)).isTrue();
	}

	@Test
	void testGetRegex_givenCorrectTokenWithLowCaseComponentCode_mustFind() {
		assertThat(token.getRegex().hasMatchIn(LOW_CASE_COMPONENT_CODE)).isTrue();
	}

	@Test
	void testGetRegex_givenCorrectTokenWithComponentCodeAndOneSkipRule_mustFind() {
		assertThat(token.getRegex().hasMatchIn(COMPONENT_CODE_WITH_1_SKIP_RULE)).isTrue();
	}

	@Test
	void testGetRegex_givenCorrectTokenWithComponentCodeAndTwoSkipRules_mustFind() {
		assertThat(token.getRegex().hasMatchIn(COMPONENT_CODE_WITH_2_SKIP_RULES)).isTrue();
	}

	@Test
	void testGetRegex_givenCorrectTokenWithComponentCodeAndTwoSkipRulesAndWhiteSpaces_mustFind() {
		assertThat(token.getRegex().hasMatchIn(COMPONENT_CODE_WITH_2_SKIP_RULES_AND_WHITE_SPACES)).isTrue();
	}

	@Test
	void testGetRegex_givenCorrectTokenWithComponentCodeAndTwoSkipRulesAndInvalidSkipRule_mustFind() {
		assertThat(token.getRegex().hasMatchIn(COMPONENT_CODE_WITH_2_SKIP_RULES_AND_INVALID_SKIP_RULE)).isTrue();
	}

	@Test
	void testParse_givenCorrectTokenWithComponentCode_mustReturnCorrectComponentCode() {
		ComponentCode expected=new ComponentCode(PAGE_110, LETTER_U, COLUMN_2);
		ComponentCode parsed = token.parse(COMPONENT_CODE_WITH_BRACKETS);
		assertThat(parsed).usingRecursiveComparison().isEqualTo(expected);
	}

	
	@Test
	void testParse_givenCorrectTokenWithLowCaseComponentCode_mustReturnCorrectComponentCode() {
		ComponentCode expected=new ComponentCode(PAGE_110, LETTER_U, COLUMN_2);
		ComponentCode parsed = token.parse(LOW_CASE_COMPONENT_CODE);
		assertThat(parsed).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void testParse_givenCorrectTokenWithComponentCodeAndOneSkipRule_mustReturnCorrectComponentCode() {
		SkipRules expectedRules=new SkipRules();
		expectedRules.add(new SkipEvenColumnRule());
		ComponentCode expected=new ComponentCode(PAGE_110, LETTER_U, COLUMN_2, expectedRules);
		ComponentCode parsed = token.parse(COMPONENT_CODE_WITH_1_SKIP_RULE);
		assertThat(parsed).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void testParse_givenCorrectTokenWithComponentCodeAndTwoSkipRules_mustReturnCorrectComponentCode() {
		SkipRules expectedRules=new SkipRules();
		expectedRules.add(new SkipEvenColumnRule());
		expectedRules.add(new SkipEvenPageRule());
		ComponentCode expected=new ComponentCode(PAGE_110, LETTER_U, COLUMN_2, expectedRules);
		ComponentCode parsed = token.parse(COMPONENT_CODE_WITH_2_SKIP_RULES);
		assertThat(parsed).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void testParse_givenCorrectTokenWithComponentCodeAndTwoSkipRulesAndWhiteSpaces_mustReturnCorrectComponentCode() {
		SkipRules expectedRules=new SkipRules();
		expectedRules.add(new SkipEvenColumnRule());
		expectedRules.add(new SkipEvenPageRule());
		ComponentCode expected=new ComponentCode(PAGE_110, LETTER_U, COLUMN_2, expectedRules);
		ComponentCode parsed = token.parse(COMPONENT_CODE_WITH_2_SKIP_RULES_AND_WHITE_SPACES);
		assertThat(parsed).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void testParse_givenCorrectTokenWithComponentCodeAndTwoSkipRulesAndInvalidSkipRule_mustThrowAnException() {
		assertThatThrownBy(() -> token.parse(COMPONENT_CODE_WITH_2_SKIP_RULES_AND_INVALID_SKIP_RULE)).hasMessageContaining("Invalid skip rule expression(s): ");
	}


}

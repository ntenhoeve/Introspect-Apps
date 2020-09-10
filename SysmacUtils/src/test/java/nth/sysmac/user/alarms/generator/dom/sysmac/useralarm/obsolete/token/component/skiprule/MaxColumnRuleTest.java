package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.ComponentCode;

class MaxColumnRuleTest {

	private static final char LETTER = 'U';
	private static final int PAGE = 10;
	private static final Object FIRST_COLUMN = 1;
	private static final int CULUMN_8 = 8;
	private static final int CULUMN_9 = 9;
	private static final int CULUMN_1000 = 1000;
	private static MaxColumnRule skipMaxColumnRule;

	@BeforeAll
	static void setup() {
		skipMaxColumnRule = new MaxColumnRule();
	}

	@Test
	void testAppliesTo_givenEvenColumn8_mustReturnFalse() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, CULUMN_8);
		assertThat(skipMaxColumnRule.appliesTo(componentCode)).isFalse();
	}
	
	@Test
	void testAppliesTo_givenEvenColumn9_mustReturnTrue() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, CULUMN_9);
		assertThat(skipMaxColumnRule.appliesTo(componentCode)).isTrue();
	}
	
	@Test
	void testAppliesTo_givenEvenColumn1000_mustReturnNextPageFirstColumn() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, CULUMN_1000);
		assertThat(skipMaxColumnRule.appliesTo(componentCode)).isTrue();
	}

	
	@Test
	void testGetNext_givenEvenColumn9_mustReturnNextPageFirstColumn() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, CULUMN_9);
		assertThat(skipMaxColumnRule.getNext(componentCode)).hasFieldOrPropertyWithValue("page", PAGE+1)
		.hasFieldOrPropertyWithValue("column", FIRST_COLUMN);
	}
	
	@Test
	void testGetNext_givenEvenColumn1000_mustReturnNextPageFirstColumn() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, CULUMN_1000);
		assertThat(skipMaxColumnRule.getNext(componentCode)).hasFieldOrPropertyWithValue("page", PAGE+1)
		.hasFieldOrPropertyWithValue("column", FIRST_COLUMN);
	}


}

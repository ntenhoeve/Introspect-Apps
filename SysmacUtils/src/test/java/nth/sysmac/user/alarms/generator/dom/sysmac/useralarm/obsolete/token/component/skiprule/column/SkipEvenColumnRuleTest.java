package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.column;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.ComponentCode;

class SkipEvenColumnRuleTest {

	private static final char LETTER = 'U';
	private static final int PAGE = 10;
	private static final int EVEN_COLUMN = 2;
	private static final int UNEVEN_COLUMN = 1;
	private static SkipEvenColumnRule skipEvenColumnRule;

	@BeforeAll
	static void setup() {
		skipEvenColumnRule = new SkipEvenColumnRule();
	}

	@Test
	void testAppliesTo_givenEvenColumnNumber_mustReturnTrue() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, EVEN_COLUMN);
		assertThat(skipEvenColumnRule.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenUnEvenColumnNumber_mustReturnTrue() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, UNEVEN_COLUMN);
		assertThat(skipEvenColumnRule.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testGetNext_givenEvenColumnNumber_mustReturnNextColumnOnSamePage() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, EVEN_COLUMN);
		assertThat(skipEvenColumnRule.getNext(componentCode)).hasFieldOrPropertyWithValue("page", PAGE)
				.hasFieldOrPropertyWithValue("column", EVEN_COLUMN + 1);

	}
}

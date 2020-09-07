package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCode;

class SkipUnevenColumnRuleTest {

	private static final char LETTER = 'U';
	private static final int PAGE = 10;
	private static final int EVEN_COLUMN = 2;
	private static final int UNEVEN_COLUMN = 1;
	private static SkipUnevenColumnRule skipUnevenColumnRule;

	@BeforeAll
	static void setup() {
		skipUnevenColumnRule = new SkipUnevenColumnRule();
	}

	@Test
	void testAppliesTo_givenUnEvenColumnNumber_mustReturnTrue() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, UNEVEN_COLUMN);
		assertThat(skipUnevenColumnRule.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenEvenColumnNumber_mustReturnTrue() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, EVEN_COLUMN);
		assertThat(skipUnevenColumnRule.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testGetNext_givenUnEvenColumnNumber_mustReturnNextColumnOnSamePage() {
		ComponentCode componentCode = new ComponentCode(PAGE, LETTER, UNEVEN_COLUMN);
		assertThat(skipUnevenColumnRule.getNext(componentCode)).hasFieldOrPropertyWithValue("page", PAGE)
				.hasFieldOrPropertyWithValue("column", UNEVEN_COLUMN + 1);

	}
}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCode;

class SkipUnevenPageRuleTest {

	private static SkipUnevenPageRule skipUnevenPageRule;

	@BeforeAll
	static void setup() {
		skipUnevenPageRule = new SkipUnevenPageRule();
	}

	@Test
	void testAppliesTo_givenUnEvenPageNumber_mustReturnTrue() {
		ComponentCode componentCode = new ComponentCode(11, 'U', 1);
		assertThat(skipUnevenPageRule.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenEvenPageNumber_mustReturnTrue() {
		ComponentCode componentCode = new ComponentCode(12, 'U', 1);
		assertThat(skipUnevenPageRule.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testGetNext_givenUnEvenPageNumber_mustReturnNextPageFirstColumn() {
		ComponentCode componentCode = new ComponentCode(11, 'U', 2);
		assertThat(skipUnevenPageRule.getNext(componentCode)).hasFieldOrPropertyWithValue("page", 12)
				.hasFieldOrPropertyWithValue("column", 1);
	}

}

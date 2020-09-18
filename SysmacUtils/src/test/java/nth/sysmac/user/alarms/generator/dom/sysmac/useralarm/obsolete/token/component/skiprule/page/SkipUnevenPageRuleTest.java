package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.page;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;

class SkipUnevenPageRuleTest {

	private static SkipUnevenPageRule skipUnevenPageRule;

	@BeforeAll
	static void setup() {
		skipUnevenPageRule = new SkipUnevenPageRule();
	}

	@Test
	void testAppliesTo_givenUnEvenPageNumber_mustReturnTrue() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(11, 'U', 1);
		assertThat(skipUnevenPageRule.appliesTo(componentCodeNode)).isTrue();
	}

	@Test
	void testAppliesTo_givenEvenPageNumber_mustReturnTrue() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(12, 'U', 1);
		assertThat(skipUnevenPageRule.appliesTo(componentCodeNode)).isFalse();
	}

	@Test
	void testGetNext_givenUnEvenPageNumber_mustReturnNextPageFirstColumn() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(11, 'U', 2);
		skipUnevenPageRule.goToNext(componentCodeNode);
		assertThat(componentCodeNode).hasFieldOrPropertyWithValue("page", 12)
				.hasFieldOrPropertyWithValue("column", 1);
	}

}

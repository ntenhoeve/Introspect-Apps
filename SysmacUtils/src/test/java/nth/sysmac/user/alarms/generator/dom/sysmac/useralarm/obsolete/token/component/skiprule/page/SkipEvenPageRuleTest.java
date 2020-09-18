package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.page;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;

class SkipEvenPageRuleTest {

	private static SkipEvenPageRule skipEvenPageRule;

	@BeforeAll
	static void setup() {
		skipEvenPageRule = new SkipEvenPageRule();
	}
	
	@Test
	void testAppliesTo_givenEvenPageNumber_mustReturnTrue() {
		ComponentCodeNode componentCodeNode=new ComponentCodeNode(10, 'U', 1);
		assertThat(skipEvenPageRule.appliesTo(componentCodeNode)).isTrue();
	}

	@Test
	void testAppliesTo_givenUnEvenPageNumber_mustReturnTrue() {
		ComponentCodeNode componentCodeNode=new ComponentCodeNode(11, 'U', 1);
		assertThat(skipEvenPageRule.appliesTo(componentCodeNode)).isFalse();
	}

	@Test
	void testGetNext_givenEvenPageNumber_mustReturnNextPage() {
		ComponentCodeNode componentCodeNode=new ComponentCodeNode(10, 'U', 1);
		skipEvenPageRule.goToNext(componentCodeNode);
		assertThat(componentCodeNode).extracting("page").isEqualTo(11);
		
	}

}

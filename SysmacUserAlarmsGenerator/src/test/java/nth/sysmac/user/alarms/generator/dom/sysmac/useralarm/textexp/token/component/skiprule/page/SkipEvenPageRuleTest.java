package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCode;

class SkipEvenPageRuleTest {

	private static SkipEvenPageRule skipEvenPageRule;

	@BeforeAll
	static void setup() {
		skipEvenPageRule = new SkipEvenPageRule();
	}
	
	@Test
	void testAppliesTo_givenEvenPageNumber_mustReturnTrue() {
		ComponentCode componentCode=new ComponentCode(10, 'U', 1);
		assertThat(skipEvenPageRule.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenUnEvenPageNumber_mustReturnTrue() {
		ComponentCode componentCode=new ComponentCode(11, 'U', 1);
		assertThat(skipEvenPageRule.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testGetNext_givenEvenPageNumber_mustReturnNextPage() {
		ComponentCode componentCode=new ComponentCode(10, 'U', 1);
		assertThat(skipEvenPageRule.getNext(componentCode)).extracting("page").isEqualTo(11);
		
	}

}

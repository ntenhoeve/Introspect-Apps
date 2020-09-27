package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;

class SkipPageColumnRangeNodeTest {

	
	private static final char Q = 'Q';
	private static SkipPageColumnRangeNode skipPageColumnRangeNode;

	@BeforeAll
	static void beforeAll() {
		skipPageColumnRangeNode = new SkipPageColumnRangeNode(30,5, 32,3);
	}
	
	@Test
	void testAppliesTo_givenRange30_5till32_3_givenComponentCode20_8_returnsFalse() {
		ComponentCodeNode componentCode=new ComponentCodeNode(20, Q, 8);
		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testAppliesTo_givenRange30_5till32_3_givenComponentCode30_4_returnsFalse() {
		ComponentCodeNode componentCode=new ComponentCodeNode(30, Q, 4);
		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}


	@Test
	void testAppliesTo_givenRange30_5till32_3_givenComponentCode30_5_returnsTrue() {
		ComponentCodeNode componentCode=new ComponentCodeNode(30, Q, 5);
		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenRange30_5till32_3_givenComponentCode31_5_returnsTrue() {
		ComponentCodeNode componentCode=new ComponentCodeNode(31, Q, 5);
		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenRange30_5till32_3_givenComponentCode32_3_returnsTrue() {
		ComponentCodeNode componentCode=new ComponentCodeNode(32, Q, 3);
		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}
	

	@Test
	void testAppliesTo_givenRange30_5till32_3_givenComponentCode32_4_returnsFalse() {
		ComponentCodeNode componentCode=new ComponentCodeNode(32, Q, 4);
		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}
	
	@Test
	void testAppliesTo_givenRange30_5till32_3_givenComponentCode33_1_returnsFalse() {
		ComponentCodeNode componentCode=new ComponentCodeNode(33, Q, 1);
		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}
	
	@Test
	void testGoToNext_givenRange30_5till32_3_givenComponentCode30_5_returnsNextProductCode32_4() {
		ComponentCodeNode componentCode = new ComponentCodeNode(30, Q,5);
		skipPageColumnRangeNode.goToNext(componentCode);
		assertThat(componentCode).hasFieldOrPropertyWithValue("page", 32)//
				.hasFieldOrPropertyWithValue("letter", Q)//
				.hasFieldOrPropertyWithValue("column", 4);
	}


	@Test
	void testGoToNext_givenRange30_5till32_3_givenComponentCodeWith31_2_returnsNextProductCode32_4() {
		ComponentCodeNode componentCode = new ComponentCodeNode(31, Q,2);
		skipPageColumnRangeNode.goToNext(componentCode);
		assertThat(componentCode).hasFieldOrPropertyWithValue("page", 32)//
				.hasFieldOrPropertyWithValue("letter", Q)//
				.hasFieldOrPropertyWithValue("column", 4);
	}


	@Test
	void testGoToNext_givenRange30_5till32_3_givenComponentCodeWith32_3_returnsNextProductCode32_4() {
		ComponentCodeNode componentCode = new ComponentCodeNode(32, Q,3);
		skipPageColumnRangeNode.goToNext(componentCode);
		assertThat(
				componentCode).hasFieldOrPropertyWithValue("page", 32)//
				.hasFieldOrPropertyWithValue("letter", Q)//
				.hasFieldOrPropertyWithValue("column", 4);
	}


}

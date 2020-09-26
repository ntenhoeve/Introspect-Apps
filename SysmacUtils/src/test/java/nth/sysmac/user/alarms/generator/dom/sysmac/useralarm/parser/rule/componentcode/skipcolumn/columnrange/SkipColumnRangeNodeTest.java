package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;

class SkipColumnRangeNodeTest {

	
	private static final int PAGE_NUMBER = 30;
	private static final char COMPONENT_LETTER = 'Q';
	private static SkipColumnRangeNode skipColumnRangeNode;

	@BeforeAll
	static void beforeAll() {
		skipColumnRangeNode = new SkipColumnRangeNode(3, 5);
	}
	
	@Test
	void testAppliesTo_givenRange3Till5_givenComponentCodeWithColumn2_returnsFalse() {
		ComponentCodeNode componentCode=new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER, 2);
		assertThat(skipColumnRangeNode.appliesTo(componentCode)).isFalse();
	}



	@Test
	void testAppliesTo_givenRange3Till5_givenComponentCodeWithColumn3_returnsTrue() {
		ComponentCodeNode componentCode=new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER, 3);
		assertThat(skipColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenRange3Till5_givenComponentCodeWithColumn4_returnsTrue() {
		ComponentCodeNode componentCode=new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER, 4);
		assertThat(skipColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenRange3Till5_givenComponentCodeWithColumn5_returnsTrue() {
		ComponentCodeNode componentCode=new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER, 5);
		assertThat(skipColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenRange3Till5_givenComponentCodeWithColumn6_returnsFalse() {
		ComponentCodeNode componentCode=new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER, 6);
		assertThat(skipColumnRangeNode.appliesTo(componentCode)).isFalse();
	}
	
	
	@Test
	void testGoToNext_givenRange3Till5_givenComponentCodeWithColumn4_returnsNextProductCodeWithColumn6() {
		ComponentCodeNode componentCode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER,
				4);
		skipColumnRangeNode.goToNext(componentCode);
		assertThat(componentCode).hasFieldOrPropertyWithValue("page", PAGE_NUMBER)//
				.hasFieldOrPropertyWithValue("letter", COMPONENT_LETTER)//
				.hasFieldOrPropertyWithValue("column", 6);
	}


	@Test
	void testGoToNext_givenRange3Till5_givenComponentCodeWithColumn5_returnsNextProductCodeWithColumn6() {
		ComponentCodeNode componentCode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER,
				5);
		skipColumnRangeNode.goToNext(componentCode);
		assertThat(componentCode).hasFieldOrPropertyWithValue("page", PAGE_NUMBER)//
				.hasFieldOrPropertyWithValue("letter", COMPONENT_LETTER)//
				.hasFieldOrPropertyWithValue("column", 6);
	}


	@Test
	void testGoToNext_givenRange3Till5_givenComponentCodeWithColumn6_returnsNextProductCodeWithColumn6() {
		ComponentCodeNode componentCode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER,
				6);
		skipColumnRangeNode.goToNext(componentCode);
		assertThat(
				componentCode).hasFieldOrPropertyWithValue("page", PAGE_NUMBER)//
				.hasFieldOrPropertyWithValue("letter", COMPONENT_LETTER)//
				.hasFieldOrPropertyWithValue("column", 6);
	}



}

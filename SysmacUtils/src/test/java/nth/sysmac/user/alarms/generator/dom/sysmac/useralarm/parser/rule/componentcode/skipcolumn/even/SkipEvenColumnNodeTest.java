package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.even;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;

class SkipEvenColumnNodeTest {

	private static final int PAGE_NUMBER = 110;
	private static final char COMPONENT_LETTER = 'U';
	private static final int UNEVEN_COLUMN_NUMBER = 5;
	private static final int EVEN_COLUMN_NUMBER = 4;

	private static SkipEvenColumnNode skipEvenColumnNode;

	@BeforeAll
	public static void beforeAll() {
		skipEvenColumnNode = new SkipEvenColumnNode();
	}

	@Test
	void testAppliesTo_givenUnevenComponentCode_returnsFalse() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER,
				UNEVEN_COLUMN_NUMBER);
		assertThat(skipEvenColumnNode.appliesTo(componentCodeNode)).isFalse();
	}

	@Test
	void testAppliesTo_givenEvenComponentCode_returnsTrue() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER, EVEN_COLUMN_NUMBER);
		assertThat(skipEvenColumnNode.appliesTo(componentCodeNode)).isTrue();
	}

	@Test
	void testGoToNext_givenEvenComponentCode_returnsNextProductCode() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER,
				EVEN_COLUMN_NUMBER);
		skipEvenColumnNode.goToNext(componentCodeNode);
		assertThat(componentCodeNode).hasFieldOrPropertyWithValue("page", PAGE_NUMBER)
				.hasFieldOrPropertyWithValue("letter", COMPONENT_LETTER)
				.hasFieldOrPropertyWithValue("column", EVEN_COLUMN_NUMBER + 1);
	}

}

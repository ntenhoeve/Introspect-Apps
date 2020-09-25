package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.uneven;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;

class SkipUnevenColumnNodeTest {

	private static final int PAGE_NUMBER = 110;
	private static final char COMPONENT_LETTER = 'U';
	private static final int UNEVEN_COLUMN_NUMBER = 5;
	private static final int EVEN_COLUMN_NUMBER = 4;

	private static SkipUnevenColumnNode skipUnevenColumnNode;

	@BeforeAll
	public static void beforeAll() {
		skipUnevenColumnNode = new SkipUnevenColumnNode();
	}

	@Test
	void testAppliesTo_givenUnevenComponentCode_returnsTrue() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER,
				UNEVEN_COLUMN_NUMBER);
		assertThat(skipUnevenColumnNode.appliesTo(componentCodeNode)).isTrue();
	}

	@Test
	void testAppliesTo_givenEvenComponentCode_returnsFalse() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER, EVEN_COLUMN_NUMBER);
		assertThat(skipUnevenColumnNode.appliesTo(componentCodeNode)).isFalse();
	}

	@Test
	void testGoToNext_givenUnevenComponentCode_returnsNextProductCode() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE_NUMBER, COMPONENT_LETTER,
				UNEVEN_COLUMN_NUMBER);
		skipUnevenColumnNode.goToNext(componentCodeNode);
		assertThat(componentCodeNode).hasFieldOrPropertyWithValue("page", PAGE_NUMBER)
				.hasFieldOrPropertyWithValue("letter", COMPONENT_LETTER)
				.hasFieldOrPropertyWithValue("column", UNEVEN_COLUMN_NUMBER + 1);
	}

}

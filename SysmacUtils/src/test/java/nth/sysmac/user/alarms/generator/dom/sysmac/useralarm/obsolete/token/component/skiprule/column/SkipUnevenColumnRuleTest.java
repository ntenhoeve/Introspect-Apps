package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.column;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.column.SkipUnevenColumnRule;

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
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE, LETTER, UNEVEN_COLUMN);
		assertThat(skipUnevenColumnRule.appliesTo(componentCodeNode)).isTrue();
	}

	@Test
	void testAppliesTo_givenEvenColumnNumber_mustReturnTrue() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE, LETTER, EVEN_COLUMN);
		assertThat(skipUnevenColumnRule.appliesTo(componentCodeNode)).isFalse();
	}

	@Test
	void testGetNext_givenUnEvenColumnNumber_mustReturnNextColumnOnSamePage() {
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(PAGE, LETTER, UNEVEN_COLUMN);
		skipUnevenColumnRule.goToNext(componentCodeNode);
		assertThat(componentCodeNode).hasFieldOrPropertyWithValue("page", PAGE).hasFieldOrPropertyWithValue("column",
				UNEVEN_COLUMN + 1);

	}
}

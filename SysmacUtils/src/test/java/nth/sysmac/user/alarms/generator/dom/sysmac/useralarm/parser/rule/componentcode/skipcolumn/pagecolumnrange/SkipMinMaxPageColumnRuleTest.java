package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.NodeParserRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class SkipMinMaxPageColumnRuleTest {

	@RepeatedTest(30)
	void test_givenValidExpression_returnValidParseTree() {
		ExpressionAndNodes attributeValue = new SkipMinMaxPageColumnTestAttributeFactory().create();
		ExpressionAndNodes attribute = TestObjectFactory
				.bracedAttributeWithValueSurroundedByRandomValues(BracedAttributeName.SKIP, attributeValue);
		ExpressionAndNodes braced = TestObjectFactory.braceNode(attribute);
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.surroundWithRandomTokens(braced);

		TokenParser tokenParser = new TokenParser(TokenRules.all());
		String expression = expressionAndNodes.expression();
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getNodes();
		List<Node> parcedNodes = expressionAndNodes.parcedNodes();
		assertThat(actual).as("expression=%s", expression).containsExactlyElementsOf(parcedNodes);
	}

	@Test
	void testAppliesTo_givenSkip30_5til32_3_givenComponentCode29_7_returnFalse() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(29, 'U', 7);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testAppliesTo_givenSkip30_5til32_3_givenComponentCode30_4_returnFalse() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(30, 'U', 4);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testAppliesTo_givenSkip30_5til32_3_givenComponentCode30_5_returnTrue() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(30, 'U', 5);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenSkip30_5til32_3_givenComponentCode31_1_returnTrue() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(31, 'U', 1);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenSkip30_5til32_3_givenComponentCode32_2_returnTrue() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(32, 'U', 2);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenSkip30_5til32_3_givenComponentCode32_4_returnFalse() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(32, 'U', 4);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testAppliesTo_givenSkip30_5til32_3_givenComponentCode33_1_returnFalse() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(33, 'U', 1);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testGoToNext_givenSkip30_5till32_3_givenComponentCode30_5_returnComponentCode32_4() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(30, 'U', 5);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);
		skipPageColumnRangeNode.goToNext(componentCode);

		assertThat(componentCode)//
				.hasFieldOrPropertyWithValue("page", 32).hasFieldOrPropertyWithValue("column", 4);
	}

	@Test
	void testGoToNext_givenSkip30_5till32_3_givenComponentCode31_2_returnComponentCode32_4() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(31, 'U', 2);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);
		skipPageColumnRangeNode.goToNext(componentCode);

		assertThat(componentCode)//
				.hasFieldOrPropertyWithValue("page", 32).hasFieldOrPropertyWithValue("column", 4);
	}

	@Test
	void testGoToNext_givenSkip30_5till32_3_givenComponentCode32_3_returnComponentCode32_4() {
		String expression = createSkipMinMaxPageColumnAttributeExpression(30, 5, 32, 3);
		ComponentCodeNode componentCode = new ComponentCodeNode(32, 'U', 3);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipPageColumnRangeNode(expression);
		skipPageColumnRangeNode.goToNext(componentCode);

		assertThat(componentCode)//
				.hasFieldOrPropertyWithValue("page", 32).hasFieldOrPropertyWithValue("column", 4);
	}

	private SkipPageColumnRangeNode parseSkipPageColumnRangeNode(String expression) {
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		ParseTree parseTree = nodeParser.parse(tokens);
		Node braced = parseTree.getNodes().get(0);
		Node bracedAttribute = braced.getNodes().get(0);
		Optional<Node> found = bracedAttribute.getNodes().stream().filter(n -> n instanceof SkipPageColumnRangeNode)
				.findFirst();
		SkipPageColumnRangeNode skipPageColumnRangeNode = (SkipPageColumnRangeNode) found.get();
		return skipPageColumnRangeNode;
	}

	private String createSkipMinMaxPageColumnAttributeExpression(int minPage, int minColumn, int maxPage,
			int maxColumn) {
		ExpressionAndNodes attributeValue = new SkipMinMaxPageColumnTestAttributeFactory().create(minPage, minColumn,
				maxPage, maxColumn);
		ExpressionAndNodes attribute = TestObjectFactory.bracedAttribute(BracedAttributeName.SKIP, attributeValue);
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.braceNode(attribute);
		return expressionAndNodes.expression();
	}

}

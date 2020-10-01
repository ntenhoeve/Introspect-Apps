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

class SkipSinglePageColumnRuleTest {

	private static final int PAGE_30 = 30;

	@RepeatedTest(30)
	void test_givenValidExpression_returnValidParseTree() {
		ExpressionAndNodes attributeValue =new  SkipSinglePageColumnTestAttributeFactory().create();
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
	void testAppliesTo_givenSkipPageColumn30_3_givenComponentCodeWithColumn30_2_returnFalse() {
		String expression = createSkipSinglePageColumnAttributeExpression(30,3);
		ComponentCodeNode componentCode = new ComponentCodeNode(PAGE_30, 'U', 2);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testAppliesTo_givenSkipColumn30_3_givenComponentCodeWithColumn30_3_returnTrue() {
		String expression = createSkipSinglePageColumnAttributeExpression(30,3);
			ComponentCodeNode componentCode = new ComponentCodeNode(PAGE_30, 'U', 3);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenSkipColumn30_3_givenComponentCodeWithColumn30_4_returnFalse() {
		String expression = createSkipSinglePageColumnAttributeExpression(30,3);
		ComponentCodeNode componentCode = new ComponentCodeNode(PAGE_30, 'U', 4);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testGoToNext_givenSkipColumn30_3_givenComponentCodeWithColumn30_3_returnColumn4() {
		String expression = createSkipSinglePageColumnAttributeExpression(30,3);
		ComponentCodeNode componentCode = new ComponentCodeNode(PAGE_30, 'U', 3);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parseSkipColumnRangeNode(expression);
		skipPageColumnRangeNode.goToNext(componentCode);

		assertThat(componentCode)//
				.hasFieldOrPropertyWithValue("page", PAGE_30).hasFieldOrPropertyWithValue("column", 4);
	}

	private SkipPageColumnRangeNode parseSkipColumnRangeNode(String expression) {
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		ParseTree parseTree = nodeParser.parse(tokens);
		Node braced = parseTree.getNodes().get(0);
		Node bracedAttribute = braced.getNodes().get(0);
		Optional<Node> found = bracedAttribute.getNodes().stream().filter(n->n instanceof SkipPageColumnRangeNode).findFirst();
		SkipPageColumnRangeNode skipPageColumnRangeNode = (SkipPageColumnRangeNode) found.get();
		return skipPageColumnRangeNode;
	}

	private String createSkipSinglePageColumnAttributeExpression(int page,int column) {
		ExpressionAndNodes attributeValue = new SkipSinglePageColumnTestAttributeFactory().create(page, column);
		ExpressionAndNodes attribute = TestObjectFactory.bracedAttribute(BracedAttributeName.SKIP, attributeValue);
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.braceNode(attribute);
		return expressionAndNodes.expression();
	}
}

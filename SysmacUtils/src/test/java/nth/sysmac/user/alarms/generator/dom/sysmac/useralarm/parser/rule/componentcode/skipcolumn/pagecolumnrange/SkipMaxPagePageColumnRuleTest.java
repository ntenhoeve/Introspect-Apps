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

class SkipMaxPagePageColumnRuleTest {


	@RepeatedTest(30)
	void test_givenValidExpression_returnValidParseTree() {
		ExpressionAndNodes attributeValue = new SkipMaxPageColumnTestAttributeFactory().create();
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
	void testAppliesTo_givenSkipPageColumn30_5_givenComponentCode1_1_returnTrue() {
		String expression = createSkipMaxPageColumnAttributeExpression(30,5);
		ComponentCodeNode componentCode = new ComponentCodeNode(1, 'U', 1);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parsePageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenSkipPageColumn30_5_givenComponentCode30_2_returnTrue() {
		String expression = createSkipMaxPageColumnAttributeExpression(30,5);
		ComponentCodeNode componentCode = new ComponentCodeNode(30, 'U', 2);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parsePageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}

	@Test
	void testAppliesTo_givenSkipPageColumn30_5_givenComponentCode30_5_returnTrue() {
		String expression = createSkipMaxPageColumnAttributeExpression(30,5);
		ComponentCodeNode componentCode = new ComponentCodeNode(30, 'U', 5);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parsePageColumnRangeNode(expression);

		assertThat(skipPageColumnRangeNode.appliesTo(componentCode)).isTrue();
	}
	
	@Test
	void testAppliesTo_givenSkipPageColumn30_5_givenComponentCode30_6_returnFalse() {
		String expression = createSkipMaxPageColumnAttributeExpression(30,5);
		ComponentCodeNode componentCode = new ComponentCodeNode(30, 'U', 6);
		SkipPageColumnRangeNode skipPagePageColumnRangeNode = parsePageColumnRangeNode(expression);

		assertThat(skipPagePageColumnRangeNode.appliesTo(componentCode)).isFalse();
	}

	@Test
	void testGoToNext_givenSkipPageColumn30_5_givenComponentCode1_1_makesComponentCode30_6() {
		String expression = createSkipMaxPageColumnAttributeExpression(30,5);
		ComponentCodeNode componentCode = new ComponentCodeNode(1, 'U', 1);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parsePageColumnRangeNode(expression);
		skipPageColumnRangeNode.goToNext(componentCode);

		assertThat(componentCode)//
				.hasFieldOrPropertyWithValue("page", 30)//
				.hasFieldOrPropertyWithValue("column", 6);
	}

	@Test
	void testGoToNext_givenSkipPageColumn30_5_givenComponentCode30_5_makesComponentCode30_6() {
		String expression = createSkipMaxPageColumnAttributeExpression(30,5);
		ComponentCodeNode componentCode = new ComponentCodeNode(30, 'U', 5);
		SkipPageColumnRangeNode skipPageColumnRangeNode = parsePageColumnRangeNode(expression);
		skipPageColumnRangeNode.goToNext(componentCode);

		assertThat(componentCode)//
				.hasFieldOrPropertyWithValue("page", 30)//
				.hasFieldOrPropertyWithValue("column", 6);
	}

	private SkipPageColumnRangeNode parsePageColumnRangeNode(String expression) {
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

	private String createSkipMaxPageColumnAttributeExpression(int page, int column) {
		ExpressionAndNodes attributeValue = new SkipMaxPageColumnTestAttributeFactory().create(page, column);
		ExpressionAndNodes attribute = TestObjectFactory.bracedAttribute(BracedAttributeName.SKIP, attributeValue);
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.braceNode(attribute);
		return expressionAndNodes.expression();
	}
}

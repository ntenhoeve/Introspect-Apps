package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class BracedAttributeRuleTest {

	@RepeatedTest(30)
	void test_givenValidExpression_returnValidParseTree() {
		
		
		ExpressionAndNodes attribute = TestObjectFactory.bracedAttributeWithRandomValues(BracedAttributeName.SKIP);
		ExpressionAndNodes braced = TestObjectFactory.braceNode(attribute);
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.surroundWithRandomTokens(braced);

		TokenParser tokenParser = new TokenParser(TokenRules.all());
		String expression=expressionAndNodes.expression();
		List<Token> tokens = tokenParser.parse(expression);
		List<NodeParserRule> nodeRules=createNodeRules();
		NodeParser nodeParser = new NodeParser(nodeRules);
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getNodes();
		List<Node> parcedNodes = expressionAndNodes.parcedNodes();
		assertThat(actual).as("expression=%s",expression) .containsExactlyElementsOf(parcedNodes);
	}

	private List<NodeParserRule> createNodeRules() {
		List<NodeParserRule> rules=new ArrayList<>();
		rules.add(new BraceRule());
		rules.add(new BracedAttributeRule());
		return rules;
	}

}

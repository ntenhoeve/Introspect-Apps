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

class BraceRuleTest {

	@RepeatedTest(15)
	void test_givenAckNodeSurroundedWithRandomNodes_returnValidParseTree() {
		ExpressionAndNodes children = TestObjectFactory.tokenNodeRandom().repeatRandomly(1, 5);
		ExpressionAndNodes braceNode=TestObjectFactory.braceNode(children);
		ExpressionAndNodes surroundedWithRandomNodes = TestObjectFactory.surroundWithRandomTokens(braceNode);
		String expression = surroundedWithRandomNodes.expression();

		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(expression);
		List<NodeParserRule> nodeParserRules = new ArrayList<>();
		nodeParserRules.add(new BraceRule());
		NodeParser nodeParser = new NodeParser(nodeParserRules);
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getNodes();
		assertThat(actual).as("expression=%s", expression).containsExactlyElementsOf(surroundedWithRandomNodes.parcedNodes());
	}

}

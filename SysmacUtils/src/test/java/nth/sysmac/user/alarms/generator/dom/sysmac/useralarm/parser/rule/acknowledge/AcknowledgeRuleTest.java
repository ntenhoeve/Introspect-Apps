package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.RepeatedTest;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.NodeParserRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class AcknowledgeRuleTest {

	@RepeatedTest(30)
	void test_givenValidExpression_returnValidParseTree() {
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeRandomRest().repeatRandomly(0, 2)//
				.append(TestObjectFactory.acknowledgeNode())//
				.append(TestObjectFactory.tokenNodeRandomRest().repeatRandomly(0, 2));
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		String expression = expressionAndNodes.expression();
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getNodes();
		List<Node> parsedNodes = expressionAndNodes.parcedNodes();
		assertThat(actual).as("expression=%s",expression). containsExactlyElementsOf(parsedNodes);
	}

}

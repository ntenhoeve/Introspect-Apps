package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.NodeParserRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class AcknowledgeRuleTest {

	@ParameterizedTest
	@MethodSource
	void test_givenValidExpression_returnValidParseTree(String expression, List<Node> expected) {
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getChildren();
		assertThat(actual).containsExactlyElementsOf(expected);
	}

	private static Stream<Arguments> test_givenValidExpression_returnValidParseTree() {
		return Stream.of(
				TestObjectFactory.acknowledgeNode("{ack}").arguments(),
				TestObjectFactory.acknowledgeNode("{ACK}").arguments(),
				TestObjectFactory.acknowledgeNode("{ ack }").arguments(),
				TestObjectFactory.acknowledgeNode("{ ACK }").arguments(),
				TestObjectFactory.acknowledgeNode("{\t ack  }").arguments(),
				TestObjectFactory.acknowledgeNode("{\t ACK  }").arguments(),
				TestObjectFactory.acknowledgeNode().append(TestObjectFactory.tokenNodeRest()).arguments(),
				TestObjectFactory.tokenNodeRest().append(TestObjectFactory.acknowledgeNode()).arguments(),
				TestObjectFactory.tokenNodeRest().append(TestObjectFactory.acknowledgeNode())
						.append(TestObjectFactory.tokenNodeRest()).arguments());
	}

}

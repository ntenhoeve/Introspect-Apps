package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.ParseTree;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule.NodeRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Token;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class BraceReplacementTest {

	@ParameterizedTest
	@MethodSource
	void test_givenValidExpression_returnValidParseTree(String expression, List<Node> expected) {
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeRules.all());
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getChildren();
		assertThat(actual).containsAll(expected);
	}

	private static Stream<Arguments> test_givenValidExpression_returnValidParseTree() {
		return Stream.of(
				TestObjectFactory.braceNode().arguments(),
				TestObjectFactory.braceNode().append(TestObjectFactory.tokenNodeRest()).arguments(),
				TestObjectFactory.tokenNodeRest().append(TestObjectFactory.braceNode()).arguments(),
				TestObjectFactory.tokenNodeRest().append(TestObjectFactory.braceNode()).append(TestObjectFactory.tokenNodeRest()).arguments());
	}

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeParserRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.ParseTree;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule.BraceRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Token;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class BraceRuleTest {

	@ParameterizedTest
	@MethodSource
	void test_givenValidExpression_returnValidParseTree(String expression, List<Node> expected) {
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(expression);
		List<NodeParserRule> nodeParserRules = new ArrayList<>();
		nodeParserRules.add(new BraceRule());
		NodeParser nodeParser = new NodeParser(nodeParserRules);
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getChildren();
		assertThat(actual).containsExactlyElementsOf(expected);
	}

	private static Stream<Arguments> test_givenValidExpression_returnValidParseTree() {
		return Stream.of(//
				TestObjectFactory.braceNode().arguments(), //
				TestObjectFactory.braceNode()//
						.append(TestObjectFactory.tokenNodeRest()).arguments(),
				TestObjectFactory.tokenNodeRest()//
						.append(TestObjectFactory.braceNode()).arguments(),
				TestObjectFactory.tokenNodeRest()//
						.append(TestObjectFactory.braceNode())//
						.append(TestObjectFactory.tokenNodeRest()).arguments(),
				TestObjectFactory.braceNode(//
						TestObjectFactory.tokenNodeOpenBrace()//
								.append(TestObjectFactory.tokenNodewhiteSpace())//
								.append(TestObjectFactory.tokenNodeRest("Ack"))//
								.append(TestObjectFactory.tokenNodewhiteSpace())//
								.append(TestObjectFactory.tokenNodeCloseBrace())//
								)
						.arguments());

	}

}

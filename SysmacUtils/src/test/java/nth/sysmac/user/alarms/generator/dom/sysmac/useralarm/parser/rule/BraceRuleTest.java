package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
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
								.append(TestObjectFactory.tokenNodeWhiteSpace())//
								.append(TestObjectFactory.tokenNodeRest("Ack"))//
								.append(TestObjectFactory.tokenNodeWhiteSpace())//
								.append(TestObjectFactory.tokenNodeCloseBrace())//
								)
						.arguments());

	}

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.provider.MethodSource;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class ComponentCodeRuleTest {

	private static final List<NodeParserRule> NODE_PARSE_RULES = Arrays.asList(new ComponentCodeRule());

	@RepeatedTest(30)
	@MethodSource
	void test_givenValidExpression_returnValidParseTree() {
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeRandomRest().repeatRandomly(0, 2)//
				.append(TestObjectFactory.componentCodeNode())//
				.append(TestObjectFactory.tokenNodeRandomRest().repeatRandomly(0, 2));
		ParseTree parseTree = parse(expressionAndNodes);
		List<Node> actual = parseTree.getNodes();
		List<Node> parcedNodes = expressionAndNodes.parcedNodes();
		assertThat(actual).containsExactlyElementsOf(parcedNodes);
	}

	private ParseTree parse(ExpressionAndNodes expressionAndNodes) {
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		String expression=expressionAndNodes.expression();
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NODE_PARSE_RULES);
		ParseTree parseTree = nodeParser.parse(tokens);
		return parseTree;
	}

}

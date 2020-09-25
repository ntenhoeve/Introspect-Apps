package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.provider.MethodSource;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.NodeParserRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class PriorityRuleTest {
	@RepeatedTest(30)
	@MethodSource
	void test_givenValidExpression_returnValidParseTree() {
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeRandomRest().repeatRandomly(0, 2)//
				.append(TestObjectFactory.priorityNode())//
				.append(TestObjectFactory.tokenNodeRandomRest().repeatRandomly(0, 2));
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		String expression = expressionAndNodes.expression();
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getNodes();
		List<Node> parcedNodes = expressionAndNodes.parcedNodes();
		assertThat(actual).containsExactlyElementsOf(parcedNodes);
	}

	@RepeatedTest(30)
	@MethodSource
	void test_givenInvalidExpression_throwsError() {
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeOpenBrace()//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(TestObjectFactory.tokenNodeRest(Random.letterCase("p").generate()))//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(TestObjectFactory.tokenNodeEqual())//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(TestObjectFactory.tokenNodeRest("invalid"))//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(TestObjectFactory.tokenNodeCloseBrace());
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		String expression = expressionAndNodes.expression();
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		assertThatThrownBy(() -> nodeParser.parse(tokens)).isInstanceOf(RuntimeException.class)
				.hasMessage("Could not find a matching Priority for abbreviation: invalid");
	}
}

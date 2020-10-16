package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

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

class DerivedComponentCodeRuleTest {

	@RepeatedTest(30)
	void test_given_validExpression_resultsInValidParseTree() {
		ExpressionAndNodes derivedComponentCode = new DerivedComponentCodeTestFactory().create();
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.surroundWithRandomTokens(derivedComponentCode);
		String expression = expressionAndNodes.expression();
		ParseTree parseTree= parse(expression);
		List<Node> actual = parseTree.getNodes();
		List<Node> parcedNodes = expressionAndNodes.parcedNodes();
		assertThat(actual).as("expression=%s", expression).containsExactlyElementsOf(parcedNodes);
	}
	


	private ParseTree parse(String expression) {
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(expression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		ParseTree parseTree = nodeParser.parse(tokens);
		return parseTree;
	}


}

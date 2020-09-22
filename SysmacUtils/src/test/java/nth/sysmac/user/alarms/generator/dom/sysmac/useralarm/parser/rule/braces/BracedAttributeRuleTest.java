package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.provider.MethodSource;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class BracedAttributeRuleTest {

	@RepeatedTest(30)
	@MethodSource
	void test_givenValidExpression_returnValidParseTree() {
		ExpressionAndNodes expressionAndNodes = createExpressionAndNodes();
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		String expression=expressionAndNodes.expression();
		List<Token> tokens = tokenParser.parse(expression);
		List<NodeParserRule> nodeRules=createNodeRules();
		NodeParser nodeParser = new NodeParser(nodeRules);
		ParseTree parseTree = nodeParser.parse(tokens);
		List<Node> actual = parseTree.getNodes();
		List<Node> parcedNodes = expressionAndNodes.parcedNodes();
		assertThat(actual).containsExactlyElementsOf(parcedNodes);
	}

	private List<NodeParserRule> createNodeRules() {
		List<NodeParserRule> rules=new ArrayList<>();
		rules.add(new BraceRule());
		rules.add(new BracedAttributeRule());
		return rules;
	}


	private ExpressionAndNodes createExpressionAndNodes() {
		List<ExpressionAndNodes> attributes = createAttributes();
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeRest().repeatRandomly(0, 2)//
				.append(TestObjectFactory.braceNodeWithAttributes(attributes))//
				.append(TestObjectFactory.tokenNodeRest().repeatRandomly(0, 2));
		return expressionAndNodes;
	}

	private List<ExpressionAndNodes> createAttributes() {
		List<ExpressionAndNodes> attributes=new ArrayList<>();
		ExpressionAndNodes firstAttribute=TestObjectFactory.attribute();
		attributes.add(firstAttribute);
		int nrOtherAttributes=Random.integer().forRange(0, 3).generate();
		for(int i=0;i<nrOtherAttributes;i++) {
			ExpressionAndNodes otherAttribute=TestObjectFactory.attribute();
			attributes.add(otherAttribute);
		}
		return attributes;
	}
}

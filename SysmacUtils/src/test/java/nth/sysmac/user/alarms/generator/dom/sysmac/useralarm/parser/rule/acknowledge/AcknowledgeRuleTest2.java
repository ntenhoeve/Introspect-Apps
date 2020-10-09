package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.reflect.util.random.Random;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class AcknowledgeRuleTest2 {
	private static final Regex REGEX_ACK_TEXT = new Regex().ignoreCase().beginOfLine().literal("ack").endOfLine();

	private static final MatchRules CHILD_MATCH_RULES = new MatchRules()//
			.firstMatchMustBeFirstNode() //
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(TokenNodePredicate.rest(REGEX_ACK_TEXT))//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.lastMatchMustBeLastNode();
	
	@RepeatedTest(30)
	void test_givenValidExpression_returnValidParseTree() {
		String ack = Random.letterCase("ack").generate();

		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 3)//
				.append(TestObjectFactory.tokenNodeRest(ack))//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 3));
		
		NodeMatcher nodeMatcher=new NodeMatcher(CHILD_MATCH_RULES);
		List<Node> tokenNodes = new ArrayList<>(expressionAndNodes.tokenNodes());
		MatchResults matchResults= nodeMatcher.match(tokenNodes);
		assertThat(matchResults.hasResults()).as("expression=%s",expressionAndNodes.expression()).isTrue();
	}

}

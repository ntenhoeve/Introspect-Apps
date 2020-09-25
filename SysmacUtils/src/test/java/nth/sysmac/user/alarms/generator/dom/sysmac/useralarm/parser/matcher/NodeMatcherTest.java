package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.matcher;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.predicate.AnyNodePredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class NodeMatcherTest {

	private static final AnyNodePredicate ANY_NODE_PREDICATE = new AnyNodePredicate();
	private static final MatchRules OPEN_BRACE_RULE = new MatchRules().add(TokenNodePredicate.openBrace());
	private static final MatchRules BETWEEN_BRACES_RULE = new MatchRules().add(ANY_NODE_PREDICATE,
			Repetition.zeroOrMore());
	private static final MatchRules CLOSE_BRACE_RULE = new MatchRules().add(TokenNodePredicate.closeBrace());

	@RepeatedTest(15)
	void testMatch_givenPattern_mustFindCorrectNodes() {
		ExpressionAndNodes children = TestObjectFactory.tokenNodeRandom().repeatRandomly(2, 5);
		ExpressionAndNodes braceNode = TestObjectFactory.braceNode(children);
		ExpressionAndNodes surroundWithRandomTokens = TestObjectFactory.surroundWithRandomTokens(braceNode);
		List<Node> nodesToMatch = new ArrayList<>(surroundWithRandomTokens.tokenNodes());

		MatchRules matchRules = new MatchRules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(nodesToMatch);
		assertThat(matchResults.getNodes()).containsAll(braceNode.tokenNodes());
	}

	@RepeatedTest(15)
	void testMatch_givenFollowedByRandomNodes_givenFirstMatchMustBeFirstNode_mustFindCorrectNodes() {
		ExpressionAndNodes children = TestObjectFactory.tokenNodeRandom().repeatRandomly(2, 5);
		ExpressionAndNodes braceNode = TestObjectFactory.braceNode(children);
		ExpressionAndNodes followedWithRandomNodes = braceNode
				.append(TestObjectFactory.tokenNodeRandom().repeatRandomly(1, 5));
		List<Node> nodesToMatch = new ArrayList<>(followedWithRandomNodes.tokenNodes());

		MatchRules matchRules = new MatchRules()//
				.firstMatchMustBeFirstNode()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(nodesToMatch);
		assertThat(matchResults.getNodes()).containsAll(braceNode.tokenNodes());
	}

	@RepeatedTest(15)
	void testMatch_givenPrecededWithRandomNodes_givenFirstMatchMustBeFirstNode_hasNoResults() {

		ExpressionAndNodes children = TestObjectFactory.tokenNodeRandom().repeatRandomly(2, 5);
		ExpressionAndNodes braceNode = TestObjectFactory.braceNode(children);
		ExpressionAndNodes precedingWithRandomNodes = TestObjectFactory.tokenNodeRandom().repeatRandomly(1, 5)
				.append(braceNode);
		List<Node> nodesToMatch = new ArrayList<>(precedingWithRandomNodes.tokenNodes());

		MatchRules matchRules = new MatchRules()//
				.firstMatchMustBeFirstNode()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(nodesToMatch);
		assertThat(matchResults.hasResults()).isFalse();
	}

	@RepeatedTest(15)
	void testMatch_givenPrecededByRandomNodes_givenLastMatchMustBeLastNode_mustFindCorrectNodes() {

		ExpressionAndNodes children = TestObjectFactory.tokenNodeRandom().repeatRandomly(2, 5);
		ExpressionAndNodes braceNode = TestObjectFactory.braceNode(children);
		ExpressionAndNodes precedingWithRandomTokens = TestObjectFactory.precedingWithRandomTokens(braceNode);

		MatchRules matchRules = new MatchRules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE)//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(new ArrayList<>(precedingWithRandomTokens.tokenNodes()));
		assertThat(matchResults.getNodes()).containsAll(braceNode.tokenNodes());
	}

	@RepeatedTest(15)
	void testMatch_givenFollowedByRandomNoded_givenLastMatchMustBeLastNode_hasNoResults() {

		ExpressionAndNodes children = TestObjectFactory.tokenNodeRandom().repeatRandomly(2, 5);
		ExpressionAndNodes braceNode = TestObjectFactory.braceNode(children);
		ExpressionAndNodes followedWithRandomTokens = braceNode
				.append(TestObjectFactory.tokenNodeRandom().repeatRandomly(1, 5));

		MatchRules matchRules = new MatchRules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE)//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(new ArrayList<>(followedWithRandomTokens.tokenNodes()));
		assertThat(matchResults.hasResults()).isFalse();
	}

}

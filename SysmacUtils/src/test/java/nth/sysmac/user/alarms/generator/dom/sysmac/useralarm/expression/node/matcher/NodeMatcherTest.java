package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.predicate.AnyNodePredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class NodeMatcherTest {

	private static final AnyNodePredicate ANY_NODE_PREDICATE = new AnyNodePredicate();
	private static final MatchRules OPEN_BRACE_RULE = new MatchRules().add(TokenNodePredicate.openBrace());
	private static final MatchRules BETWEEN_BRACES_RULE = new MatchRules().add(ANY_NODE_PREDICATE, Repetition.zeroOrMore());
	private static final MatchRules CLOSE_BRACE_RULE = new MatchRules().add(TokenNodePredicate.closeBrace());


	@ParameterizedTest
	@MethodSource
	void testMatch_givenPattern_mustFindCorrectNodes(List<Node> nodesToMatch, List<Node> extectedNodesToFind) {
			MatchRules matchRules = new MatchRules()//
					.add(OPEN_BRACE_RULE)//
					.add(BETWEEN_BRACES_RULE)//
					.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(nodesToMatch);
		assertThat(matchResults.getNodes()).containsAll(extectedNodesToFind);
	}

	static Stream<Arguments> testMatch_givenPattern_mustFindCorrectNodes() {
		return Stream.of(
				Arguments.of(TestObjectFactory.braceNode().tokenNodes(), TestObjectFactory.braceNode().tokenNodes()),
				Arguments.of(TestObjectFactory.braceNode().append(TestObjectFactory.tokenNodeRest()).tokenNodes(),
						TestObjectFactory.braceNode().tokenNodes()),
				Arguments.of(TestObjectFactory.tokenNodeRest().append(TestObjectFactory.braceNode()).tokenNodes(),
						TestObjectFactory.braceNode().tokenNodes()));
	}

	@ParameterizedTest
	@MethodSource
	void testMatch_givenPatternFirstMatchMustBeFirstNode_mustFindCorrectNodes(List<Node> nodesToMatch,
			List<Node> extectedNodesToFind) {
		MatchRules matchRules = new MatchRules()//
				.firstMatchMustBeFirstNode()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(nodesToMatch);
		assertThat(matchResults.getNodes()).containsAll(extectedNodesToFind);
	}

	static Stream<Arguments> testMatch_givenPatternFirstMatchMustBeFirstNode_mustFindCorrectNodes() {
		return Stream.of(
				Arguments.of(TestObjectFactory.braceNode().tokenNodes(), TestObjectFactory.braceNode().tokenNodes()),
				Arguments.of(TestObjectFactory.braceNode().append(TestObjectFactory.tokenNodeRest()).tokenNodes(),
						TestObjectFactory.braceNode().tokenNodes()));
	}

	@ParameterizedTest
	@MethodSource
	void testMatch_givenPatternFirstMatchMustBeFirstNode_hasNoResults(List<Node> nodesToMatch,
			List<Node> extectedNodesToFind) {
		MatchRules matchRules = new MatchRules()//
				.firstMatchMustBeFirstNode()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(nodesToMatch);
		assertThat(matchResults.hasResults()).isFalse();
	}

	static Stream<Arguments> testMatch_givenPatternFirstMatchMustBeFirstNode_hasNoResults() {
		return Stream
				.of(Arguments.of(TestObjectFactory.tokenNodeRest().append(TestObjectFactory.braceNode()).tokenNodes(),
						new ArrayList<TokenNode>()));
	}

	@ParameterizedTest
	@MethodSource
	void testMatch_givenPatternLastMatchMustBeLastNode_mustFindCorrectNodes(List<Node> nodesToMatch,
			List<Node> extectedNodesToFind) {
		MatchRules matchRules = new MatchRules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE)//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(nodesToMatch);
		assertThat(matchResults.getNodes()).containsAll(extectedNodesToFind);
	}

	static Stream<Arguments> testMatch_givenPatternLastMatchMustBeLastNode_mustFindCorrectNodes() {
		return Stream.of(
				Arguments.of(TestObjectFactory.braceNode().tokenNodes(), TestObjectFactory.braceNode().tokenNodes()),
				Arguments.of(TestObjectFactory.tokenNodeRest().append(TestObjectFactory.braceNode()).tokenNodes(),
						TestObjectFactory.braceNode().tokenNodes()));
	}

	@ParameterizedTest
	@MethodSource
	void testMatch_givenPatternLastMatchMustBeLastNode_hasNoResults(List<Node> nodesToMatch,
			List<Node> extectedNodesToFind) {
		MatchRules matchRules = new MatchRules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE)//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(nodesToMatch);
		assertThat(matchResults.hasResults()).isFalse();
	}

	static Stream<Arguments> testMatch_givenPatternLastMatchMustBeLastNode_hasNoResults() {
		return Stream
				.of(Arguments.of(TestObjectFactory.braceNode().append(TestObjectFactory.tokenNodeRest()).tokenNodes(),
						new ArrayList<TokenNode>()));
	}
	
}

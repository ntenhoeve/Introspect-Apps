package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.NoResultsFoundException;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class NodeMatcherTest {

	private static final Rules OPEN_BRACE_RULE = new Rules().add(TokenNodePredicate.openBrace());
	private static final Rules BETWEEN_BRACES_RULE = new Rules().add(NodePredicate.any(), Repetition.zeroOrMore());
	private static final Rules CLOSE_BRACE_RULE = new Rules().add(TokenNodePredicate.closeBrace());


	@ParameterizedTest
	@MethodSource
	void testMatch_givenPattern_mustFindCorrectNodes(List<Node> nodesToMatch, List<Node> extectedNodesToFind) {
			Rules rules = new Rules()//
					.add(OPEN_BRACE_RULE)//
					.add(BETWEEN_BRACES_RULE)//
					.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(rules);
		Results results = nodeMatcher.match(nodesToMatch);
		assertThat(results.getNodes()).containsAll(extectedNodesToFind);
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
		Rules rules = new Rules()//
				.firstMatchMustBeFirstNode()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(rules);
		Results results = nodeMatcher.match(nodesToMatch);
		assertThat(results.getNodes()).containsAll(extectedNodesToFind);
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
		Rules rules = new Rules()//
				.firstMatchMustBeFirstNode()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(rules);
		Results results = nodeMatcher.match(nodesToMatch);
		assertThat(results.hasResults()).isFalse();
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
		Rules rules = new Rules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE)//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(rules);
		Results results = nodeMatcher.match(nodesToMatch);
		assertThat(results.getNodes()).containsAll(extectedNodesToFind);
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
		Rules rules = new Rules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE)//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(rules);
		Results results = nodeMatcher.match(nodesToMatch);
		assertThat(results.hasResults()).isFalse();
	}

	static Stream<Arguments> testMatch_givenPatternLastMatchMustBeLastNode_hasNoResults() {
		return Stream
				.of(Arguments.of(TestObjectFactory.braceNode().append(TestObjectFactory.tokenNodeRest()).tokenNodes(),
						new ArrayList<TokenNode>()));
	}
	
}

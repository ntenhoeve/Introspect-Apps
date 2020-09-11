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
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResult;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.NoResultsFoundException;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.RequiredGroupResultFilter;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

class NodeMatcherTest {

	private static final String CLOSE_BRACE = "closeBrace";
	private static final String OPEN_BRACE = "openBrace";
	private static final String BETWEEN_BRACES = "betweenBraces";

	@ParameterizedTest
	@MethodSource
	void testMatch_givenPattern_mustFindCorrectNodes(List<Node> nodesToMatch, List<Node> extectedNodesToFind) {
		MatchPattern pattern = new MatchPattern()//
				.newGroup(OPEN_BRACE).node(TokenNodePredicate.openBrace())//
				.newGroup(BETWEEN_BRACES).node(NodePredicate.any(), Repetition.zeroOrMore())//
				.newGroup(CLOSE_BRACE).node(TokenNodePredicate.closeBrace());

		NodeMatcher nodeMatcher = new NodeMatcher(pattern);
		MatchResult matchResult = nodeMatcher.match(nodesToMatch);
		assertThat(matchResult.getChildren(new RequiredGroupResultFilter())).containsAll(extectedNodesToFind);
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
		MatchPattern pattern = new MatchPattern()//
				.firstMatchMustBeFirstNode()//
				.newGroup(OPEN_BRACE).node(TokenNodePredicate.openBrace())//
				.newGroup(BETWEEN_BRACES).node(NodePredicate.any(), Repetition.zeroOrMore())//
				.newGroup(CLOSE_BRACE).node(TokenNodePredicate.closeBrace());

		NodeMatcher nodeMatcher = new NodeMatcher(pattern);
		MatchResult matchResult = nodeMatcher.match(nodesToMatch);
		assertThat(matchResult.getChildren(new RequiredGroupResultFilter())).containsAll(extectedNodesToFind);
	}

	static Stream<Arguments> testMatch_givenPatternFirstMatchMustBeFirstNode_mustFindCorrectNodes() {
		return Stream.of(
				Arguments.of(TestObjectFactory.braceNode().tokenNodes(), TestObjectFactory.braceNode().tokenNodes()),
				Arguments.of(TestObjectFactory.braceNode().append(TestObjectFactory.tokenNodeRest()).tokenNodes(),
						TestObjectFactory.braceNode().tokenNodes()));
	}

	@ParameterizedTest
	@MethodSource
	void testMatch_givenPatternFirstMatchMustBeFirstNode_mustThrowError(List<Node> nodesToMatch,
			List<Node> extectedNodesToFind) {
		MatchPattern pattern = new MatchPattern()//
				.firstMatchMustBeFirstNode()//
				.newGroup(OPEN_BRACE).node(TokenNodePredicate.openBrace())//
				.newGroup(BETWEEN_BRACES).node(NodePredicate.any(), Repetition.zeroOrMore())//
				.newGroup(CLOSE_BRACE).node(TokenNodePredicate.closeBrace());

		NodeMatcher nodeMatcher = new NodeMatcher(pattern);
		MatchResult matchResult = nodeMatcher.match(nodesToMatch);
		assertThatThrownBy(() -> matchResult.getChildren(new RequiredGroupResultFilter()))
				.hasMessageContaining(NoResultsFoundException.MESSAGE);
	}

	static Stream<Arguments> testMatch_givenPatternFirstMatchMustBeFirstNode_mustThrowError() {
		return Stream
				.of(Arguments.of(TestObjectFactory.tokenNodeRest().append(TestObjectFactory.braceNode()).tokenNodes(),
						new ArrayList<TokenNode>()));
	}

	@ParameterizedTest
	@MethodSource
	void testMatch_givenPatternLastMatchMustBeLastNode_mustFindCorrectNodes(List<Node> nodesToMatch,
			List<Node> extectedNodesToFind) {
		MatchPattern pattern = new MatchPattern()//
				.newGroup(OPEN_BRACE).node(TokenNodePredicate.openBrace())//
				.newGroup(BETWEEN_BRACES).node(NodePredicate.any(), Repetition.zeroOrMore())//
				.newGroup(CLOSE_BRACE).node(TokenNodePredicate.closeBrace())//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(pattern);
		MatchResult matchResult = nodeMatcher.match(nodesToMatch);
		assertThat(matchResult.getChildren(new RequiredGroupResultFilter())).containsAll(extectedNodesToFind);
	}

	static Stream<Arguments> testMatch_givenPatternLastMatchMustBeLastNode_mustFindCorrectNodes() {
		return Stream.of(
				Arguments.of(TestObjectFactory.braceNode().tokenNodes(), TestObjectFactory.braceNode().tokenNodes()),
				Arguments.of(TestObjectFactory.tokenNodeRest().append(TestObjectFactory.braceNode()).tokenNodes(),
						TestObjectFactory.braceNode().tokenNodes()));
	}

	@ParameterizedTest
	@MethodSource
	void testMatch_givenPatternLastMatchMustBeLastNode_mustThrowError(List<Node> nodesToMatch,
			List<Node> extectedNodesToFind) {
		MatchPattern pattern = new MatchPattern()//
				.newGroup(OPEN_BRACE).node(TokenNodePredicate.openBrace())//
				.newGroup(BETWEEN_BRACES).node(NodePredicate.any(), Repetition.zeroOrMore())//
				.newGroup(CLOSE_BRACE).node(TokenNodePredicate.closeBrace())//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(pattern);
		MatchResult matchResult = nodeMatcher.match(nodesToMatch);
		assertThatThrownBy(() -> matchResult.getChildren(new RequiredGroupResultFilter()))
				.hasMessageContaining(NoResultsFoundException.MESSAGE);
	}

	static Stream<Arguments> testMatch_givenPatternLastMatchMustBeLastNode_mustThrowError() {
		return Stream
				.of(Arguments.of(TestObjectFactory.braceNode().append(TestObjectFactory.tokenNodeRest()).tokenNodes(),
						new ArrayList<TokenNode>()));
	}
	
}

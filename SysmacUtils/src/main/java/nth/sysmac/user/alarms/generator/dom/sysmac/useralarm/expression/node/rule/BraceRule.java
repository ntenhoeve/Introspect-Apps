package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResult;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.GroupNameResultFilter;

public class BraceRule implements NodeRule<BraceNode> {

	private static final String CLOSE_BRACE = "closeBrace";
	private static final String OPEN_BRACE = "openBrace";
	private static final String BETWEEN_BRACES = "betweenBraces";

	@Override
	public MatchResult find(List<Node> children) {
		MatchPattern pattern = new MatchPattern()//
				.newGroup(OPEN_BRACE)//
				.node(TokenNodePredicate.openBrace())//
				.newGroup(BETWEEN_BRACES)//
				.node(NodePredicate.any(), Repetition.zeroOrMore())//
				.newGroup(CLOSE_BRACE)//
				.node(TokenNodePredicate.closeBrace());

		NodeMatcher nodeMatcher = new NodeMatcher(pattern);
		MatchResult matchResult = nodeMatcher.match(children);
		return matchResult;
	}

	@Override
	public BraceNode createReplacement(MatchResult matchResult) {
		GroupNameResultFilter groupNameFilter = new GroupNameResultFilter(BETWEEN_BRACES);
		List<Node> childrenBetweenBrackets = matchResult.getChildren(groupNameFilter);
		BraceNode braceNode = new BraceNode(childrenBetweenBrackets);
		return braceNode;
	}
}

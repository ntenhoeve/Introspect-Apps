package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.predicate;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResults;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.MatchRules;

public class NodeTypeAndMatchChildrenPredicate implements Predicate<Node> {

	private final Class<? extends Node> nodeType;
	private final MatchRules childMatchRules;

	public NodeTypeAndMatchChildrenPredicate(Class<? extends Node> nodeType, MatchRules childMatchRules) {
		this.nodeType = nodeType;
		this.childMatchRules = childMatchRules;
	}
	
	@Override
	public boolean test(Node node) {
		if (!nodeType.isInstance(node)) {
			return false;
		}
		boolean childrenMatch = childrenMatch(node);
		return childrenMatch;
	}

	private boolean childrenMatch(Node node) {
		NodeMatcher nodeMatcher = new NodeMatcher(childMatchRules);
		MatchResults matchResults = nodeMatcher.match(node.getChildren());
		boolean matches = matchResults.hasResults();
		return matches;
	}

}

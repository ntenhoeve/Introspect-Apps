package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;

public class NodeTypeAndMatchChildrenPredicate implements Predicate<Node> {

	private final Class<? extends Node> nodeType;
	private final Rules childMatchRules;

	public NodeTypeAndMatchChildrenPredicate(Class<? extends Node> nodeType, Rules childMatchRules) {
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
		Results results = nodeMatcher.match(node.getChildren());
		boolean matches = results.hasResults();
		return matches;
	}

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.impl;

import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeChildren;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeReplacement;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;

public class AcknowledgeReplacement implements NodeReplacement<AcknowledgeNode> {

	@Override
	public MatchResult find(NodeChildren children) {
		Predicate<Node> predicate = createPredicate();
		NodeMatcher nodeMatcher=new NodeMatcher();
		MatchResult matchResult=nodeMatcher.find(children, predicate);
		return matchResult;
	}

	private Predicate<Node> createPredicate() {
		return new Predicate<Node>() {
			@Override
			public boolean test(Node node) {
				boolean found = isBraceNodeContainingOnlyAckOrWhiteSpaces(node);
				return found;
			}
		};
	}

	private boolean isBraceNodeContainingOnlyAckOrWhiteSpaces(Node node) {
		if (node instanceof BraceNode) {
			boolean found = containingOnlyAckOrWhiteSpaces(node.getChildren());
			return found;
		}
		return false;
	}

	private boolean containingOnlyAckOrWhiteSpaces(NodeChildren children) {
		boolean foundAckText = false;
		for (Node child : children) {
			boolean isWhiteSpace = TokenNodePredicate.whiteSpace().test(child);
			boolean isRest = TokenNodePredicate.rest(new Regex().ignoreCase().beginOfLine().literal("ack").endOfLine())
					.test(child);
			if (isRest) {
				if (foundAckText) {
					// multiple ack's
					return false;
				}
				foundAckText = true;
			} else if (!isWhiteSpace) {
				return false;
			}
		}
		return true;
	}

	@Override
	public AcknowledgeNode createReplacement(MatchResult matchResult) {
		return new AcknowledgeNode();
	}
}

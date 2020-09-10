package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;

public class AcknowledgeRule implements NodeRule<AcknowledgeNode> {

	@Override
	public MatchResult find(List<Node> children) {
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

	private boolean containingOnlyAckOrWhiteSpaces(List<Node> children) {
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

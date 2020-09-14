package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;

public class AcknowledgeRule implements NodeRule<AcknowledgeNode> {

	private static final Regex REGEX_ACK_TEXT = new Regex().ignoreCase().beginOfLine().literal("ack").endOfLine();

	@Override
	public Results find(List<Node> nodes) {
		
		
//		Optional<Node> found=nodes.stream().filter(c-> c instanceof BraceNode).findAny() ;
//		if (found.isPresent() && found.get().getChildren().size()==1 && found.get().getChildren().get(0).toString().toLowerCase().contains("ack")) {
//			System.out.println();
//		}
		
		Predicate<Node> braceNodeWithAckTextPredicate = createBraceNodeWithAckTextPredicate();
		Rules rules = new Rules()//
				.node(braceNodeWithAckTextPredicate);

		NodeMatcher nodeMatcher = new NodeMatcher(rules);
		Results results = nodeMatcher.match(nodes);
		return results;
	}

	private Predicate<Node> createBraceNodeWithAckTextPredicate() {
		return new Predicate<Node>() {
			@Override
			public boolean test(Node node) {
				if (!(node instanceof BraceNode)) {
					return false;
				}
				boolean childrenMatch = childrenMatch(node.getChildren());
				return childrenMatch;
			}
		};
	}


	private boolean childrenMatch(List<Node> children) {
		Rules rules = new Rules()//
				.firstMatchMustBeFirstNode() //
				.node(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.node(TokenNodePredicate.rest(REGEX_ACK_TEXT))//
				.node(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(rules);
		Results results = nodeMatcher.match(children);
		boolean matches = results.hasResults();
		return matches;
	}

	@Override
	public AcknowledgeNode createReplacement(Results results) {
		return new AcknowledgeNode();
	}
}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResult;

public class AcknowledgeRule implements NodeRule<AcknowledgeNode> {

	private static final Regex REGEX_ACK_TEXT = new Regex().ignoreCase().beginOfLine().literal("ack").endOfLine();

	@Override
	public MatchResult find(List<Node> children) {
		
		Optional<Node> found=children.stream().filter(c-> c instanceof BraceNode).findAny() ;
		if (found.isPresent() && found.get().getChildren().size()==1 && found.get().getChildren().get(0).toString().toLowerCase().contains("ack")) {
			System.out.println();
		}
		
		Predicate<Node> braceNodeWithAckTextPredicate = createBraceNodeWithAckTextPredicate();
		MatchPattern pattern = new MatchPattern()//
				.node(braceNodeWithAckTextPredicate);

		NodeMatcher nodeMatcher = new NodeMatcher(pattern);
		MatchResult matchResult = nodeMatcher.match(children);
		return matchResult;
	}

	private Predicate<Node> createBraceNodeWithAckTextPredicate() {
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
		MatchPattern pattern = new MatchPattern()//
				.firstMatchMustBeFirstNode() //
				.node(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.node(TokenNodePredicate.rest(REGEX_ACK_TEXT))//
				.node(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.lastMatchMustBeLastNode();

		NodeMatcher nodeMatcher = new NodeMatcher(pattern);
		MatchResult matchResult = nodeMatcher.match(children);
		boolean matches = matchResult.hasResults();
		return matches;
	}

	@Override
	public AcknowledgeNode createReplacement(MatchResult matchResult) {
		return new AcknowledgeNode();
	}
}

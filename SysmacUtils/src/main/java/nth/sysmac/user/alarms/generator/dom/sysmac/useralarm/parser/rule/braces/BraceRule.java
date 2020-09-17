package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.predicate.AnyNodePredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.result.filter.RulesResultFilter;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.CloseBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.OpenBrace;

public class BraceRule implements NodeParserRule {

	private static final AnyNodePredicate ANY_NODE_PREDICATE = new AnyNodePredicate();
	private static final MatchRules OPEN_BRACE_RULE = new MatchRules().add(TokenNodePredicate.openBrace());
	private static final MatchRules BETWEEN_BRACES_RULE = new MatchRules().add(ANY_NODE_PREDICATE, Repetition.zeroOrMore());
	private static final MatchRules CLOSE_BRACE_RULE = new MatchRules().add(TokenNodePredicate.closeBrace());

	@Override
	public MatchRules getMatchRules() {
		MatchRules matchRules = new MatchRules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);
		return matchRules;
	}

	/**
	 * Replaces {@link TokenNode}s that match {@link #getMatchRules()} and replaces
	 * them with a {@link BraceNode} containing the same nodes except for
	 * {@link TokenNode}s of type {@link OpenBrace} and {@link CloseBrace}
	 */
	@Override
	public void removeOrReplace(MatchResults matchResults) {
		RulesResultFilter filter = new RulesResultFilter(BETWEEN_BRACES_RULE);
		List<Node> nodesBetweenBrackets = matchResults.getFoundNodes(filter);
		BraceNode braceNode = new BraceNode(nodesBetweenBrackets);
		matchResults.replaceMatchingNodesWith(braceNode);
	}
}

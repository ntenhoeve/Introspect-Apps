package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeParserRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.RulesResultFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.CloseBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.OpenBrace;

public class BraceRule implements NodeParserRule {

	private static final Rules OPEN_BRACE_RULE = new Rules().add(TokenNodePredicate.openBrace());
	private static final Rules BETWEEN_BRACES_RULE = new Rules().add(NodePredicate.any(), Repetition.zeroOrMore());
	private static final Rules CLOSE_BRACE_RULE = new Rules().add(TokenNodePredicate.closeBrace());

	@Override
	public Rules getMatchRules() {
		Rules rules = new Rules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);
		return rules;
	}

	/**
	 * Replaces {@link TokenNode}s that match {@link #getMatchRules()} and replaces
	 * them with a {@link BraceNode} containing the same nodes except for
	 * {@link TokenNode}s of type {@link OpenBrace} and {@link CloseBrace}
	 */
	@Override
	public void removeOrReplace(Results results) {
		RulesResultFilter filter = new RulesResultFilter(BETWEEN_BRACES_RULE);
		List<Node> nodesBetweenBrackets = results.getNodes(filter);
		BraceNode braceNode = new BraceNode(nodesBetweenBrackets);
		results.replaceMatchingNodesWith(braceNode);
	}
}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.RulesResultFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;

public class BraceRule implements NodeRule<BraceNode> {

	private static final Rules OPEN_BRACE_RULE = new Rules().add(TokenNodePredicate.openBrace());
	private static final Rules BETWEEN_BRACES_RULE = new Rules().add(NodePredicate.any(), Repetition.zeroOrMore());
	private static final Rules CLOSE_BRACE_RULE = new Rules().add(TokenNodePredicate.closeBrace());

	@Override
	public Results find(List<Node> nodes) {
		Rules rules = new Rules()//
				.add(OPEN_BRACE_RULE)//
				.add(BETWEEN_BRACES_RULE)//
				.add(CLOSE_BRACE_RULE);

		NodeMatcher nodeMatcher = new NodeMatcher(rules);
		Results results = nodeMatcher.match(nodes);
		return results;
	}

	@Override
	public BraceNode createReplacement(Results results) {
		RulesResultFilter filter = new RulesResultFilter(BETWEEN_BRACES_RULE);
		List<Node> nodesBetweenBrackets = results.getNodes(filter);
		BraceNode braceNode = new BraceNode(nodesBetweenBrackets);
		return braceNode;
	}
}

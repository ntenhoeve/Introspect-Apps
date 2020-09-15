package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeParserRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResults;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.MatchRules;

public class AcknowledgeRule implements NodeParserRule {

	private static final AcknowledgePredicate ACKNOWLEDGE_PREDICATE = new AcknowledgePredicate();

	@Override
	public MatchRules getMatchRules() {
		MatchRules matchRules = new MatchRules().add(ACKNOWLEDGE_PREDICATE);
		return matchRules;
	}

	/**
	 * Replaces a {@link Node} that matches a {@link AcknowledgePredicate} with a
	 * {@link AcknowledgeNode}
	 */
	@Override
	public void removeOrReplace(MatchResults matchResults) {
		AcknowledgeNode acknowledgeNode = new AcknowledgeNode();
		matchResults.replaceMatchingNodesWith(acknowledgeNode);
	}
}

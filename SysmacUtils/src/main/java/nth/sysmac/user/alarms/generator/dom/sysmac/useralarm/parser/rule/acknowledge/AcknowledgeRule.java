package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

public class AcknowledgeRule implements NodeParserRule {

	private static final AcknowledgePredicate ACKNOWLEDGE_PREDICATE = new AcknowledgePredicate();

	@Override
	public MatchRules getMatchRules() {
		MatchRules matchRules = new MatchRules().add(ACKNOWLEDGE_PREDICATE);
		return matchRules;
	}

	/**
	 * Replaces a {@link Node} that matches a {@link PriorityPredicate} with a
	 * {@link AcknowledgeNode}
	 */
	@Override
	public void removeOrReplace(MatchResults matchResults) {
		AcknowledgeNode acknowledgeNode = new AcknowledgeNode();
		matchResults.replaceMatchingNodesWith(acknowledgeNode);
	}
}

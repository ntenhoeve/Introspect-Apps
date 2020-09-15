package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeParserRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;

public class AcknowledgeRule implements NodeParserRule {

	private static final AcknowledgePredicate ACKNOWLEDGE_PREDICATE = new AcknowledgePredicate();

	@Override
	public Rules getMatchRules() {
		Rules rules = new Rules().add(ACKNOWLEDGE_PREDICATE);
		return rules;
	}

	/**
	 * Replaces a {@link Node} that matches a {@link AcknowledgePredicate} with a
	 * {@link AcknowledgeNode}
	 */
	@Override
	public void removeOrReplace(Results results) {
		AcknowledgeNode acknowledgeNode = new AcknowledgeNode();
		results.replaceMatchingNodesWith(acknowledgeNode);
	}
}

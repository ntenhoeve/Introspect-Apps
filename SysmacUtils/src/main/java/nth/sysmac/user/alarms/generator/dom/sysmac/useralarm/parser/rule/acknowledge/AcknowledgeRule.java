package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityPredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

/**
 * <h2>Acknowledge</h2>
 * 
 * You can set the {@link UserAlarm} needs to be acknowledged by an operator by adding the following text
 * to the {@link DataType} comment:
 * <p>
 * {ack}
 * <p>
 * The {@link UserAlarm} won't need to be acknowledged when {ack} is not in the {@link UserAlarm} message.
 * <p>
 * {@insert AcknowledgeRuleExampleTest}
 * <p>
 * @author nilsth
 *
 */
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
		matchResults.replaceFoundNodesWith(acknowledgeNode);
	}
}

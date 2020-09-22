package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.result.filter.RulesResultFilter;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.text.NodesToTextConverter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

/**
 * <h2>Priority</h2>
 * 
 * You can set the priority of a {@link UserAlarm} by adding the following text
 * to the {@link DataType} comment:
 * <p>
 * {p=&lt;priority abbreviation&gt;} where &lt;priority abbreviation&gt; is
 * replaced with one of the following abbreviation:
 * <p>
 * {@insert Priority}
 * <p>
 * The {@link UserAlarm} will get {@link Priority#MEDIUM} by default.
 * 
 * @author nilsth
 *
 */
public class PriorityRule implements NodeParserRule {

	private static final Predicate<Node> DETAILS_PREDICATE = new PriorityPredicate();

	@Override
	public MatchRules getMatchRules() {
		MatchRules matchRules = new MatchRules()//
				.add(DETAILS_PREDICATE);
		return matchRules;
	}

	/**
	 * Replaces {@link TokenNode}s that match {@link #getMatchRules()} and replaces
	 * them with a {@link PriorityNode} containing the {@link TokenNode}s that match {@link PriorityPredicate#ATTRIBUTE_VALUE_RULES}
	 */
	@Override
	public void removeOrReplace(MatchResults matchResults) {
		Priority priority = createAttribute(matchResults);
		PriorityNode priorityNode=new PriorityNode(priority);
		matchResults.replaceFoundNodesWith(priorityNode);
	}

	private Priority createAttribute(MatchResults matchResults) {
		List<Node> priorityAttributeValue = getAttributeValues(matchResults);
		String priorityAbbreviation=NodesToTextConverter.convert(priorityAttributeValue).trim();
		Priority priority=Priority.valueOfAbbreviation(priorityAbbreviation);
		return priority;
	}

	private List<Node> getAttributeValues(MatchResults matchResults) {
		List<Node> braceNodeChildren = matchResults.getFoundNodes().get(0).getNodes();
		NodeMatcher nodeMatcher=new NodeMatcher(PriorityPredicate.CHILD_MATCH_RULES);
		MatchResults priorityMatchResults= nodeMatcher.match(braceNodeChildren);
		RulesResultFilter filter = new RulesResultFilter(PriorityPredicate.ATTRIBUTE_VALUE_RULES);
		List<Node> priorityChildren = priorityMatchResults.getFoundNodes(filter);
		return priorityChildren;
	}
}

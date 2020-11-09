package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.result.filter.RulesResultFilter;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityPredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

/**
 * <h2>Details</h2>
 * <p>
 * You can add details to an {@link UserAlarm} to provide extra information on
 * how to solve an alarm. Details are added by adding the following text to the
 * {@link DataType} comment:
 * <p>
 * {details=&lt;solution&gt;} where &lt;solution&gt; is replaced with your
 * additional information on how to solve a problem.
 * <p>
 * {insert DetailsRuleExampleTest}
 * <p>
 * 
 * @author nilsth
 *
 */
public class DetailsRule implements NodeParserRule {

	private static final Predicate<Node> DETAILS_PREDICATE = new DetailsPredicate();

	@Override
	public MatchRules getMatchRules() {
		MatchRules matchRules = new MatchRules()//
				.add(DETAILS_PREDICATE);
		return matchRules;
	}

	/**
	 * Replaces {@link TokenNode}s that match {@link #getMatchRules()} and replaces
	 * them with a {@link DetailsNode} containing the {@link TokenNode}s that match
	 * {@link CounterPredicate#ATTRIBUTE_VALUE_RULES}
	 */
	@Override
	public void removeOrReplace(MatchResults matchResults) {
		List<Node> braceNodeChildren = matchResults.getFoundNodes().get(0).getNodes();
		NodeMatcher nodeMatcher = new NodeMatcher(DetailsPredicate.CHILD_MATCH_RULES);
		MatchResults detailsMatchResults = nodeMatcher.match(braceNodeChildren);
		RulesResultFilter filter = new RulesResultFilter(DetailsPredicate.ATTRIBUTE_VALUE_RULES);
		List<Node> detailsChildren = detailsMatchResults.getFoundNodes(filter);
		DetailsNode detailsNode = new DetailsNode(detailsChildren);
		matchResults.replaceFoundNodesWith(detailsNode);
	}
}

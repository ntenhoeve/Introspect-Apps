package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResults;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.MatchRules;

/**
 * Defines what {@link Node} children need to be replaced as defined by the
 * {@link #getRules(List)} method and removes or or replaces the the matching
 * nodes with the {@link #removeOrReplace(MatchResults)} method
 * 
 * @author nilsth
 *
 * @param <T>
 */
public interface NodeParserRule {

	public MatchRules getMatchRules();

	public void removeOrReplace(MatchResults matchResults);
}

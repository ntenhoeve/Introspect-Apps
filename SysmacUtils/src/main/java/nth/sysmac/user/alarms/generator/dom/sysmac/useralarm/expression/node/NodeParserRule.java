package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;


import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;

/**
 * Defines what {@link Node} children need to be replaced
 * ({@link #getRules(List)} method) and creates a replacement Node
 * ({@link #removeOrReplace(MatchResultOld)} method)
 * 
 * @author nilsth
 *
 * @param <T>
 */
public interface NodeParserRule {

	public Rules getMatchRules();

	public void removeOrReplace(Results results);
}

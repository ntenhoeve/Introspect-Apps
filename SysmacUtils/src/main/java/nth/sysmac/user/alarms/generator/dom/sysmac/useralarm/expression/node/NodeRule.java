package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;


import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;

/**
 * Defines what {@link Node} children need to be replaced
 * ({@link #find(List)} method) and creates a replacement Node
 * ({@link #createReplacement(MatchResultOld)} method)
 * 
 * @author nilsth
 *
 * @param <T>
 */
public interface NodeRule<T extends Node> {

	/**
	 * @deprecated TODO: replace with  {@link Rules} getRules()
	 */
	public Results find(List<Node> nodes);

	/**
	 * @deprecated TODO: replace with  void removeOrReplaceSearchResult
	 */

	public T createReplacement(Results results);

}

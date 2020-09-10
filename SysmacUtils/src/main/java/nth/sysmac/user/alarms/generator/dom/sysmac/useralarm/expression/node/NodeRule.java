package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;


import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;

/**
 * Defines what {@link Node} children need to be replaced
 * ({@link #find(List)} method) and creates a replacement Node
 * ({@link #createReplacement(MatchResult)} method)
 * 
 * @author nilsth
 *
 * @param <T>
 */
public interface NodeRule<T extends Node> {

	public MatchResult find(List<Node> children);

	public T createReplacement(MatchResult matchResult);

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;


import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;

/**
 * Defines what {@link NodeChildren}s need to be replaced
 * ({@link #find(NodeChildren)} method) and creates a replacement Node
 * ({@link #createReplacement(MatchResult)} method)
 * 
 * @author nilsth
 *
 * @param <T>
 */
public interface NodeReplacement<T extends Node> {

	public MatchResult find(NodeChildren children);

	public T createReplacement(MatchResult matchResult);

}

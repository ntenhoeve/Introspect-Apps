package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;


import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResultOld;

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
	 * @deprecated change to public MatchPattern getMatchPattern();
	 * @param children
	 * @return
	 */
	public MatchResultOld find(List<Node> children);

	public T createReplacement(MatchResultOld matchResultOld);

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;

/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public class ResultFilter {

	private final boolean excludeFirstNode;
	private final boolean excludeLastNode;
	private final boolean excludeWhiteSpaces;

	public ResultFilter() {
		excludeFirstNode = false;
		excludeLastNode = false;
		excludeWhiteSpaces = false;
	}

	public ResultFilter(boolean excludeFirstNode, boolean excludeLastNode, boolean excludeWhiteSpaces) {
		this.excludeFirstNode = excludeFirstNode;
		this.excludeLastNode = excludeLastNode;
		this.excludeWhiteSpaces = excludeWhiteSpaces;
	}

	public ResultFilter excludeFirstNode() {
		return new ResultFilter(true, excludeLastNode, excludeWhiteSpaces);
	}

	public ResultFilter excludeLastNode() {
		return new ResultFilter(excludeFirstNode, true, excludeWhiteSpaces);
	}

	public ResultFilter excludeWhiteSpaces() {
		return new ResultFilter(excludeFirstNode, excludeLastNode, true);
	}

	public boolean isExcludeFirstNode() {
		return excludeFirstNode;
	}

	public boolean isExcludeLastNode() {
		return excludeLastNode;
	}

	public boolean isExcludeWhiteSpaces() {
		return excludeWhiteSpaces;
	}

	public boolean isIncluded(Node child) {
		return !isExcluded(child);
	}

	private boolean isExcluded(Node child) {
		return excludeWhiteSpaces && TokenNodePredicate.whiteSpace().test(child);
	}

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.NoResultsFoundException;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.ResultGroup;

public abstract class ResultFilter {

	public abstract int getFirst(List<ResultGroup> resultGroups);

	public abstract int getLast(List<ResultGroup> resultGroups);

	public List<Node> getChildren(List<ResultGroup> resultGroups, List<Node> children) {
		throwErrorWhenNoResultsAreFound(resultGroups);
		int first = getFirst(resultGroups);
		int last = getLast(resultGroups);
		List<Node> found = new ArrayList<>();
		for (int index = first; index <= last; index++) {
			Node child = children.get(index);
			found.add(child);
		}
		return found;
	}

	public void throwErrorWhenNoResultsAreFound(List<ResultGroup> resultGroups) {
		if (resultGroups.isEmpty()) {
			throw new NoResultsFoundException();
		}
	}

}

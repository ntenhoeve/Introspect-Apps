package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.NoResultsFoundException;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Result;

public abstract class ResultFilter {

	public abstract int getFirst(List<Result> results);

	public abstract int getLast(List<Result> results);

	public List<Node> getChildren(List<Result> results, List<Node> children) {
		throwErrorWhenNoResultsAreFound(results);
		int first = getFirst(results);
		int last = getLast(results);
		List<Node> found = new ArrayList<>();
		for (int index = first; index <= last; index++) {
			Node child = children.get(index);
			found.add(child);
		}
		return found;
	}

	public void throwErrorWhenNoResultsAreFound(List<Result> results) {
		if (results.isEmpty()) {
			throw new NoResultsFoundException();
		}
	}

}

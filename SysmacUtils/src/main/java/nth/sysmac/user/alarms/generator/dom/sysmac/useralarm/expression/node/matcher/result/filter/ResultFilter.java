package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.NoResultsFoundException;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResult;

public abstract class ResultFilter {

	public abstract int getFirstNodeIndex(List<MatchResult> matchResults);

	public abstract int getLastNodeIndex(List<MatchResult> matchResults);

	public List<Node> getChildren(List<MatchResult> matchResults, List<Node> children) {
		throwErrorWhenNoResultsAreFound(matchResults);
		int first = getFirstNodeIndex(matchResults);
		int last = getLastNodeIndex(matchResults);
		List<Node> found = new ArrayList<>();
		for (int index = first; index <= last; index++) {
			Node child = children.get(index);
			found.add(child);
		}
		return found;
	}

	public void throwErrorWhenNoResultsAreFound(List<MatchResult> matchResults) {
		if (matchResults.isEmpty()) {
			throw new NoResultsFoundException();
		}
	}

}

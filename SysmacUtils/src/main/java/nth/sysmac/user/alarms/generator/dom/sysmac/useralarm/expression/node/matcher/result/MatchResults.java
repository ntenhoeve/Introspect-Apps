package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.ResultFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.MatchRule;

/**
 * {@link MatchResults} are the matchResults of a {@link NodeParser}. Each match is
 * stored in one a {@link MatchResult} per {@link MatchRule}.
 * 
 * @author nilsth
 *
 */
public class MatchResults {

	public static final MatchResults NOT_FOUND = new MatchResults(new ArrayList<>());
	private final List<MatchResult> matchResults;
	private final List<Node> nodes;

	public MatchResults(List<Node> nodes) {
		this.nodes = nodes;
		matchResults = new ArrayList<>();
	}

	public boolean hasResults() {
		return !matchResults.isEmpty();
	}

	public void add(MatchRule matchRule, int index) {
		if (!hasResults()) {
			createAndAddNewResult(index, matchRule);
		} else {
			MatchResult lastResult = getLastResult();
			if (matchRule == lastResult.getRule()) {
				lastResult.setLastNodeIndex(index);
			} else {
				createAndAddNewResult(index, matchRule);
			}
		}
	}

	private void createAndAddNewResult(int index, MatchRule matchRule) {
		MatchResult newResult = new MatchResult(matchRule, index);
		matchResults.add(newResult);
	}

	public int getFirstNodeIdex(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int first = resultFilter.getFirstNodeIndex(matchResults);
		return first;
	}

	private void throwErrorWhenNothingWasFound() {
		if (!hasResults()) {
			throw new NoResultsFoundException();
		}
	}

	public int getLastNodeIndex(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int last = resultFilter.getLastNodeIndex(matchResults);
		return last;
	}

	public void replaceMatchingNodesWith(Node replacementNode) {
		int firstIndex = getFirstNodeIndex();
		int lastIndex = getLastNodeIndex();

		for (int index = firstIndex; index <= lastIndex; index++) {
			nodes.remove(firstIndex);
		}
		int replacementIndex = firstIndex;
		nodes.add(replacementIndex, replacementNode);
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		reply.append(MatchResults.class.getSimpleName());
		if (hasResults()) {
			reply.append("\n");
			for (MatchResult matchResult : matchResults) {
				reply.append("  " + matchResult + "\n");
			}

		} else {
			reply.append(": NOTHING FOUND!");
		}
		return reply.toString();
	}

	public List<Node> getNodes(ResultFilter resultFilter) {
		List<Node> found = resultFilter.getChildren(matchResults, nodes);
		return found;
	}

	public MatchResult getLastResult() {
		throwErrorWhenNothingWasFound();
		return matchResults.get(matchResults.size() - 1);
	}

	public MatchResult getFirstResult() {
		throwErrorWhenNothingWasFound();
		return matchResults.get(0);
	}

	public int getFirstNodeIndex() {
		throwErrorWhenNothingWasFound();
		int lastNodeIndex = getFirstResult().getFirstNodeIndex();
		return lastNodeIndex;
	}

	public int getLastNodeIndex() {
		throwErrorWhenNothingWasFound();
		int lastNodeIndex = getLastResult().getLastNodeIndex();
		return lastNodeIndex;
	}

	public int getNumberOfMatches(MatchRule ruleToFind) {
		int numberOfMatches = (int) matchResults.stream().filter(r -> r.getRule() == ruleToFind).count();
		return numberOfMatches;
	}

	public List<Node> getNodes() {
		return nodes;
	}

}

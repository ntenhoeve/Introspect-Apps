package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.ResultFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rule;

public class Results {

	public static final Results NOT_FOUND = new Results(new ArrayList<>());
	private final List<Result> results;
	private final List<Node> nodes;

	public Results(List<Node> nodes) {
		this.nodes = nodes;
		results = new ArrayList<>();
	}

	public boolean hasResults() {
		return !results.isEmpty();
	}

	public void add(Rule rule, int index) {
		if (!hasResults()) {
			createAndAddNewResult(index, rule);
		} else {
			Result lastResult = getLastResult();
			if (rule==lastResult.getRule()) {
				lastResult.setLastNodeIndex(index);
			} else {
				createAndAddNewResult(index, rule);
			}
		}
	}

	private void createAndAddNewResult(int index, Rule rule) {
		Result newResult = new Result(rule, index);
		results.add(newResult);
	}

	public int getFirstNodeIdex(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int first = resultFilter.getFirstNodeIndex(results);
		return first;
	}

	private void throwErrorWhenNothingWasFound() {
		if (!hasResults()) {
			throw new NoResultsFoundException();
		}
	}

	public int getLastNodeIndex(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int last = resultFilter.getLastNodeIndex(results);
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
		reply.append(Results.class.getSimpleName());
		if (hasResults()) {
			reply.append("\n");
			for (Result result : results) {
				reply.append("  " + result + "\n");
			}

		} else {
			reply.append(": NOTHING FOUND!");
		}
		return reply.toString();
	}

	public List<Node> getNodes(ResultFilter resultFilter) {
		List<Node> found = resultFilter.getChildren(results, nodes);
		return found;
	}

	public Result getLastResult() {
		throwErrorWhenNothingWasFound();
		return results.get(results.size()-1);
	}

	public Result getFirstResult() {
		throwErrorWhenNothingWasFound();
		return results.get(0);
	}
	
	public int getFirstNodeIndex() {
		throwErrorWhenNothingWasFound(); 
		int lastNodeIndex=getFirstResult().getFirstNodeIndex();
		return lastNodeIndex;
	}
	
	public int getLastNodeIndex() {
		throwErrorWhenNothingWasFound(); 
		int lastNodeIndex=getLastResult().getLastNodeIndex();
		return lastNodeIndex;
	}

	public int getNumberOfMatches(Rule ruleToFind) {
		int numberOfMatches=(int) results.stream().filter(r-> r.getRule()==ruleToFind).count();
		return numberOfMatches;
	}

	public List<Node> getNodes() {
		return nodes;
	}

}

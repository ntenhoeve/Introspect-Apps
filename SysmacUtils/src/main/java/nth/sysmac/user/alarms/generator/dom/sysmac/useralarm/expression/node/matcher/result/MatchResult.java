package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.Necessity;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.GroupNameResultFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.RequiredGroupResultFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.ResultFilter;

public class MatchResult {

	private static final RequiredGroupResultFilter RESULT_FILTER = new RequiredGroupResultFilter();
	public static final MatchResult NOT_FOUND = new MatchResult(new ArrayList<>());
	private final List<ResultGroup> resultGroups;
	private final List<Node> children;

	public MatchResult(List<Node> children) {
		this.children = children;
		resultGroups = new ArrayList<>();
	}

	public boolean hasResults() {
		return !resultGroups.isEmpty();
	}

	public void addResult(NodeRule nodeRule, int index) {
		if (!hasResults()) {
			createAndAddNewGroup(index, nodeRule);
		} else {
			String name = nodeRule.getGroup().getName();
			ResultGroup resultGroup = getLastResultGroup();
			if (name.equals(resultGroup.getName())) {
				resultGroup.setLast(index);
			} else {
				createAndAddNewGroup(index, nodeRule);
			}
		}
	}

	private void createAndAddNewGroup(int index, NodeRule nodeRule) {
		String name = nodeRule.getGroup().getName();
		Necessity nesessity = nodeRule.getGroup().getNecessity();
		ResultGroup newResultGroup = new ResultGroup(name, nesessity, index);
		resultGroups.add(newResultGroup);
	}


	private ResultGroup getLastResultGroup() {
		return resultGroups.get(resultGroups.size() - 1);
	}

	public int getFirst(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int first = resultFilter.getFirst(resultGroups);
		return first;
	}

	private void throwErrorWhenNothingWasFound() {
		if (!hasResults()) {
			throw new NoResultsFoundException();
		}
	}

	public int getLast(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int last = resultFilter.getLast(resultGroups);
		return last;
	}

	public void replaceFoundNodesWith(Node replacementNode) {
		int firstIndex = getFirst(RESULT_FILTER);
		int lastIndex = getLast(RESULT_FILTER);

		for (int index = firstIndex; index <= lastIndex; index++) {
			children.remove(firstIndex);
		}
		int replacementIndex = firstIndex;
		children.add(replacementIndex, replacementNode);
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		reply.append(MatchResult.class.getSimpleName());
		if (hasResults()) {
			reply.append("\n");
			for (ResultGroup resultGroup : resultGroups) {
				reply.append("  " + resultGroup + "\n");
				String name = resultGroup.getName();
				GroupNameResultFilter groupNameResultFilter = new GroupNameResultFilter(name);
				List<Node> groupChildren = getChildren(groupNameResultFilter);
				for (Node child : groupChildren) {
					reply.append("    " + child);
				}
			}

		} else {
			reply.append(": NOTHING FOUND!");
		}
		return reply.toString();
	}

	public List<Node> getChildren(ResultFilter resultFilter) {
		List<Node> found = resultFilter.getChildren(resultGroups, children);
		return found;
	}

}

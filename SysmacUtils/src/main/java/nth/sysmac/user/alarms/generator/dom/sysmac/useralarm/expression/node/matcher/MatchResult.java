package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeChildren;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance.NodeDistance;

public class MatchResult {

	public static final MatchResult EMPTY = new MatchResult(new NodeChildren(), new ArrayList<>());
	private final List<Integer> foundIndexes;
	private final List<Node> children;

	public MatchResult(List<Node> children, List<Integer> foundIndexes) {
		this.children = children;
		this.foundIndexes = foundIndexes.stream().collect(Collectors.toUnmodifiableList());
	}

	public MatchResult(List<Node> children, List<Integer> foundIndexes, NodeDistance nodeDistance) {
		MatchResult matchResult = new MatchResult(children, foundIndexes);
		boolean nodeDistanceOk = nodeDistance.isCorrectFor(matchResult);
		if (!nodeDistanceOk) {
			children = new NodeChildren();
			foundIndexes = new ArrayList<>();
		}
		this.children = children;
		this.foundIndexes = foundIndexes.stream().collect(Collectors.toUnmodifiableList());
	}

	public boolean found() {
		return !foundIndexes.isEmpty();
	}

	public List<Integer> getFoundIndexes() {
		return foundIndexes;
	}

	public List<Node> getChildren() {
		return children;
	}

	public int getFirstFoundIndex() {
		if (!found()) {
			throw new RuntimeException("No results where found!");
		}
		return foundIndexes.get(0);
	}

	public int getLastFoundIndex() {
		if (!found()) {
			throw new RuntimeException("No results where found!");
		}
		return foundIndexes.get(foundIndexes.size() - 1);
	}

	public List<Node> getFoundChildren() {
		return getFoundChildren(new ResultFilter());
	}

	public List<Node> getFoundChildren(ResultFilter resultFilter) {
		List<Node> found = new ArrayList<>();
		int firstIndex = getFirstIndex(resultFilter);
		int lastIndex = getLastIndex(resultFilter);
		for (int index = firstIndex; index <= lastIndex; index++) {
			Node child = children.get(index);

			if (resultFilter.isIncluded(child)) {
				found.add(child);
			}
		}
		return found;
	}

	private int getLastIndex(ResultFilter resultFilter) {
		int lastIndex = getLastFoundIndex();
		if (resultFilter.isExcludeLastNode()) {
			lastIndex--;
		}
		return lastIndex;
	}

	private int getFirstIndex(ResultFilter resultFilter) {
		int firstIndex = getFirstFoundIndex();
		if (resultFilter.isExcludeFirstNode()) {
			firstIndex++;
		}
		return firstIndex;
	}

	public void replaceFoundNodesWith(Node replacementNode) {
		int firstIndex = getFirstFoundIndex();
		int lastIndex = getLastFoundIndex();
		
		for (int index=firstIndex;index<=lastIndex;index++) {
			children.remove(firstIndex);
		}
		int replacementIndex=firstIndex;
		children.add(replacementIndex, replacementNode);
	}

}

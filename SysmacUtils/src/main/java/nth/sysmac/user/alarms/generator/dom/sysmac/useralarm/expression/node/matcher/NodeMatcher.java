package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance.NodeDistance;

public class NodeMatcher {
	
	public MatchResult find(List<Node> children, Predicate<Node> predicate) {
		return find(children, predicate, 0);
	}

	public MatchResult find(List<Node> children, Predicate<Node> predicate, int startIndex) {
		for (int index = startIndex; index < children.size(); index++) {
			Node child = children.get(index);
			if (predicate.test(child)) {
				return new MatchResult(children, Arrays.asList(index));
			}
		}
		return MatchResult.EMPTY;
	}

	@SafeVarargs
	public final MatchResult find(List<Node> children, NodeDistance nodeDistance, Predicate<Node>... predicates) {
		int startIndex = 0;
		List<Integer> foundIndexes = new ArrayList<>();
		for (int predicateIndex = 0; predicateIndex < predicates.length; predicateIndex++) {
			Predicate<Node> predicate = predicates[predicateIndex];
			MatchResult matchResult = find(children, predicate, startIndex);
			if (!matchResult.found()) {
				return MatchResult.EMPTY;
			}

			int foundIndex = matchResult.getFoundIndexes().get(0);
			foundIndexes.add(foundIndex);
			startIndex = foundIndex + 1;
		}

		return new MatchResult(children, foundIndexes, nodeDistance);
	}
	

}

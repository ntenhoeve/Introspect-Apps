package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.function.Predicate;

public class NodeTypePredicate implements Predicate<Node> {

	private final Class<? extends Node> type;

	public NodeTypePredicate(Class<? extends Node> type) {
		this.type = type;
	}

	@Override
	public boolean test(Node node) {
		return type.isAssignableFrom(node.getClass());
	}


}

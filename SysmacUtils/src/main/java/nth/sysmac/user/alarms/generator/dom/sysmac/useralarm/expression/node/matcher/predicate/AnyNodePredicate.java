package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.predicate;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;

public class AnyNodePredicate implements Predicate<Node> {

	@Override
	public boolean test(Node node) {
		return true;
	}

}

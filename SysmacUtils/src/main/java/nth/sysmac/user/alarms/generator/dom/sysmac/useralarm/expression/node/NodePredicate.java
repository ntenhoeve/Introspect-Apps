package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.function.Predicate;

public class NodePredicate  {


	public static Predicate<Node> ofType(Class<? extends Node> type) {
		return new Predicate<Node>() {
			@Override
			public boolean test(Node node) {
				return type.isAssignableFrom(node.getClass());
			}
		};		
	}
	
	public static Predicate<Node> any() {
		return new Predicate<Node>() {
			@Override
			public boolean test(Node node) {
				return true;
			}
		};
	}


}

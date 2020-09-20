package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority;

import nth.reflect.util.parser.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;


public class PriorityNode extends Node {

	private final Priority priority;

	public PriorityNode(Priority priority) {
		this.priority = priority;
	}

	public Priority getPriority() {
		return priority;
	}

	@Override
	public boolean equals(Object that) {

		if (!super.equals(that)) {
			return false;
		}
		PriorityNode thatPriorityNode = (PriorityNode) that;
		boolean equals = priority.equals(thatPriorityNode.getPriority());
		return equals;
	}

	@Override
	public String toString() {
		return PriorityNode.class.getSimpleName()+" priority="+priority;
	}

	
}

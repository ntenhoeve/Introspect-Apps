package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority;

import nth.reflect.util.parser.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;

/**
 * You can set the priority of a {@link UserAlarm} by adding the following text
 * to the {@link UserAlarm} message:
 * <p>
 * {p=<priority>} where <priority> is replaced with one of the following
 * letter(s):
 * <p>
 * 
 * <p>
 * The {@link UserAlarm} will get {@link Priority#MEDIUM} by default.
 * 
 * @author nilsth
 *
 */
public class PriorityNode extends Node {

	private final Priority priority;

	public PriorityNode(Priority priority){
		this.priority = priority;
	}

	public Priority getPriority() {
		return priority;
	}
	
	@Override
		public boolean equals(Object that) {
			
			if (! super.equals(that)) {
				return false;
			}
			PriorityNode thatPriorityNode=(PriorityNode) that;
			boolean equals = priority.equals(thatPriorityNode.getPriority());
			return equals;
		}
	
	

}

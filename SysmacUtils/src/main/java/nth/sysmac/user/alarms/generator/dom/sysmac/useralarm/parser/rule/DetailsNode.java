package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.text.NodesToTextConverter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;

/**
 * Represent {@link UserAlarm} details information
 * 
 * @author nilsth
 *
 */
public class DetailsNode extends Node {

	public DetailsNode(List<Node> children) {
		super(children);
	}

	@Override
	public String toString() {
		return NodesToTextConverter.convert(getChildren());
	}
}

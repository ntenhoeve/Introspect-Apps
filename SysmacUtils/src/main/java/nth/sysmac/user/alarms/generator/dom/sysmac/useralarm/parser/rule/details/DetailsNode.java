package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.text.NodesToTextConverter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;

/**
 * You can add details to an {@link UserAlarm} to provide extra information on
 * how to solve an alarm. Details are added by adding the following text to the
 * {@link UserAlarm} message:
 * <p>
 * {d=<description>} where <description> is replaced with your text.
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

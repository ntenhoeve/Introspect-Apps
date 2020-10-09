package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.text.NodesToTextConverter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;

/**
 * Represents {@link UserAlarm} detais, see {@link DetailsRule}
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
		return DetailsNode.class.getSimpleName()+" details="+NodesToTextConverter.convert(getNodes());
	}
}

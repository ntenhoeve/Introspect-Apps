package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link ArrayList} that houses the (potential) children of a {@link Node}.
 * This class has additional methods for conveniently searching the
 * {@link NodeChildren} and replacing {@link NodeChildren} with new nodes by the
 * {@link NodeParser}
 * 
 * @author nilsth
 *
 *@deprecated use ArrayList<Node>
 */
public class NodeChildren extends ArrayList<Node> {

	private static final long serialVersionUID = 3952652124104360946L;

	public NodeChildren() {
		super();	}
	
	public NodeChildren(List<Node> children) {
		super(children);
	}


}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

import java.util.List;

import nth.reflect.util.parser.node.Node;

/**
 * Intermediate node (will be replaced by another Node) for child{@link Node}'s
 * in an {@link BraceNode} that represent a {@link BracedAttributeNode} with
 * format &lt;attributename&gt;= &lt;attrubute values&gt;
 * 
 * @author nilsth
 *
 */
public class BracedAttributeNode extends Node {
	private final BracedAttributeName name;

	public BracedAttributeNode(BracedAttributeName name, List<Node> values) {
		super();
		this.name = name;
		getNodes().addAll(values);
	}

	public BracedAttributeName getName() {
		return name;
	}

	@Override
	public boolean equals(Object that) {
		if (that == null) {
			return false;
		}
		if (this.getClass() != that.getClass()) {
			return false;
		}
		BracedAttributeNode thatNode=(BracedAttributeNode) that;
		if (!name.equals(thatNode.getName())) {
			return false;
		}
		boolean equalChildren = getNodes().equals(thatNode.getNodes());
		return equalChildren;
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();

		reply.append(getClass().getSimpleName());
		reply.append(" name=");
		reply.append(name.name());
		reply.append(Node.NEW_LINE);

		for (Node child : getNodes()) {
			String[] childStrings = child.toString().split(REGEX_NEW_LINE);
			for (String childString : childStrings) {
				reply.append(INDENT);
				reply.append(childString);
				reply.append(NEW_LINE);
			}
		}

		return reply.toString();
	}

}

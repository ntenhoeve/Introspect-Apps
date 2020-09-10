package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Node} is a part of a
 * <a href="https://en.wikipedia.org/wiki/Tree_structure">tree structure</a>. In
 * this case the tree represents a {@link ParseTree}
 * 
 * @author nilsth
 *
 */
public abstract class Node {

	private static final String NEW_LINE = "\n";
	private static final String REGEX_NEW_LINE = "\\n";
	private static final String INDENT = "  ";
	private final List<Node> children;
	private final String name;

	public Node() {
		this(new ArrayList<>());
	}

	public Node(List<Node> children) {
		this.children = children;
		this.name = getClass().getSimpleName();
	}

	public Node(String name) {
		this.children = new ArrayList<>();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Node> getChildren() {
		return children;
	}

	@Override
	public boolean equals(Object that) {
		if (that == null) {
			return false;
		}
		if (this.getClass() != that.getClass()) {
			return false;
		}
		Node thatNode = (Node) that;
		if (!this.getName().equals(thatNode.getName())) {
			return false;
		}
		boolean equalChildren = children.equals(thatNode.getChildren());
		return equalChildren;
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();

		reply.append(name);
		reply.append(NEW_LINE);

		for (Node child : children) {
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

package nth.software.doc.generator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NodeContainer<T extends Node> implements Node {

	private final List<T> children;
	
	public NodeContainer() {
		children=new ArrayList<T>();
	}

	public List<T> getChildren() {
		return children ;
	}
	
	public void add(T node) {
		children.add(node);
	}
	
	public void addAll(Collection<T> nodes) {
		children.addAll(nodes);
	}
	
}

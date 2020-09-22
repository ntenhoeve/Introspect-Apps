package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypePredicate;

public class BracedAttributePredicate extends NodeTypePredicate {

	private final BracedAttributeName bracedAttributeNameToFind;

	public BracedAttributePredicate(BracedAttributeName bracedAttributeNameToFind) {
		super(BracedAttributeNode.class);
		this.bracedAttributeNameToFind = bracedAttributeNameToFind;
	}

	@Override
	public boolean test(Node node) {
		boolean typeOk = super.test(node);
		if (!typeOk) {
			return false;
		} else {
			BracedAttributeNode bracedAttributeNode=(BracedAttributeNode) node;
			boolean nameOk=bracedAttributeNode.getName().equals(bracedAttributeNameToFind);
			return nameOk;
		}
	}
	
	

}

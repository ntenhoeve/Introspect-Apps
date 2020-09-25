package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypePredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

public class BracedAttributePredicate extends NodeTypePredicate {

	private final BracedAttributeName bracedAttributeNameToFind;
	private final MatchRules childMatchRules;

	public BracedAttributePredicate(BracedAttributeName bracedAttributeNameToFind, MatchRules childMatchRules) {
		super(BracedAttributeNode.class);
		this.bracedAttributeNameToFind = bracedAttributeNameToFind;
		this.childMatchRules = childMatchRules;
	}

	@Override
	public boolean test(Node node) {
		boolean typeOk = super.test(node);
		if (!typeOk) {
			return false;
		} else {
			BracedAttributeNode bracedAttributeNode=(BracedAttributeNode) node;
			boolean nameOk=bracedAttributeNode.getName().equals(bracedAttributeNameToFind);
			if (!nameOk) {
				return false;
			} else {
				List<Node> nodes = node.getNodes();
				NodeMatcher nodeMatcher=new NodeMatcher(childMatchRules);
				MatchResults matchResults=nodeMatcher.match(nodes);
				boolean hasResults=matchResults.hasResults();
				return hasResults;
			}
		}
	}
	
	

}

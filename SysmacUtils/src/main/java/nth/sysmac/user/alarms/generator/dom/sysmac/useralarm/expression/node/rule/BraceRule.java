package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;


import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeChildren;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.ResultFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance.NodeDistance;

public class BraceRule implements NodeRule<BraceNode> {

	@Override
	public MatchResult find(NodeChildren children) {
		NodeMatcher nodeMatcher=new NodeMatcher();
		MatchResult matchResult=nodeMatcher.find(children, NodeDistance.ZERO_OR_MORE_INBETWEEN, TokenNodePredicate.openBrace(),TokenNodePredicate.closeBrace());
		return matchResult;
	}


	@Override
	public BraceNode createReplacement(MatchResult matchResult) {
		List<Node> foundChildren=matchResult.getFoundChildren(new ResultFilter().excludeFirstNode().excludeLastNode());
		BraceNode braceNode=new BraceNode();
		braceNode.getChildren().addAll(foundChildren);
		return braceNode;
	}}

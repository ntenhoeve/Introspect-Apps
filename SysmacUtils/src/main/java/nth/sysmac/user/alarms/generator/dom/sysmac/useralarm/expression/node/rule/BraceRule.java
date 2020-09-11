package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;


import java.util.List;
import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResultOld;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcherOld;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.ResultFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance.NodeDistance;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResult;

public class BraceRule implements NodeRule<BraceNode> {

	@Override
	public MatchResultOld find(List<Node> children) {
		

		
		
		NodeMatcherOld nodeMatcherOld=new NodeMatcherOld();
		MatchResultOld matchResultOld=nodeMatcherOld.find(children, NodeDistance.ZERO_OR_MORE_INBETWEEN, TokenNodePredicate.openBrace(),TokenNodePredicate.closeBrace());
		return matchResultOld;
	}


	@Override
	public BraceNode createReplacement(MatchResultOld matchResultOld) {
		List<Node> foundChildren=matchResultOld.getFoundChildren(new ResultFilter().excludeFirstNode().excludeLastNode());
		BraceNode braceNode=new BraceNode();
		braceNode.getChildren().addAll(foundChildren);
		return braceNode;
	}}

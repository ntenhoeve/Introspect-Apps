package nth.sysmac.user.alarms.generator.dom.testobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule.AcknowledgeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Rest;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.CloseBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.OpenBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.WhiteSpace;

public class TestObjectFactory {

	private static final String REST = "Rest";

	// ====== TOKEN NODES
	public static ExpressionAndNodes tokenNodeRest() {
		return tokenNodeRest(REST);
	}
	
	public static ExpressionAndNodes tokenNodeRest(String expression) {
		return new ExpressionAndNodes(expression, new Rest());
	}

	public static ExpressionAndNodes tokenNodeOpenBrace() {
		return new ExpressionAndNodes("{", new OpenBrace());
	}

	public static ExpressionAndNodes tokenNodeCloseBrace() {
		return new ExpressionAndNodes("}", new CloseBrace());
	}

	public static ExpressionAndNodes tokenNodewhiteSpace() {
		return new ExpressionAndNodes(" ", new WhiteSpace());
	}
	
	// ====== PARCED NODES
	public static ExpressionAndNodes braceNode() {
		ExpressionAndNodes expressionAndNodes=tokenNodeOpenBrace().append(tokenNodeRest()).append(tokenNodeCloseBrace());
		return braceNode(expressionAndNodes);
	}

	public static ExpressionAndNodes braceNode(ExpressionAndNodes expressionAndNodesWithBraces) {
		String expression=expressionAndNodesWithBraces.expression();
		List<TokenNode> tokenNodes=expressionAndNodesWithBraces.tokenNodes();
		List<Node> childNodes = new ArrayList<>(expressionAndNodesWithBraces.parcedNodes());
		if (childNodes.size()>0 && new TokenNodePredicate(new OpenBrace()).test(childNodes.get(0)) ) {
			childNodes.remove(0);
		}
		if (childNodes.size()>0 && new TokenNodePredicate(new CloseBrace()).test(childNodes.get(childNodes.size()-1)) ) {
			childNodes.remove(childNodes.size()-1);
		}
		List<Node> parsedNodes = Arrays.asList(new BraceNode(childNodes));
		return new ExpressionAndNodes(expression, tokenNodes, parsedNodes);
	}

	
	
	public static ExpressionAndNodes acknowledgeNode() {
		return acknowledgeNode("{ack}");
	}

	public static ExpressionAndNodes acknowledgeNode(String expression) {
		List<TokenNode> tokenNodes = new ArrayList<>();
		tokenNodes.addAll(tokenNodeOpenBrace().tokenNodes());
		tokenNodes.addAll(tokenNodeRest(expression).tokenNodes());
		tokenNodes.addAll(tokenNodeCloseBrace().tokenNodes());

		List<Node> parsedNodes = Arrays.asList(new AcknowledgeNode());

		return new ExpressionAndNodes(expression, tokenNodes, parsedNodes);
	}



}

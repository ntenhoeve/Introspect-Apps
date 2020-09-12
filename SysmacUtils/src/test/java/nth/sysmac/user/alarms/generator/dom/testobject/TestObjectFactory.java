package nth.sysmac.user.alarms.generator.dom.testobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule.AcknowledgeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Rest;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.CloseBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.OpenBrace;

public class TestObjectFactory {

	private static final String REST = "Rest";

	// ====== TOKEN NODES
	public static ExpressionAndNodes tokenNodeRest() {
		return tokenNodeRest(REST);
	}
	
	public static ExpressionAndNodes tokenNodeRest(String expression) {
		return new ExpressionAndNodes(expression, new Rest());
	}

	private static ExpressionAndNodes tokenNodeOpenBrace() {
		return new ExpressionAndNodes("{", new OpenBrace());
	}

	private static ExpressionAndNodes tokenNodeCloseBrace() {
		return new ExpressionAndNodes("}", new CloseBrace());
	}

	// ====== PARCED NODES
	public static ExpressionAndNodes braceNode() {
		String expression = "{" + REST + "}";

		List<TokenNode> tokenNodes = new ArrayList<>();
		tokenNodes.addAll(tokenNodeOpenBrace().tokenNodes());
		tokenNodes.addAll(tokenNodeRest().tokenNodes());
		tokenNodes.addAll(tokenNodeCloseBrace().tokenNodes());

		List<Node> restNode=new ArrayList<>(tokenNodeRest().tokenNodes());
		List<Node> parsedNodes = Arrays.asList(new BraceNode(restNode));

		return new ExpressionAndNodes(expression, tokenNodes, parsedNodes);
	}

	public static ExpressionAndNodes acknowledgeNode() {
		return acknowledgeNode("ack");
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

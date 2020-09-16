package nth.sysmac.user.alarms.generator.dom.testobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.token.parser.Rest;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.AcknowledgeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.DetailsNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.CloseBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Equal;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.OpenBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.WhiteSpace;

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

	public static ExpressionAndNodes tokenNodeWhiteSpace() {
		return new ExpressionAndNodes(" ", new WhiteSpace());
	}


	private static ExpressionAndNodes tokenNodeEqual() {
		return new ExpressionAndNodes("=", new Equal());
	}

	// ====== PARCED NODES
	public static ExpressionAndNodes braceNode() {
		ExpressionAndNodes expressionAndNodes = tokenNodeOpenBrace().append(tokenNodeRest())
				.append(tokenNodeCloseBrace());
		return braceNode(expressionAndNodes);
	}

	public static ExpressionAndNodes braceNode(ExpressionAndNodes expressionAndNodesWithBraces) {
		String expression = expressionAndNodesWithBraces.expression();
		List<TokenNode> tokenNodes = expressionAndNodesWithBraces.tokenNodes();
		List<Node> childNodes = new ArrayList<>(expressionAndNodesWithBraces.parcedNodes());
		if (childNodes.size() > 0 && new TokenNodePredicate(new OpenBrace()).test(childNodes.get(0))) {
			childNodes.remove(0);
		}
		if (childNodes.size() > 0
				&& new TokenNodePredicate(new CloseBrace()).test(childNodes.get(childNodes.size() - 1))) {
			childNodes.remove(childNodes.size() - 1);
		}
		List<Node> parsedNodes = Arrays.asList(new BraceNode(childNodes));
		return new ExpressionAndNodes(expression, tokenNodes, parsedNodes);
	}

	public static ExpressionAndNodes acknowledgeNode() {
		String ack;
		int capitalCase = Random.integer().forRange(0, 2).generate();
		switch (capitalCase) {
		case 0:
			ack = "ack";
			break;
		case 1:
			ack = "Ack";
		default:
			ack = "ACK";
			break;
		}

		ExpressionAndNodes expressionAndNodes = tokenNodeOpenBrace()//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeRest(ack))//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeCloseBrace());

		List<Node> parsedNodes = Arrays.asList(new AcknowledgeNode());

		return new ExpressionAndNodes(expressionAndNodes.expression(), expressionAndNodes.tokenNodes(), parsedNodes);
	}

	public static ExpressionAndNodes detailsNode(ExpressionAndNodes  details) {
		String d=Random.character().forCharacters("dD").generate().toString();
		ExpressionAndNodes expressionAndNodes = tokenNodeOpenBrace()//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeRest(d))//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
		.append(tokenNodeEqual())//
		.append(details)//
		.append(tokenNodeCloseBrace());

		List<Node> children=new ArrayList<>(details.tokenNodes());
		List<Node> parsedNodes = Arrays.asList(new DetailsNode(children));

		return new ExpressionAndNodes(expressionAndNodes.expression(), expressionAndNodes.tokenNodes(), parsedNodes);
	}

}

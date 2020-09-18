package nth.sysmac.user.alarms.generator.dom.testobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.token.parser.Rest;
import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.text.CharacterSet;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge.AcknowledgeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details.DetailsNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.CloseBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Equal;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.OpenBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.UnsignedInteger;
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

	public static ExpressionAndNodes tokenNodeEqual() {
		return new ExpressionAndNodes("=", new Equal());
	}

	private static ExpressionAndNodes tokenNodeUnsignedInteger(int value) {
		String expression = Integer.toString(value);
		return new ExpressionAndNodes(expression, new UnsignedInteger());
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

	public static ExpressionAndNodes detailsNode() {
		ExpressionAndNodes details = TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 3)//
				.append(TestObjectFactory.tokenNodeRest())//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2));
		return detailsNode(details);
	}

	public static ExpressionAndNodes detailsNode(ExpressionAndNodes details) {
		ExpressionAndNodes expressionAndNodes = tokenNodeOpenBrace()//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeRest(Random.letterCase("d").generate()))//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeEqual())//
				.append(details)//
				.append(tokenNodeCloseBrace());

		List<Node> children = new ArrayList<>(details.tokenNodes());
		List<Node> parsedNodes = Arrays.asList(new DetailsNode(children));

		return new ExpressionAndNodes(expressionAndNodes.expression(), expressionAndNodes.tokenNodes(), parsedNodes);
	}

	public static ExpressionAndNodes priorityNode(Priority priority) {
		ExpressionAndNodes expressionAndNodes = tokenNodeOpenBrace()//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeRest(Random.letterCase("p").generate()))//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeEqual())//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeRest(Random.letterCase(priority.getAbbreviation()).generate()))//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeCloseBrace());

		List<Node> parsedNodes = Arrays.asList(new PriorityNode(priority));

		return new ExpressionAndNodes(expressionAndNodes.expression(), expressionAndNodes.tokenNodes(), parsedNodes);
	}

	public static ExpressionAndNodes priorityNode() {
		Priority randomPriority = (Priority) Random.fromEnum(Priority.class).generate();
		return priorityNode(randomPriority);
	}

	public static ExpressionAndNodes componentCodeNode() {
		int page = Random.integer().forRange(1, 9999).generate();
		char letter = Random.character().forCharacters(CharacterSet.LETTERS).generate();
		int colomn = Random.integer().forRange(1, 8).generate();

		ExpressionAndNodes expressionAndNodes = tokenNodeUnsignedInteger(page)//
				.append(tokenNodeRest(Character.toString(letter)))//
				.append(tokenNodeUnsignedInteger(colomn));

		List<Node> parsedNodes = Arrays.asList(new ComponentCodeNode(page, letter, colomn));

		return new ExpressionAndNodes(expressionAndNodes.expression(), expressionAndNodes.tokenNodes(), parsedNodes);
	}
}

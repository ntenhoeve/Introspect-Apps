package nth.sysmac.user.alarms.generator.dom.testobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.token.parser.Rest;
import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.text.CharacterSet;
import nth.reflect.util.random.generator.text.LetterCase;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge.AcknowledgeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details.DetailsNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details.DetailsPredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityPredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.CloseBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Comma;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Dash;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Dot;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Equal;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.OpenBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.UnsignedInteger;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.WhiteSpace;

public class TestObjectFactory {

	// ====== TOKEN NODES

	public static ExpressionAndNodes tokenNodeRandomRest() {
		String expression = Random.string().forCharSet(CharacterSet.letters(LetterCase.UPPER_AND_LOWER)).generate();
		return tokenNodeRest(expression);
	}

	public static ExpressionAndNodes tokenNodeRest(String expression) {
		return new ExpressionAndNodes(expression, new Rest());
	}

	public static ExpressionAndNodes tokenNodeDot() {
		return new ExpressionAndNodes(".", new Dot());
	}

	public static ExpressionAndNodes tokenNodeDash() {
		return new ExpressionAndNodes("-", new Dash());
	}

	static ExpressionAndNodes tokenNodeComma() {
		return new ExpressionAndNodes(",", new Comma());
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

	public static ExpressionAndNodes tokenNodeUnsignedInteger(int value) {
		String expression = Integer.toString(value);
		return new ExpressionAndNodes(expression, new UnsignedInteger());
	}

	static ExpressionAndNodes tokenNodeRandomUnsignedInteger() {
		int i = Random.integer().forRange(0, 999).generate();
		return tokenNodeUnsignedInteger(i);
	}

	// === COMBINED ===
	public static ExpressionAndNodes tokenNodeRandom() {
		return new RandomTokenNode();
	}

	public static ExpressionAndNodes surroundWithRandomWhiteSpace(ExpressionAndNodes toBeSurrounded) {
		return tokenNodeWhiteSpace().repeatRandomly(0, 2)//
				.append(toBeSurrounded)//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 2));
	}

	public static ExpressionAndNodes precedingWithRandomTokens(ExpressionAndNodes expressionAndNodes) {
		return tokenNodeRandom().repeatRandomly(0, 2)//
				.append(expressionAndNodes);
	}

	public static ExpressionAndNodes followedWithRandomTokens(ExpressionAndNodes expressionAndNodes) {
		return expressionAndNodes//
				.append(tokenNodeRandom().repeatRandomly(0, 2));
	}

	public static ExpressionAndNodes surroundWithRandomTokens(ExpressionAndNodes toBeSurrounded) {
		return tokenNodeRandom().repeatRandomly(0, 2)//
				.append(toBeSurrounded)//
				.append(tokenNodeRandom().repeatRandomly(0, 2));
	}

	public static ExpressionAndNodes seperatedByTokenNodeCommas(List<ExpressionAndNodes> allExpressionAndNodes) {
		ExpressionAndNodes result = new ExpressionAndNodes();
		for (ExpressionAndNodes expressionAndNodes : allExpressionAndNodes) {
			if (!result.expression().isEmpty()) {
				result = result.append(tokenNodeComma());
			}
			result = result.append(expressionAndNodes);
		}
		return result;
	}

	// ====== PARCED NODES
	public static ExpressionAndNodes braceNode() {
		return braceNode(tokenNodeRandomRest().repeatRandomly(1, 2));
	}

	public static ExpressionAndNodes braceNode(ExpressionAndNodes children) {
		ExpressionAndNodes allNodes = tokenNodeOpenBrace()//
				.append(children)//
				.append(tokenNodeCloseBrace());

		List<Node> parsedNodes = Arrays.asList(new BraceNode(children.parcedNodes()));
		return new ExpressionAndNodes(allNodes.expression(), allNodes.tokenNodes(), parsedNodes);
	}

	public static ExpressionAndNodes acknowledgeNode() {
		String ack = Random.letterCase("ack").generate();

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
				.append(TestObjectFactory.tokenNodeRandom().repeatRandomly(1, 5));
		return detailsNode(details);
	}

	public static ExpressionAndNodes detailsNode(ExpressionAndNodes details) {
		ExpressionAndNodes expressionAndNodes = tokenNodeOpenBrace()//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeRest(Random.letterCase(DetailsPredicate.DETAILS).generate()))//
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
				.append(tokenNodeRest(Random.letterCase(PriorityPredicate.PRIORITY_ABBREVIATION).generate()))//
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
		char letter = Random.character().forCharacters(CharacterSet.LETTERS).generate();
		return componentCodeNode(letter);
	}
	
	public static ExpressionAndNodes componentCodeNode(char letter) {
		int page = Random.integer().forRange(1, 9999).generate();
		int colomn = Random.integer().forRange(1, 8).generate();

		ExpressionAndNodes expressionAndNodes = tokenNodeUnsignedInteger(page)//
				.append(tokenNodeRest(Character.toString(letter)))//
				.append(tokenNodeUnsignedInteger(colomn));

		List<Node> parsedNodes = Arrays.asList(new ComponentCodeNode(page, letter, colomn));

		return new ExpressionAndNodes(expressionAndNodes.expression(), expressionAndNodes.tokenNodes(), parsedNodes);
	}

	public static ExpressionAndNodes bracedAttribute(BracedAttributeName name, ExpressionAndNodes values) {
		ExpressionAndNodes expressionAndNodes = tokenNodeRest(name.getName())//
				.append(tokenNodeWhiteSpace().repeatRandomly(0, 3))//
				.append(tokenNodeEqual());
		expressionAndNodes = expressionAndNodes.append(values);

		List<Node> parcedNodes = Arrays.asList(new BracedAttributeNode(name, values.parcedNodes()));

		return new ExpressionAndNodes(expressionAndNodes.expression(), expressionAndNodes.tokenNodes(), parcedNodes);
	}

	public static ExpressionAndNodes bracedAttributeWithRandomValues(BracedAttributeName name) {
		ExpressionAndNodes randomValues = tokenNodeRandom().repeatRandomly(1, 5);
		return bracedAttribute(name, randomValues);
	}

	public static ExpressionAndNodes bracedAttributeWithValueSurroundedByRandomValues(BracedAttributeName name,
			ExpressionAndNodes attributeValueToBeSurrounded) {

		ExpressionAndNodes attributeValues = TestObjectFactory
				.attributeValueWithOtherRandomValues(attributeValueToBeSurrounded);

		return bracedAttribute(name, attributeValues);
	}

	public static ExpressionAndNodes braceNodeWithAttributes(List<ExpressionAndNodes> attributes) {
		ExpressionAndNodes expressionAndNodesWithBraces = tokenNodeOpenBrace();
		for (ExpressionAndNodes attribute : attributes) {
			expressionAndNodesWithBraces = expressionAndNodesWithBraces.append(attribute);
		}
		expressionAndNodesWithBraces = expressionAndNodesWithBraces.append(tokenNodeCloseBrace());
		ExpressionAndNodes braceNode = braceNode(expressionAndNodesWithBraces);
		return braceNode;
	}

	

	public static ExpressionAndNodes attributeValueWithOtherRandomValues(
			ExpressionAndNodes attributeValueToBeSurrounded) {
		List<ExpressionAndNodes> attributeValues = new ArrayList<>();
		int n = Random.integer().forRange(0, 2).generate();
		for (int i = 0; i < n; i++) {
			attributeValues.add(tokenNodeRandomRest());
		}
		attributeValues.add(attributeValueToBeSurrounded);
		n = Random.integer().forRange(0, 2).generate();
		for (int i = 0; i < n; i++) {
			attributeValues.add(tokenNodeRandomRest());
		}
		return seperatedByTokenNodeCommas(attributeValues);
	}

	

}

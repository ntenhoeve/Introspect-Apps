package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.text.CharacterSet;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.RandomExpressionAndNodesFactory;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

public class DerivedComponentCodeTestFactory extends RandomExpressionAndNodesFactory {

	private static final HiddenComponentCodeTestFactory HIDDEN_COMPONENT_CODE_TEST_FACTORY = new HiddenComponentCodeTestFactory();

	@Override
	public ExpressionAndNodes create() {
		boolean hasSameLetterAsHiddenComponentCode = Random.bool().generate();
		boolean hasHidenComponentCodesWithSameLetter = Random.bool().generate();
		int index = 1;

		Character derivedLetter = Random.character().forCharacters(CharacterSet.LETTERS).generate();
		String otherLetters = CharacterSet.LETTERS.replace(derivedLetter.toString(), "");

		ExpressionAndNodes expressionAndNodes = new ExpressionAndNodes();

		expressionAndNodes = expressionAndNodes.append(createHiddenComponentCodesWithSpacesAndRestNode(otherLetters,
				Random.integer().forRange(1, 4).generate()));
		if (hasSameLetterAsHiddenComponentCode) {
			int repetition = getMaxRepetition(hasHidenComponentCodesWithSameLetter);
			index = Random.integer().forRange(1, repetition).generate();
			expressionAndNodes = expressionAndNodes
					.append(createHiddenComponentCodesWithSpacesAndRestNode(derivedLetter.toString(), repetition));
		}
		expressionAndNodes = expressionAndNodes.append(createHiddenComponentCodesWithSpacesAndRestNode(otherLetters,
				Random.integer().forRange(0, 3).generate()));

		ExpressionAndNodes letter = TestObjectFactory.tokenNodeRest(derivedLetter.toString());

		ExpressionAndNodes braceChildren = new ExpressionAndNodes()//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2)).append(letter)//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2));

		if (index > 1) {
			braceChildren = braceChildren.append(TestObjectFactory.tokenNodeUnsignedInteger(index))//
					.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2));
		}

		ExpressionAndNodes derivedComponentCode = TestObjectFactory.braceNode(braceChildren);

		List<Node> parsedNodes = createParcedNodes(expressionAndNodes, derivedLetter, index);

		expressionAndNodes = expressionAndNodes.append(new ExpressionAndNodes(derivedComponentCode.expression(),
				derivedComponentCode.tokenNodes(), parsedNodes));
		return expressionAndNodes;
	}

	private int getMaxRepetition(boolean hasHidenComponentCodesWithSameLetter) {
		if (hasHidenComponentCodesWithSameLetter) {
			return Random.integer().forRange(1, 5).generate();
		} else {
			return 0;
		}
	}

	private ExpressionAndNodes createHiddenComponentCodesWithSpacesAndRestNode(String letters, int repetition) {
		ExpressionAndNodes expressionAndNodes = new ExpressionAndNodes();
		for (int i = 0; i < repetition; i++) {
			expressionAndNodes = expressionAndNodes //
					.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
					.append(HIDDEN_COMPONENT_CODE_TEST_FACTORY.create(letters))//
					.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
					.append(TestObjectFactory.tokenNodeRandomRest().repeatRandomly(0, 2));
		}
		return expressionAndNodes;
	}

	private List<Node> createParcedNodes(ExpressionAndNodes expressionAndNodes, Character letter, int index) {
		List<ComponentCodeNode> hiddenComponentCodes = getHiddenComponentCodes(expressionAndNodes);
		DerivedComponentCodeNode derivedComponentCode = new DerivedComponentCodeNode(hiddenComponentCodes, letter,
				index);
		List<Node> parcedNodes = new ArrayList<>();
		parcedNodes.add(derivedComponentCode);
		return parcedNodes;
	}

	private List<ComponentCodeNode> getHiddenComponentCodes(ExpressionAndNodes expressionAndNodes) {
		return expressionAndNodes.parcedNodes().stream().filter(n -> n instanceof HiddenComponentCodeNode)
				.map(n -> (ComponentCodeNode) n).collect(Collectors.toList());
	}

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip.even.SkipEvenCounterNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip.even.SkipEvenCounterRule;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.RandomExpressionAndNodesFactory;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

public class SkipCounterTestAttributeFactory extends RandomExpressionAndNodesFactory {

	@Override
	public ExpressionAndNodes create() {
		List<ExpressionAndNodes> skipCountersAttributeValues = createList();
		ExpressionAndNodes expressionAndNodes = TestObjectFactory
				.seperatedByTokenNodeCommas(skipCountersAttributeValues);
		return expressionAndNodes;
	}

	@Override
	public List<ExpressionAndNodes> createList() {
		List<ExpressionAndNodes> skipCounters = new ArrayList<>();
		skipCounters.addAll(randomSkipRandomEvenUnevenCounterAttributeValues());
//		skipCounters.addAll(new SkipSingleCounterTestAttributeFactory().createList());
//		skipCounters.addAll(new SkipMaxCounterTestAttributeFactory().createList());
//		skipCounters.addAll(new SkipMinMaxCounterTestAttributeFactory().createList());
//		skipCounters.addAll(new SkipSinglePageCounterTestAttributeFactory().createList());
//		skipCounters.addAll(new SkipMaxPageCounterTestAttributeFactory().createList());
//		skipCounters.addAll(new SkipMinMaxPageCounterTestAttributeFactory().createList());
		Collections.shuffle(skipCounters);
		return skipCounters;
	}

	private List<ExpressionAndNodes> randomSkipRandomEvenUnevenCounterAttributeValues() {
		int i = Random.integer().forRange(0, 2).generate();
		List<ExpressionAndNodes> skipCounterAttributeValues = new ArrayList<>();
		switch (i) {
		case 0:
			skipCounterAttributeValues.add(createEvenAttributeValue());
			break;
		case 1:
			skipCounterAttributeValues.add(createEvenAttributeValue());
//TODO!!!
//			skipCounterAttributeValues.add(createUnevenAttributeValue());
		}
		return skipCounterAttributeValues;
	}

	public ExpressionAndNodes createEvenAttributeValue() {
		String evenAbbreviation = Random.letterCase(SkipEvenCounterRule.EVEN_ABBREVIATION).generate();
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeRest(evenAbbreviation);
		List<Node> parcedNodes = Arrays.asList(new SkipEvenCounterNode());
		ExpressionAndNodes attribute = new ExpressionAndNodes(expressionAndNodes.expression(),
				expressionAndNodes.tokenNodes(), parcedNodes);
		return TestObjectFactory.surroundWithRandomWhiteSpace(attribute);
	}
//
//	public ExpressionAndNodes createUnevenAttributeValue() {
//		String evenAbbreviation = Random.letterCase(SkipUnevenCounterRule.UNEVEN_ABBREVIATION).generate();
//		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeRest(evenAbbreviation);
//		List<Node> parcedNodes = Arrays.asList(new SkipUnevenCounterNode());
//		ExpressionAndNodes attribute = new ExpressionAndNodes(expressionAndNodes.expression(),
//				expressionAndNodes.tokenNodes(), parcedNodes);
//		return TestObjectFactory.surroundWithRandomWhiteSpace(attribute);
//	}
}

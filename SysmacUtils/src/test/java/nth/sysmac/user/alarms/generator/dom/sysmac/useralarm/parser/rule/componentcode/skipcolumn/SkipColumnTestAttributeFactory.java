package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipMaxColumnTestAttributeFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipMinMaxColumnTestAttributeFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipSingleColumnTestAttributeFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.even.SkipEvenColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.even.SkipEvenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange.SkipMaxPageColumnTestAttributeFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange.SkipMinMaxPageColumnTestAttributeFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange.SkipSinglePageColumnTestAttributeFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.uneven.SkipUnevenColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.uneven.SkipUnevenColumnRule;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.RandomExpressionAndNodesFactory;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

public class SkipColumnTestAttributeFactory extends RandomExpressionAndNodesFactory {

	@Override
	public ExpressionAndNodes create() {
		List<ExpressionAndNodes> skipColumnsAttributeValues = createList();
		ExpressionAndNodes expressionAndNodes = TestObjectFactory
				.seperatedByTokenNodeCommas(skipColumnsAttributeValues);
		return expressionAndNodes;
	}

	@Override
	public List<ExpressionAndNodes> createList() {
		List<ExpressionAndNodes> skipColumns = new ArrayList<>();
		skipColumns.addAll(randomSkipRandomEvenUnevenColumnAttributeValues());
		skipColumns.addAll(new SkipSingleColumnTestAttributeFactory().createList());
		skipColumns.addAll(new SkipMaxColumnTestAttributeFactory().createList());
		skipColumns.addAll(new SkipMinMaxColumnTestAttributeFactory().createList());
		skipColumns.addAll(new SkipSinglePageColumnTestAttributeFactory().createList());
		skipColumns.addAll(new SkipMaxPageColumnTestAttributeFactory().createList());
		skipColumns.addAll(new SkipMinMaxPageColumnTestAttributeFactory().createList());
		Collections.shuffle(skipColumns);
		return skipColumns;
	}

	private List<ExpressionAndNodes> randomSkipRandomEvenUnevenColumnAttributeValues() {
		int i = Random.integer().forRange(0, 2).generate();
		List<ExpressionAndNodes> skipColumnAttributeValues = new ArrayList<>();
		switch (i) {
		case 0:
			skipColumnAttributeValues.add(createEvenAttributeValue());
			break;
		case 1:
			skipColumnAttributeValues.add(createUnevenAttributeValue());
		}
		return skipColumnAttributeValues;
	}

	public ExpressionAndNodes createEvenAttributeValue() {
		String evenAbbreviation = Random.letterCase(SkipEvenColumnRule.EVEN_ABBREVIATION).generate();
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeRest(evenAbbreviation);
		List<Node> parcedNodes = Arrays.asList(new SkipEvenColumnNode());
		ExpressionAndNodes attribute = new ExpressionAndNodes(expressionAndNodes.expression(),
				expressionAndNodes.tokenNodes(), parcedNodes);
		return TestObjectFactory.surroundWithRandomWhiteSpace(attribute);
	}

	public ExpressionAndNodes createUnevenAttributeValue() {
		String evenAbbreviation = Random.letterCase(SkipUnevenColumnRule.UNEVEN_ABBREVIATION).generate();
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeRest(evenAbbreviation);
		List<Node> parcedNodes = Arrays.asList(new SkipUnevenColumnNode());
		ExpressionAndNodes attribute = new ExpressionAndNodes(expressionAndNodes.expression(),
				expressionAndNodes.tokenNodes(), parcedNodes);
		return TestObjectFactory.surroundWithRandomWhiteSpace(attribute);
	}
}

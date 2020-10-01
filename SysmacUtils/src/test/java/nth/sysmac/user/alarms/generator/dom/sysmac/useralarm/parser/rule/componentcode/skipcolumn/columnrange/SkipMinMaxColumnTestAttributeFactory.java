package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.RandomExpressionAndNodesFactory;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

public class SkipMinMaxColumnTestAttributeFactory extends RandomExpressionAndNodesFactory {
	@Override
	public ExpressionAndNodes create() {
		int minColumn = Random.integer().forRange(2, 7).generate();
		int maxColumn = Random.integer().forRange(minColumn, 8).generate();
		return create(minColumn, maxColumn);
	}

	public ExpressionAndNodes create(int minColumn, int maxColumn) {
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeUnsignedInteger(minColumn)//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeDash())//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeUnsignedInteger(maxColumn));
		List<Node> parcedNodes = Arrays.asList(new SkipColumnRangeNode(minColumn, maxColumn));
		ExpressionAndNodes attributeValue = new ExpressionAndNodes(expressionAndNodes.expression(),
				expressionAndNodes.tokenNodes(), parcedNodes);
		return TestObjectFactory.surroundWithRandomWhiteSpace(attributeValue);
	}
}

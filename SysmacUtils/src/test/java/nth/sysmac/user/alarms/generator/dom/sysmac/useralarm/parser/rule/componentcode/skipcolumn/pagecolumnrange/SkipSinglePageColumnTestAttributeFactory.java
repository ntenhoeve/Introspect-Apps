package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.RandomExpressionAndNodesFactory;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

public class SkipSinglePageColumnTestAttributeFactory extends RandomExpressionAndNodesFactory{


	@Override
	public ExpressionAndNodes create() {
		int page=Random.integer().forMax(999).generate();
		int column=Random.integer().forRange(1,8).generate();
		return create(page,column);
	}

	public ExpressionAndNodes create(int page, int column) {
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeUnsignedInteger(page)//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeDot())//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeUnsignedInteger(column));
		List<Node> parcedNodes = Arrays.asList(new SkipPageColumnRangeNode(page, column, page, column));
		ExpressionAndNodes attributeValue = new ExpressionAndNodes(expressionAndNodes.expression(),
				expressionAndNodes.tokenNodes(), parcedNodes);
		return TestObjectFactory.surroundWithRandomWhiteSpace(attributeValue);
	}
}

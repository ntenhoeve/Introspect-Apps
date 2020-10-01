package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.RandomExpressionAndNodesFactory;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

public class SkipMinMaxPageColumnTestAttributeFactory extends RandomExpressionAndNodesFactory {

	@Override
	public ExpressionAndNodes create() {
		int minPage= Random.integer().forRange(1, 100).generate();
		int minColumn = Random.integer().forRange(2, 7).generate();
		int maxPage= minPage+Random.integer().forRange(0, 100).generate();
		int maxColumn = Random.integer().forRange(2, 7).generate();
		return create(minPage, minColumn, maxPage, maxColumn);
	}
	
	public ExpressionAndNodes create(int minPage, int minColumn, int maxPage,
			int maxColumn) {
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeUnsignedInteger(minPage)//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeDot())//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeUnsignedInteger(minColumn))//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeDash())//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeUnsignedInteger(maxPage))//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeDot())//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeUnsignedInteger(maxColumn));
		List<Node> parcedNodes = Arrays.asList(new SkipPageColumnRangeNode(minPage,minColumn, maxPage, maxColumn));
		ExpressionAndNodes attributeValue = new ExpressionAndNodes(expressionAndNodes.expression(),
				expressionAndNodes.tokenNodes(), parcedNodes);
		return TestObjectFactory.surroundWithRandomWhiteSpace(attributeValue);
	}
	
	

}

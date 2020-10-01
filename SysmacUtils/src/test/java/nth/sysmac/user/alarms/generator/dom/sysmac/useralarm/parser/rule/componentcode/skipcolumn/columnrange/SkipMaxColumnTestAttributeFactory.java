package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.RandomExpressionAndNodesFactory;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

public class SkipMaxColumnTestAttributeFactory extends RandomExpressionAndNodesFactory {

	@Override
	public ExpressionAndNodes create() {
		int columnNumber = Random.integer().forRange(2, 7).generate();
		return create(columnNumber);
	}

	
	public ExpressionAndNodes create(int columnNumber) {
		ExpressionAndNodes expressionAndNodes = TestObjectFactory.tokenNodeDash()//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.tokenNodeUnsignedInteger(columnNumber));
		List<Node> parcedNodes = Arrays.asList(new SkipColumnRangeNode(1, columnNumber));
		ExpressionAndNodes attributeValue = new ExpressionAndNodes(expressionAndNodes.expression(),
				expressionAndNodes.tokenNodes(), parcedNodes);
		return TestObjectFactory.surroundWithRandomWhiteSpace(attributeValue);
	}


}

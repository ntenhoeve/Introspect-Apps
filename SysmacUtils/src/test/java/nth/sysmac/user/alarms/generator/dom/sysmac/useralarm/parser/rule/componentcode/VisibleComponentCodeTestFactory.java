package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnTestAttributeFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumns;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipColumnRangeNode;
import nth.sysmac.user.alarms.generator.dom.testobject.ExpressionAndNodes;
import nth.sysmac.user.alarms.generator.dom.testobject.RandomExpressionAndNodesFactory;
import nth.sysmac.user.alarms.generator.dom.testobject.TestObjectFactory;

public class VisibleComponentCodeTestFactory extends RandomExpressionAndNodesFactory {

	@Override
	public ExpressionAndNodes create() {
		ExpressionAndNodes componentCode = TestObjectFactory.componentCodeNode();
		List<ExpressionAndNodes> skipAttributeValues = new SkipColumnTestAttributeFactory().createList();
		ExpressionAndNodes skipAttributeValuesWithCommas = TestObjectFactory
				.seperatedByTokenNodeCommas(skipAttributeValues);
		ExpressionAndNodes skipAttribute = TestObjectFactory.bracedAttribute(BracedAttributeName.SKIP,
				skipAttributeValuesWithCommas);
		ExpressionAndNodes visibleComponentCode = new ExpressionAndNodes()//
				.append(componentCode)//
				.append(TestObjectFactory.tokenNodeWhiteSpace().repeatRandomly(0, 2))//
				.append(TestObjectFactory.braceNode(skipAttribute));

		List<Node> parsedNodes = createParcedNode(componentCode, skipAttributeValues);

		return new ExpressionAndNodes(visibleComponentCode.expression(), visibleComponentCode.tokenNodes(),
				parsedNodes);
	}

	private List<Node> createParcedNode(ExpressionAndNodes componentCode,
			List<ExpressionAndNodes> skipAttributeValues) {
		SkipColumns skipColumns = createSkipColumnNodes(skipAttributeValues);
		ComponentCodeNode parsedComponentCode = (ComponentCodeNode) componentCode.parcedNodes().get(0);
		int page = parsedComponentCode.getPage();
		char letter = parsedComponentCode.getLetter();
		int column = parsedComponentCode.getColumn();
		VisibleComponentCodeNode parsedNode = new VisibleComponentCodeNode(page, letter, column, skipColumns);
		List<Node> parsedNodes = new ArrayList<>();
		parsedNodes.add(parsedNode);
		return parsedNodes;
	}

	private SkipColumns createSkipColumnNodes(List<ExpressionAndNodes> skipAttributeValues) {
		SkipColumns skipColumns = new SkipColumns();
		for (ExpressionAndNodes skipAttributeValue : skipAttributeValues) {
			List<Node> nodes = skipAttributeValue.parcedNodes();
			for (Node node : nodes) {
				if (node instanceof SkipColumnRangeNode) {
					SkipColumnNode skipColumnNode = (SkipColumnNode) node;
					skipColumns.add(skipColumnNode);
				}
			}
		}
		return skipColumns;
	}

}

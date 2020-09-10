package nth.sysmac.user.alarms.generator.dom.testobject;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.impl.AcknowledgeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.impl.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Rest;

public class TestObjectFactory {

	private static final String REST = "Rest";

	public static ExpressionAndNode tokenNodeRest() {
		return new ExpressionAndNode(REST, new Rest());
	}
	
	public static ExpressionAndNode braceNode() {
		return new ExpressionAndNode("{"+REST+"}", new BraceNode(tokenNodeRest().nodes()));
	}

	public static ExpressionAndNode acknowledgeNode() {
		return new ExpressionAndNode("{ack}", new AcknowledgeNode());
	}

	public static ExpressionAndNode acknowledgeNode(String expression) {
		return new ExpressionAndNode(expression, new AcknowledgeNode());
	}
	
}

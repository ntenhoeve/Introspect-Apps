package nth.sysmac.user.alarms.generator.dom.testobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.provider.Arguments;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenRule;

public class ExpressionAndNode {

	private final String expression;
	private final List<Node> nodes;

	public ExpressionAndNode(String expression, List<Node> node) {
		this.expression = expression;
		this.nodes = node;
	}

	public ExpressionAndNode(String expression, TokenRule tokenRule) {
		this.expression=expression;
		this.nodes=Arrays.asList(new TokenNode( tokenRule, expression));
	}

	public ExpressionAndNode(String expression, Node... nodes) {
		this.expression=expression;
		this.nodes=Arrays.asList(nodes);
	}

	public String expression() {
		return expression;
	}

	public List<Node> nodes() {
		return nodes;
	}
	
	public ExpressionAndNode append(ExpressionAndNode appendix) {
		String newExpression = expression+appendix.expression();
		List<Node> newNodes = new ArrayList<>();
		newNodes.addAll(nodes);
		newNodes.addAll(appendix.nodes);
		return new ExpressionAndNode(newExpression, newNodes);
	}

	public Arguments arguments() {
		return Arguments.of(expression,nodes);
	}


}

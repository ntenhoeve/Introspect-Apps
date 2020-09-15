package nth.sysmac.user.alarms.generator.dom.testobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.provider.Arguments;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.token.parser.TokenRule;

public class ExpressionAndNodes {

	private final String expression;
	private final List<TokenNode> tokenNodes;
	private final List<Node> parsedNodes;

	public ExpressionAndNodes(String expression, List<TokenNode> tokenNodes, List<Node> parsedNodes) {
		this.expression = expression;
		this.tokenNodes= tokenNodes;
		this.parsedNodes = parsedNodes;
	}

	public ExpressionAndNodes(String expression, TokenRule tokenRule) {
		this.expression=expression;
		this.tokenNodes=Arrays.asList(new TokenNode( tokenRule, expression));
		this.parsedNodes=new ArrayList<>(tokenNodes);
	}

//	public ExpressionAndNodes(String expression, Node... parsedNodes) {
//		this.expression=expression;
//		this.tokenNodes=new ArrayList<>();
//		this.parsedNodes=Arrays.asList(parsedNodes);
//	}

	public String expression() {
		return expression;
	}

	public List<Node> parcedNodes() {
		return parsedNodes;
	}
	public List<TokenNode> tokenNodes() {
		return tokenNodes;
	}
	
	
	public ExpressionAndNodes append(ExpressionAndNodes appendix) {
		String newExpression = expression+appendix.expression();
		List<TokenNode> newTokenNodes = new ArrayList<>();
		newTokenNodes.addAll(tokenNodes);
		newTokenNodes.addAll(appendix.tokenNodes);
		List<Node> newParcedNodes = new ArrayList<>();
		newParcedNodes.addAll(parsedNodes);
		newParcedNodes.addAll(appendix.parsedNodes);

		return new ExpressionAndNodes(newExpression, newTokenNodes, newParcedNodes);
	}

	public Arguments arguments() {
		return Arguments.of(expression,parsedNodes);
	}


}

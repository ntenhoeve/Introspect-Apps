package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Token;

/**
 * A {@link NodeParser} creates a {@link ParseTree} and then combines
 * {@link Node} children by replacing them with other more detailed
 * {@link Node}s as is defined by {@link Rule}s.
 * 
 * @author nilsth
 *
 */
public class NodeParser {

	private final List<NodeRule<? extends Node>> nodeRules;

	public NodeParser(List<NodeRule<? extends Node>> nodeRules) {
		this.nodeRules = nodeRules;
	}

	public ParseTree parse(List<Token> tokens) {
		ParseTree parseTree = new ParseTree(tokens);

		replaceAll(parseTree);

		return parseTree;
	}

	private void replaceAll(ParseTree parseTree) {
		List<Node> children = parseTree.getChildren();
		boolean doneReplacement = false;
		do {
			for (NodeRule<? extends Node> nodeRule : nodeRules) {
				doneReplacement = replaceAll(children, nodeRule);
			}
		} while (doneReplacement);
	}

	private boolean replaceAll(List<Node> nodes, NodeRule<? extends Node> node) {
		boolean foundReplacement = false;
		boolean doneReplacement = false;
		do {
			foundReplacement = false;
			Results results = node.find(nodes);
			if (results.hasResults()) {
				foundReplacement = true;
				Node replacementNode = node.createReplacement(results);
				results.replaceFoundNodesWith(replacementNode);
				doneReplacement = true;
			}
		} while (foundReplacement);

		doneReplacement = doneReplacement || replaceAllInChildrenRecursively(nodes, node);
		return doneReplacement;
	}

	private boolean replaceAllInChildrenRecursively(List<Node> children, NodeRule<? extends Node> nodeReplacement) {
		boolean doneReplacement = false;
		for (Node child : children) {
			doneReplacement = replaceAll(child.getChildren(), nodeReplacement);
		}
		return doneReplacement;
	}
}

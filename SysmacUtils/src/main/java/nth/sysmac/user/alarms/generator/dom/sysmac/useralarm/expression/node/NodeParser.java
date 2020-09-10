package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Token;

/**
 * A {@link NodeParser} creates a {@link ParseTree} and then combines
 * {@link NodeChildren} by replacing them with other more detailed {@link Node}s
 * as is defined by {@link NodeReplacement}s.
 * 
 * @author nilsth
 *
 */
public class NodeParser {

	private final List<NodeReplacement<? extends Node>> nodeReplacements;

	public NodeParser(List<NodeReplacement<? extends Node>> nodeReplacements) {
		this.nodeReplacements = nodeReplacements;
	}

	public ParseTree parse(List<Token> tokens) {
		ParseTree parseTree = new ParseTree(tokens);

		replaceAll(parseTree);

		return parseTree;
	}

	private void replaceAll(ParseTree parseTree) {
		NodeChildren nodeChildren = parseTree.getChildren();
		boolean doneReplacement = false;
		do {
			for (NodeReplacement<? extends Node> nodeReplacement : nodeReplacements) {
				doneReplacement = replaceAllInChildren(nodeChildren, nodeReplacement);
			}
		} while (doneReplacement);
	}

	private boolean replaceAllInChildren(NodeChildren nodeChildren, NodeReplacement<? extends Node> nodeReplacement) {
		boolean foundReplacement = false;
		boolean doneReplacement = false;
		do {
			foundReplacement = false;
			MatchResult matchResult = nodeReplacement.find(nodeChildren);
			if (matchResult.found()) {
				foundReplacement = true;
				Node replacementNode = nodeReplacement.createReplacement(matchResult);
				matchResult.replaceFoundNodesWith(replacementNode);
				doneReplacement = true;
			}
		} while (foundReplacement);

		doneReplacement = doneReplacement || replaceAllInChildrenRecursively(nodeChildren, nodeReplacement);
		return doneReplacement;
	}

	private boolean replaceAllInChildrenRecursively(NodeChildren nodeChildren, NodeReplacement<? extends Node> nodeReplacement) {
		boolean doneReplacement = false;
		for (Node child : nodeChildren) {
			doneReplacement = replaceAllInChildren(child.getChildren(), nodeReplacement);
		}
		return doneReplacement;
	}
}
	
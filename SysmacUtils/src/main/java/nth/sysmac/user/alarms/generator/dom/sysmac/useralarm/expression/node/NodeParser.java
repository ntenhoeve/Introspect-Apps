package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.NodeMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResults;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.MatchRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Token;

/**
 * A {@link NodeParser} creates a {@link ParseTree} and then combines
 * {@link Node} children by replacing them with other more detailed
 * {@link Node}s as is defined by {@link NodeParserRule}s.
 * 
 * @author nilsth
 *
 */
public class NodeParser {

	private final List<NodeParserRule> nodeParserRules;

	public NodeParser(List<NodeParserRule> nodeParserRules) {
		this.nodeParserRules = nodeParserRules;
	}

	public ParseTree parse(List<Token> tokens) {
		ParseTree parseTree = new ParseTree(tokens);

		replaceAll(parseTree);

		return parseTree;
	}

	private void replaceAll(ParseTree parseTree) {
		List<Node> nodeChildren = parseTree.getChildren();
		boolean doneReplacement = false;
		do {
			for (NodeParserRule nodeParserRule : nodeParserRules) {
				doneReplacement = replaceAll(nodeChildren, nodeParserRule);
			}
		} while (doneReplacement);
	}

	private boolean replaceAll(List<Node> nodes, NodeParserRule nodeParserRule) {
		boolean foundReplacement = false;
		boolean doneReplacement = false;
		do {
			foundReplacement = false;
			MatchRules matchRules = nodeParserRule.getMatchRules();
			NodeMatcher nodeMatcher=new NodeMatcher(matchRules);
			MatchResults matchResults = nodeMatcher.match(nodes);
			if (matchResults.hasResults()) {
				foundReplacement = true;
				nodeParserRule.removeOrReplace(matchResults);
				doneReplacement = true;
			}
		} while (foundReplacement);

		doneReplacement = doneReplacement || replaceAllInChildrenRecursively(nodes, nodeParserRule);
		return doneReplacement;
	}

	private boolean replaceAllInChildrenRecursively(List<Node> nodes, NodeParserRule nodeReplacement) {
		boolean doneReplacement = false;
		for (Node node : nodes) {
			doneReplacement = replaceAll(node.getChildren(), nodeReplacement);
		}
		return doneReplacement;
	}
}

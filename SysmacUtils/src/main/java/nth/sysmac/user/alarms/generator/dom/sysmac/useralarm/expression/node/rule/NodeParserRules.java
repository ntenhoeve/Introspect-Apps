package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeParserRule;

public enum NodeParserRules {

	BRACE(new BraceRule()),
	ACKNOWLEDGE(new AcknowledgeRule());

	private final NodeParserRule nodeParserRule;

	NodeParserRules(NodeParserRule nodeReplacement) {
		this.nodeParserRule = nodeReplacement;
	}

	private NodeParserRule get() {
		return nodeParserRule;
	}

	public static List<NodeParserRule> all() {
		return Arrays.asList(values()).stream().map(NodeParserRules::get).collect(Collectors.toUnmodifiableList());
	}
}

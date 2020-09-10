package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.rule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeRule;

public enum NodeRules {

	BRACE(new BraceRule()),
	ACKNOWLEDGE(new AcknowledgeRule());

	private final NodeRule<? extends Node> nodeRule;

	NodeRules(NodeRule<? extends Node> nodeReplacement) {
		this.nodeRule = nodeReplacement;
	}

	private NodeRule<? extends Node> get() {
		return nodeRule;
	}

	public static List<NodeRule<? extends Node>> all() {
		return Arrays.asList(values()).stream().map(NodeRules::get).collect(Collectors.toUnmodifiableList());
	}
}

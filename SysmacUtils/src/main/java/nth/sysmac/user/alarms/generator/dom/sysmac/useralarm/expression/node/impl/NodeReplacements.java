package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.NodeReplacement;

public enum NodeReplacements {

	BRACE(new BraceReplacement()),
	ACKNOWLEDGE(new AcknowledgeReplacement());

	private final NodeReplacement<? extends Node> nodeReplacement;

	NodeReplacements(NodeReplacement<? extends Node> nodeReplacement) {
		this.nodeReplacement = nodeReplacement;
	}

	private NodeReplacement<? extends Node> get() {
		return nodeReplacement;
	}

	public static List<NodeReplacement<? extends Node>> all() {
		return Arrays.asList(values()).stream().map(NodeReplacements::get).collect(Collectors.toUnmodifiableList());
	}
}

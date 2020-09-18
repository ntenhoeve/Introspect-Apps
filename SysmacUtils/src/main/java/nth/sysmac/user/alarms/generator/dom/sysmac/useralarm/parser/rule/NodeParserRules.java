package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.NodeParserRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge.AcknowledgeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details.DetailsRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityRule;

public enum NodeParserRules {

	BRACE(new BraceRule()),
	COMPONENT_CODE(new ComponentCodeRule()),
	ACKNOWLEDGE(new AcknowledgeRule()),
	PRIORITY(new PriorityRule()),
	//TODO COUNTER(new CounterRule());
	DETAILS(new DetailsRule());

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

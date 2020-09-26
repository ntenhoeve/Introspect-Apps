package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.NodeParserRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge.AcknowledgeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipMaxColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipMinMaxColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipSingleColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.even.SkipEvenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.uneven.SkipUnevenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details.DetailsRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityRule;

public enum NodeParserRules {

	BRACE(new BraceRule())
	,COMPONENT_CODE(new ComponentCodeRule())
	,ACKNOWLEDGE(new AcknowledgeRule())
	,PRIORITY(new PriorityRule())
	//TODO COUNTER(new CounterRule());
	,DETAILS(new DetailsRule())
	,BRACED_ATTRIBUTE_RULE(new BracedAttributeRule())
	,SKIP_EVEN_COLUMN_RULE(new SkipEvenColumnRule())
	,SKIP_UNEVEN_COLUMN_RULE(new SkipUnevenColumnRule())
	,SKIP_MIN_MAX_COLUMN_RULE(new SkipMinMaxColumnRule())
	,SKIP_MAX_COLUMN_RULE(new SkipMaxColumnRule())
	,SKIP_SINGLE_COLUMN_RULE(new SkipSingleColumnRule())
	;
	
	//TODO SKIP RULES
	//TODO COMPONENT_CODE_FIXED
	//TODO COMPONENT_CODE_HIDDEN
	//TODO COMPONENT_CODE_REFERENCE

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

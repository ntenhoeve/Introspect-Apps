package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.NodeParserRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge.AcknowledgeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.DerivedComponentCodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.HiddenComponentCodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.VisibleComponentCodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipMaxColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipMinMaxColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipSingleColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.even.SkipEvenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange.SkipMaxPageColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange.SkipMinMaxPageColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange.SkipSinglePageColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.uneven.SkipUnevenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details.DetailsRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.CounterRule;

public enum NodeParserRules {

	BRACE(new BraceRule())
	,COMPONENT_CODE(new ComponentCodeRule())
	,ACKNOWLEDGE(new AcknowledgeRule())
	,PRIORITY(new PriorityRule())
	,DETAILS(new DetailsRule())
	,BRACED_ATTRIBUTE_RULE(new BracedAttributeRule())
	,SKIP_EVEN_COLUMN_RULE(new SkipEvenColumnRule())
	,SKIP_UNEVEN_COLUMN_RULE(new SkipUnevenColumnRule())
	,SKIP_MIN_MAX_PAGE_COLUMN_RULE(new SkipMinMaxPageColumnRule())
	,SKIP_MAX_PAGE_COLUMN_RULE(new SkipMaxPageColumnRule())
	,SKIP_SINGLE_PAGE_COLUMN_RULE(new SkipSinglePageColumnRule())
	,SKIP_MIN_MAX_COLUMN_RULE(new SkipMinMaxColumnRule())
	,SKIP_MAX_COLUMN_RULE(new SkipMaxColumnRule())
	,SKIP_SINGLE_COLUMN_RULE(new SkipSingleColumnRule())
	,COUNTER(new CounterRule())//: {cnt} increases for each array {cnt skip=0}: Starts at 1, {cnt skip=-9, u}: skips 0-9 and uneven numbers,  {cnt skip=0,10-14, e} skips 0, 10-14 and enven numbers, {cnt array=1} increases when the last array is increassed and restarts when the last array in in creases, TODO example of combination e.g. scalder tank{cnt array=2 skip=0} pump {cnt array=2 skip=0}
	,HIDDEN_COMPONENT_CODE(new HiddenComponentCodeRule())
	,VISIBLE_COMPONENT_CODE(new VisibleComponentCodeRule())
	,DERIVED_COMPONENT_CODE(new DerivedComponentCodeRule())
	;
	
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

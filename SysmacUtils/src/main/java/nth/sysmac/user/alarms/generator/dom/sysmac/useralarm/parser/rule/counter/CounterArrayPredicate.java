package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter;

import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

public class CounterArrayPredicate extends BracedAttributePredicate {

	public CounterArrayPredicate() {
		super(BracedAttributeName.ARRAY, createChildMatchRules());
	}

	private static MatchRules createChildMatchRules() {
		MatchRules matchRules=new MatchRules().add(TokenNodePredicate.unsignedInteger());
		return matchRules;
	}

}

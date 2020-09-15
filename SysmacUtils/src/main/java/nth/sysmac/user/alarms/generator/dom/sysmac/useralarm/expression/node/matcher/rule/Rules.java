package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule;

import java.util.ArrayList;
import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;

public class Rules extends ArrayList<Rule> {

	private static final long serialVersionUID = -1539398207124002219L;
	private FirstMatchRule firstMatchRule;
	private LastMatchRule lastMatchRule;

	public Rules() {
		this.firstMatchRule = FirstMatchRule.CAN_BE_ANY_NODE;
		this.lastMatchRule = LastMatchRule.CAN_BE_ANY_NODE;
	}

	public FirstMatchRule getFirstMatchRule() {
		return firstMatchRule;
	}

	public LastMatchRule getLastMatchRule() {
		return lastMatchRule;
	}


	public Rules firstMatchMustBeFirstNode() {
		firstMatchRule = FirstMatchRule.MUST_BE_FIRST_NODE;
		return this;
	}

	public Rules firstMatchCanBeAnyNode() {
		firstMatchRule = FirstMatchRule.CAN_BE_ANY_NODE;
		return this;
	}

	public Rules lastMatchMustBeLastNode() {
		lastMatchRule = LastMatchRule.MUST_BE_LAST_NODE;
		return this;
	}

	public Rules lastMatchCanBeAnyNode() {
		lastMatchRule = LastMatchRule.CAN_BE_ANY_NODE;
		return this;
	}


	public Rules add(Predicate<Node> predicate) {
		Rule rule = new Rule(this, predicate, Repetition.oneTime());
		add(rule);
		return this;
	}

	public Rules add(Predicate<Node> predicate, Repetition repetition) {
		Rule rule = new Rule(this, predicate, repetition);
		add(rule);
		return this;
	}

	public Rules add(Rules rules) {
		for (Rule rule : rules) {
			rule.setParent(rules);
		}
		addAll(rules);
		return this;
	}


	
}

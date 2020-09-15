package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;

public class MatchRules extends ArrayList<MatchRule> {

	private static final long serialVersionUID = -1539398207124002219L;
	private FirstMatchRule firstMatchRule;
	private LastMatchRule lastMatchRule;
	private Optional<MatchRules> stopMatchingAfter;

	public MatchRules() {
		this.firstMatchRule = FirstMatchRule.CAN_BE_ANY_NODE;
		this.lastMatchRule = LastMatchRule.CAN_BE_ANY_NODE;
		this.stopMatchingAfter=Optional.empty();
	}

	public FirstMatchRule getFirstMatchRule() {
		return firstMatchRule;
	}

	public LastMatchRule getLastMatchRule() {
		return lastMatchRule;
	}


	public MatchRules firstMatchMustBeFirstNode() {
		firstMatchRule = FirstMatchRule.MUST_BE_FIRST_NODE;
		return this;
	}

	public MatchRules firstMatchCanBeAnyNode() {
		firstMatchRule = FirstMatchRule.CAN_BE_ANY_NODE;
		return this;
	}

	public MatchRules lastMatchMustBeLastNode() {
		lastMatchRule = LastMatchRule.MUST_BE_LAST_NODE;
		return this;
	}

	public MatchRules lastMatchCanBeAnyNode() {
		lastMatchRule = LastMatchRule.CAN_BE_ANY_NODE;
		return this;
	}


	public MatchRules add(Predicate<Node> predicate) {
		MatchRule matchRule = new MatchRule(this, predicate, Repetition.oneTime());
		add(matchRule);
		return this;
	}

	public MatchRules add(Predicate<Node> predicate, Repetition repetition) {
		MatchRule matchRule = new MatchRule(this, predicate, repetition);
		add(matchRule);
		return this;
	}

	public MatchRules add(MatchRules matchRules) {
		for (MatchRule matchRule : matchRules) {
			matchRule.setParent(matchRules);
		}
		addAll(matchRules);
		return this;
	}
	
	public MatchRules stopMatchingAfter(MatchRules stopMatchingAfterRules) {
		stopMatchingAfter=Optional.of(stopMatchingAfterRules);
		return this;
	}

	public Optional<MatchRules> getStopMatchingAfter() {
		return stopMatchingAfter;
	}


	
}

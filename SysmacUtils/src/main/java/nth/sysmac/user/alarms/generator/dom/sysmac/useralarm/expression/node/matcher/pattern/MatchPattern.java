package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.PatternGroup;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.Repetition;

public class MatchPattern {

	private FirstMatchRule firstMatchRule;
	private LastMatchRule lastMatchRule;
	private List<NodeRule> rules;
	private PatternGroup currentGroup;

	public MatchPattern() {
		this.firstMatchRule = FirstMatchRule.CAN_BE_ANY_NODE;
		this.lastMatchRule = LastMatchRule.CAN_BE_ANY_NODE;
		this.rules = new ArrayList<>();
		this.currentGroup = new PatternGroup("1", Necessity.REQUIRED);
	}

	public FirstMatchRule getFirstMatchRule() {
		return firstMatchRule;
	}

	public LastMatchRule getLastMatchRule() {
		return lastMatchRule;
	}

	public List<NodeRule> getRules() {
		return rules;
	}

	public MatchPattern firstMatchMustBeFirstNode() {
		firstMatchRule = FirstMatchRule.MUST_BE_FIRST_NODE;
		return this;
	}

	public MatchPattern firstMatchCanBeAnyNode() {
		firstMatchRule = FirstMatchRule.CAN_BE_ANY_NODE;
		return this;
	}

	public MatchPattern lastMatchMustBeLastNode() {
		lastMatchRule = LastMatchRule.MUST_BE_LAST_NODE;
		return this;
	}

	public MatchPattern lastMatchCanBeAnyNode() {
		lastMatchRule = LastMatchRule.CAN_BE_ANY_NODE;
		return this;
	}

	public MatchPattern newGroup() {
		String groupName = nextGroupNumber();
		currentGroup = new PatternGroup(groupName, Necessity.REQUIRED);
		return this;
	}

	public MatchPattern newGroup(String name) {
		if (name == null || name.isBlank()) {
			newGroup();
		} else {
			currentGroup = new PatternGroup(name, Necessity.REQUIRED);
		}
		return this;
	}

	public MatchPattern newOptionalGroup() {
		String groupName = nextGroupNumber();
		currentGroup = new PatternGroup(groupName, Necessity.OPTIONAL);
		return this;
	}

	public MatchPattern newOptionalGroup(String name) {
		if (name == null || name.isBlank()) {
			newGroup();
		} else {
			currentGroup = new PatternGroup(name, Necessity.OPTIONAL);
		}
		return this;
	}


	private String nextGroupNumber() {
		Set<PatternGroup> existingGroups = rules.stream().map(r -> r.getGroup()).collect(Collectors.toSet());
		int nextRuleNumber = existingGroups.size() + 1;
		return String.valueOf(nextRuleNumber);
	}

	public MatchPattern node(Predicate<Node> predicate) {
		NodeRule rule = new NodeRule(currentGroup, predicate, Repetition.oneTime());
		rules.add(rule);
		return this;
	}

	public MatchPattern node(Predicate<Node> predicate, Repetition repetition) {
		NodeRule rule = new NodeRule(currentGroup, predicate, repetition);
		rules.add(rule);
		return this;
	}

}

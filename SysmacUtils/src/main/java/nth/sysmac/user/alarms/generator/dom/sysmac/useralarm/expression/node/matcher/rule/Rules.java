package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;

public class Rules extends ArrayList<Rule> {

	private static final long serialVersionUID = -1539398207124002219L;
	private FirstMatchRule firstMatchRule;
	private LastMatchRule lastMatchRule;
	private Group currentGroup;

	public Rules() {
		this.firstMatchRule = FirstMatchRule.CAN_BE_ANY_NODE;
		this.lastMatchRule = LastMatchRule.CAN_BE_ANY_NODE;
		this.currentGroup = new Group("1", Necessity.REQUIRED);
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

	public Rules newGroup() {
		String groupName = nextGroupNumber();
		currentGroup = new Group(groupName, Necessity.REQUIRED);
		return this;
	}

	public Rules newGroup(String name) {
		if (name == null || name.isBlank()) {
			newGroup();
		} else {
			currentGroup = new Group(name, Necessity.REQUIRED);
		}
		return this;
	}

	public Rules newOptionalGroup() {
		String groupName = nextGroupNumber();
		currentGroup = new Group(groupName, Necessity.OPTIONAL);
		return this;
	}

	public Rules newOptionalGroup(String name) {
		if (name == null || name.isBlank()) {
			newGroup();
		} else {
			currentGroup = new Group(name, Necessity.OPTIONAL);
		}
		return this;
	}


	private String nextGroupNumber() {
		Set<Group> existingGroups = stream().map(r -> r.getGroup()).collect(Collectors.toSet());
		int nextRuleNumber = existingGroups.size() + 1;
		return String.valueOf(nextRuleNumber);
	}

	public Rules node(Predicate<Node> predicate) {
		Rule rule = new Rule(currentGroup, predicate, Repetition.oneTime());
		add(rule);
		return this;
	}

	public Rules node(Predicate<Node> predicate, Repetition repetition) {
		Rule rule = new Rule(currentGroup, predicate, repetition);
		add(rule);
		return this;
	}

}

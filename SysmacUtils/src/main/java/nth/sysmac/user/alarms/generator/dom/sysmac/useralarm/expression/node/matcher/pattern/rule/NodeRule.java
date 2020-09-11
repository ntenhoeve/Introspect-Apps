package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;

public class NodeRule {
	private final PatternGroup group;
	private final Predicate<Node> predicate;
	private final Repetition repetition;

	public NodeRule(PatternGroup group, Predicate<Node> predicate, Repetition repetition) {
		this.group = group;
		this.predicate = predicate;
		this.repetition = repetition;
	}

	public PatternGroup getGroup() {
		return group;
	}

	public Predicate<Node> getPredicate() {
		return predicate;
	}

	public Repetition getRepetition() {
		return repetition;
	}

	public boolean isValid(Node child) {
		boolean isValid = predicate.test(child);
		return isValid;
	}

}

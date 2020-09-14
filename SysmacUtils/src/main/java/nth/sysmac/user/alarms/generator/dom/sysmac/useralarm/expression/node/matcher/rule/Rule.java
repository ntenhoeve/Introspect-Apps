package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule;

import java.util.function.Predicate;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;

public class Rule {
	private final Group group;
	private final Predicate<Node> predicate;
	final Repetition repetition;

	public Rule(Group group, Predicate<Node> predicate, Repetition repetition) {
		this.group = group;
		this.predicate = predicate;
		this.repetition = repetition;
	}

	public Group getGroup() {
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

	@Override
	public String toString() {
		TitleBuilder title=new TitleBuilder();
		title.append(Rule.class.getSimpleName());
		title.append(" predicate=", predicate);
		title.append(", repetition=", repetition);
		return title.toString();
	}

	public boolean mustGoToNext(Results results) {
		if (repetition.max==Integer.MAX_VALUE) {
			return false;
		}
		int numberOfMatches = results.getNumberOfMatches(this);
		boolean mustGoToNextPattern=numberOfMatches>=repetition.getMax();
		return mustGoToNextPattern;
	}

	public boolean canGoToNext( Results results) {
		int numberOfMatches = results.getNumberOfMatches(this);
		boolean canGoToNextPattern=numberOfMatches>=repetition.getMin();
		return canGoToNextPattern;
	}

}

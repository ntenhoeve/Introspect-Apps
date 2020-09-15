package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule;

import java.util.Optional;
import java.util.function.Predicate;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;

public class Rule {
	private final Predicate<Node> predicate;
	private final Repetition repetition;
	private  Optional<Rules> parent;

	public Rule(Predicate<Node> predicate, Repetition repetition) {
		this.parent = Optional.empty();
		this.predicate = predicate;
		this.repetition = repetition;
	}
	
	public Rule(Rules parent, Predicate<Node> predicate, Repetition repetition) {
		this.parent =Optional.of(parent);
		this.predicate = predicate;
		this.repetition = repetition;
	}

	public Predicate<Node> getPredicate() {
		return predicate;
	}

	public Repetition getRepetition() {
		return repetition;
	}

	public Optional<Rules> getParent() {
		return parent;
	}
	
	public void setParent(Rules parent) {
		this.parent=Optional.of(parent);
	}

	public boolean isValid(Node child) {
		boolean isValid = predicate.test(child);
		return isValid;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(Rule.class.getSimpleName());
		title.append(" predicate=", predicate);
		title.append(", repetition=", repetition);
		return title.toString();
	}

	public boolean mustGoToNext(Results results) {
		if (repetition.max == Integer.MAX_VALUE) {
			return false;
		}
		int numberOfMatches = results.getNumberOfMatches(this);
		boolean mustGoToNextPattern = numberOfMatches >= repetition.getMax();
		return mustGoToNextPattern;
	}

	public boolean canGoToNext(Results results) {
		int numberOfMatches = results.getNumberOfMatches(this);
		boolean canGoToNextPattern = numberOfMatches >= repetition.getMin();
		return canGoToNextPattern;
	}

	

}

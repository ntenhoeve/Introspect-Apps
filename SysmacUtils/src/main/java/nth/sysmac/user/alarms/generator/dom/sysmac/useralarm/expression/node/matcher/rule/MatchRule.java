package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule;

import java.util.Optional;
import java.util.function.Predicate;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResults;

public class MatchRule {
	private final Predicate<Node> predicate;
	private final Repetition repetition;
	private  Optional<MatchRules> parent;

	public MatchRule(Predicate<Node> predicate, Repetition repetition) {
		this.parent = Optional.empty();
		this.predicate = predicate;
		this.repetition = repetition;
	}
	
	public MatchRule(MatchRules parent, Predicate<Node> predicate, Repetition repetition) {
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

	public Optional<MatchRules> getParent() {
		return parent;
	}
	
	public void setParent(MatchRules parent) {
		this.parent=Optional.of(parent);
	}

	public boolean isValid(Node child) {
		boolean isValid = predicate.test(child);
		return isValid;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(MatchRule.class.getSimpleName());
		title.append(" predicate=", predicate);
		title.append(", repetition=", repetition);
		return title.toString();
	}

	public boolean mustGoToNext(MatchResults matchResults) {
		if (repetition.max == Integer.MAX_VALUE) {
			return false;
		}
		int numberOfMatches = matchResults.getNumberOfMatches(this);
		boolean mustGoToNextPattern = numberOfMatches >= repetition.getMax();
		return mustGoToNextPattern;
	}

	public boolean canGoToNext(MatchResults matchResults) {
		int numberOfMatches = matchResults.getNumberOfMatches(this);
		boolean canGoToNextPattern = numberOfMatches >= repetition.getMin();
		return canGoToNextPattern;
	}

	

}

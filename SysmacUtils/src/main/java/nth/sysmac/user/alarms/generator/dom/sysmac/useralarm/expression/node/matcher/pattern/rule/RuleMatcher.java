package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;

public class RuleMatcher {

	private final List<RepetitionCounter> repetitionCounters;
	private int ruleIndex;
	private final List<NodeRule> rules;

	public RuleMatcher(MatchPattern pattern) {
		repetitionCounters = new ArrayList<>();
		for (NodeRule rule : pattern.getRules()) {
			Repetition repetition = rule.getRepetition();
			RepetitionCounter repetitionCounter = new RepetitionCounter(repetition);
			repetitionCounters.add(repetitionCounter);
		}
		ruleIndex = 0;
		rules = pattern.getRules();
	}

	public Optional<NodeRule> match(Node node, Optional<Node> nextNode) {
		boolean matchesCurrentRule = matchesCurrentRule(node);
		if (matchesCurrentRule) {
			return processFound(node, nextNode);
		} else {
			return processNotFound(node, nextNode);
		}
	}

	private Optional<NodeRule> processNotFound(Node node, Optional<Node> nextNode) {
		if(hasNoMoreRules()) {
			return Optional.absent();
		}
		boolean canGoToNextRule = getCurrentRepetitionCounter().canGoToNextRule();
		if (canGoToNextRule) {
			ruleIndex++;
			
			// try and match the following rules (recursively) 
			return match(node, nextNode);
		} else {
			// Definitely not found :-(
			return Optional.absent();
		}
	}

	private Optional<NodeRule> processFound(Node node, Optional<Node> nextNode) {
		NodeRule matchingRule = rules.get(ruleIndex);

		getCurrentRepetitionCounter().next();
		
		if (canOrMustGoToNextRule(node, nextNode)) {
			ruleIndex++;
		}

		return Optional.of(matchingRule);
	}

	private RepetitionCounter getCurrentRepetitionCounter() {
		return repetitionCounters.get(ruleIndex);
	}

	private boolean canOrMustGoToNextRule(Node node, Optional<Node> nextNode) {
		if (hasNoMoreRules()) {
			return false;
		}
		boolean nextNodeMatchesNextRule = nextNodeMatchesNextRule(nextNode);
		RepetitionCounter currentRepetitionCounter = getCurrentRepetitionCounter();
		boolean canGoToNextRule = currentRepetitionCounter.canGoToNextRule();
		boolean mustGoToNextRule = currentRepetitionCounter.mustGoToNextRule();
		boolean goToNextRule = mustGoToNextRule || (canGoToNextRule && nextNodeMatchesNextRule);
		return goToNextRule;
	}

	private boolean matchesCurrentRule(Node node) {
		if (hasNoMoreRules()) {
			return false;
		}
		NodeRule currentRule = rules.get(ruleIndex);
		boolean matchesCurrentRule = currentRule.getPredicate().test(node);
		return matchesCurrentRule;
	}

	private boolean hasNoMoreRules() {
		return ruleIndex >= rules.size();
	}

	private boolean nextNodeMatchesNextRule(Optional<Node> nextNode) {
		if (hasNoNextRule() || !nextNode.isPresent()) {
			return false;
		}
		NodeRule nextRule = rules.get(ruleIndex + 1);
		boolean matchesNextRule = nextRule.getPredicate().test(nextNode.get());
		return matchesNextRule;
	}

	private boolean hasNoNextRule() {
		return ruleIndex + 1 >= rules.size();
	}

	public boolean allRulesOk() {
		return hasNoMoreRules();
	}

	public boolean lastRuleOk() {
		return lastRepetitionCounter().canGoToNextRule() && hasNoNextRule();
	}

	private RepetitionCounter lastRepetitionCounter() {
		return repetitionCounters.get(repetitionCounters.size() - 1);
	}
}

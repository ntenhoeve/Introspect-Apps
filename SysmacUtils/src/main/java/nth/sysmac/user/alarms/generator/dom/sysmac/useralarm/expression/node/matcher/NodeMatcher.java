package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher;

import java.util.List;

import com.google.common.base.Optional;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Results;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter.AllResultsFilter;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.FirstMatchRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.LastMatchRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;

public class NodeMatcher {

	private static final AllResultsFilter ALL_RESULT_FILTER = new AllResultsFilter();
	private final Rules rules;

	public NodeMatcher(Rules rules) {
		this.rules = rules;
	}

	public Results match(List<Node> nodes) {
		if (firstMatchCanBeAnyNode()) {
			Results results = matchFromAnyChild(nodes);
			return results;
		} else {
			Results results = matchFromFirstChild(nodes);
			return results;
		}
	}

	private Results matchFromAnyChild(List<Node> nodes) {
		for (int nodeStartIndex = 0; nodeStartIndex < nodes.size(); nodeStartIndex++) {
			Results results = match(nodes, nodeStartIndex);
			if (results.hasResults()) {
				return results;
			}
		}
		return Results.NOT_FOUND;
	}

	private Results matchFromFirstChild(List<Node> nodes) {
		int startIndex = 0;
		Results results = match(nodes, startIndex);
		return results;
	}

	private Results match(List<Node> nodes, int nodeStartIndex) {
		Results results = new Results(nodes);
		int ruleIndex = 0;

		for (int nodeIndex = nodeStartIndex; nodeIndex < nodes.size(); nodeIndex++) {
			Node node = nodes.get(nodeIndex);
			Optional<Rule> foundRule = findMatchingRule(node, ruleIndex, results);
			if (foundRule.isPresent()) {
				Rule rule = foundRule.get();
				results.add(rule, nodeIndex);
				ruleIndex = rules.indexOf(rule);

//				if (done(ruleIndex, results)) {
//					if (firstAndLastRulesAreOk(results)) {
//						return results;
//					} else {
//						return Results.NOT_FOUND;
//					}
//				}
			} else {
				// found first mismatch but this is ok if we where done anyway
				return getValidatedResults(results);
			}
		}
		// done matching. did we match all?
		return getValidatedResults(results);
	}

	private Results getValidatedResults(Results results) {
		if (done(results)) {
			if (firstAndLastRulesAreOk(results)) {
				return results;
			} else {
				return Results.NOT_FOUND;
			}
		} else {
			return Results.NOT_FOUND;
		}
	}

	private boolean firstAndLastRulesAreOk(Results results) {
		int firstFoundNodeIndex = results.getFirst(ALL_RESULT_FILTER);
		boolean firstFoundNodeIsFirst = firstFoundNodeIndex == 0;
		boolean firstRuleOk = firstMatchCanBeAnyNode() || firstFoundNodeIsFirst;
		
		int lastFoundNodeIndex = results.getLastResult().getLast();
		int lastNodeIndex = results.getNodes().size()-1;
		boolean lastFoundNodeisLast=lastFoundNodeIndex == lastNodeIndex;
		boolean lastRuleOk = lastMatchCanBeAnyNode() ||lastFoundNodeisLast  ;

		return firstRuleOk && lastRuleOk;
	}

	private boolean lastMatchCanBeAnyNode() {
		return rules.getLastMatchRule() == LastMatchRule.CAN_BE_ANY_NODE;
	}

	private boolean firstMatchCanBeAnyNode() {
		return rules.getFirstMatchRule() == FirstMatchRule.CAN_BE_ANY_NODE;
	}

	private boolean done(Results results) {
		if (!results.hasResults()) {
			return false;
		}
		int ruleIndex = rules.indexOf(results.getLastResult().getRule());
		if (passedAllRules(ruleIndex)) {
			return true;
		}
		if (remainingRulesCanGoToNext(ruleIndex + 1, results)) {
			return true;
		}
		return false;
	}

	private boolean remainingRulesCanGoToNext(int ruleIndex, Results results) {
		if (ruleIndex >= rules.size()) {
			// is last rule so remaining rules can go to next
			return true;
		}
		Rule rule = rules.get(ruleIndex);
		boolean canGoToNext = rule.canGoToNext(results);
		if (canGoToNext) {
			// recursive call for the remaining node rules;
			boolean remainingRulesCanGoToNext = remainingRulesCanGoToNext(ruleIndex + 1, results);
			return remainingRulesCanGoToNext;
		}
		return false;
	}

	private boolean passedAllRules(int ruleIndex) {
		boolean passedAllRules = ruleIndex >= rules.size();
		return passedAllRules;
	}

	private Optional<Rule> findMatchingRule(Node node, int startRuleIndex, Results results) {
		int ruleCurrentIndex = findRuleThatMustMatch(startRuleIndex, results);

		Optional<Rule> found = findMatchingRule(node, startRuleIndex, ruleCurrentIndex, results);

		return found;
	}

	private Optional<Rule> findMatchingRule(Node node, int ruleStartIndex, int ruleCurrentIndex, Results results) {
		Rule rule = rules.get(ruleCurrentIndex);
		if (rule.getPredicate().test(node)) {
			return Optional.of(rule);
		} else {
			if (ruleCurrentIndex - 1 < ruleStartIndex) {
				// nothing found if we are back where we started
				return Optional.absent();
			} else {
				// try to find a matching rule in the previous rules (recursively)
				return findMatchingRule(node, ruleStartIndex, ruleCurrentIndex - 1, results);
			}
		}
	}

	private int findRuleThatMustMatch(int ruleIndex, Results results) {
		if (ruleIndex >= rules.size()) {
			return rules.size() - 1;
		}
		Rule rule = rules.get(ruleIndex);
		if (rule.mustGoToNext(results) || rule.canGoToNext(results)) {
			// recursive call
			int findNextRuleIndexToMatch = findRuleThatMustMatch(ruleIndex + 1, results);
			return findNextRuleIndexToMatch;
		} else {
			return ruleIndex;
		}
	}

}

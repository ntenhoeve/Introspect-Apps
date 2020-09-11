package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher;

import java.util.List;

import com.google.common.base.Optional;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.FirstMatchRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.LastMatchRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.NodeRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule.RuleMatcher;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.MatchResult;

public class NodeMatcher {

	private final MatchPattern pattern;

	public NodeMatcher(MatchPattern pattern) {
		this.pattern = pattern;
	}

	public MatchResult match(List<Node> children) {
		if (firstMatchMustBeFirstNode()) {
			MatchResult matchResult = matchFromFirstChild(children);
			return matchResult;
		} else {
			MatchResult matchResult = matchFromAnyChild(children);
			return matchResult;
		}
	}

	private MatchResult matchFromAnyChild(List<Node> children) {
		for (int startIndex = 0; startIndex < children.size(); startIndex++) {
			MatchResult matchResult = match(children, startIndex);
			if (matchResult.hasResults()) {
				return matchResult;
			}
		}
		return MatchResult.NOT_FOUND;
	}

	private MatchResult matchFromFirstChild(List<Node> children) {
		int startIndex = 0;
		MatchResult matchResult = match(children, startIndex);
		return matchResult;
	}

	private MatchResult match(List<Node> children, int startIndex) {
		RuleMatcher ruleMatcher = new RuleMatcher(pattern);
		MatchResult matchResult = new MatchResult(children);

		for (int index = startIndex; index < children.size(); index++) {
			Node child = children.get(index);
			Optional<Node> nextChild = getNextChild(children, index);
			Optional<NodeRule> matchingRule = ruleMatcher.match(child, nextChild);
			if (matchingRule.isPresent()) {
				matchResult.addResult(matchingRule.get(), index);
			} else {
				return MatchResult.NOT_FOUND;
			}
			if (ruleMatcher.allRulesOk() && !lastMatchMustBeLastNode()) {
				return matchResult;
			}
		}

		if (ruleMatcher.lastRuleOk()) {
			return matchResult;
		} else {
			return MatchResult.NOT_FOUND;
		}
	}

	private Optional<Node> getNextChild(List<Node> children, int index) {
		if (index +1 < children.size()) {
			return Optional.of(children.get(index + 1));
		} else {
			return Optional.absent();
		}
	}

	private boolean firstMatchMustBeFirstNode() {
		return pattern.getFirstMatchRule() == FirstMatchRule.MUST_BE_FIRST_NODE;
	}

	private boolean lastMatchMustBeLastNode() {
		return pattern.getLastMatchRule() == LastMatchRule.MUST_BE_LAST_NODE;
	}

}

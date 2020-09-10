package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;

/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public enum NodeDistance {
	ZERO_INBETWEEN(new ZeroInbetween()),
	ZERO_OR_MORE_WHITESPACE_INBETWEEN(new ZeroOrMoreWhitespaceInbetween()),
	ZERO_OR_MORE_INBETWEEN(new ZeroOrMoreNodesInbetween());

	private final Predicate<MatchResult> predicate;

	NodeDistance(Predicate<MatchResult> predicate) {
		this.predicate = predicate;
	}

	public boolean isCorrectFor(MatchResult matchResult) {
		return predicate.test(matchResult);
	}
}

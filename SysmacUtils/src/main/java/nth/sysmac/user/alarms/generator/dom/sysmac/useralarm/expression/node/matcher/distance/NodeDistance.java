package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResultOld;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;

/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public enum NodeDistance {
	ZERO_INBETWEEN(new ZeroInbetween()),
	ZERO_OR_MORE_WHITESPACE_INBETWEEN(new ZeroOrMoreWhitespaceInbetween()),
	ZERO_OR_MORE_INBETWEEN(new ZeroOrMoreNodesInbetween());

	private final Predicate<MatchResultOld> predicate;

	NodeDistance(Predicate<MatchResultOld> predicate) {
		this.predicate = predicate;
	}

	public boolean isCorrectFor(MatchResultOld matchResultOld) {
		return predicate.test(matchResultOld);
	}
}

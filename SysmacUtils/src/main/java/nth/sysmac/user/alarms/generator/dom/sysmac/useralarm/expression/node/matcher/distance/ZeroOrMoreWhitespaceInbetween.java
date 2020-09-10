package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.WhiteSpace;
/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public class ZeroOrMoreWhitespaceInbetween implements Predicate<MatchResult> {

	@Override
	public boolean test(MatchResult matchResult) {
		if (!matchResult.found()) {
			return false;
		}

		Integer previousfoundIndex=null;
		for (Integer foundIndex : matchResult.getFoundIndexes()) {
			if (previousfoundIndex==null) {
				previousfoundIndex=foundIndex;
			} else if (foundIndex>previousfoundIndex) {
				boolean ok = inbetweenAreZeroOrMoreWhitespaces(matchResult, previousfoundIndex, foundIndex);
				if (!ok) {
					return false;
				}
				previousfoundIndex=foundIndex;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean inbetweenAreZeroOrMoreWhitespaces(MatchResult matchResult, Integer startIndex,
			Integer stopIndex) {
		if (stopIndex - startIndex < 2) {
			return true;
		}
		for (int index = startIndex + 1; index < stopIndex - 1; index++) {
			Node child = matchResult.getChildren().get(index);
			if (otherThanWhiteSpace(child)) {
				return false;
			}
		}
		return true;
	}

	private boolean otherThanWhiteSpace(Node child) {
		if (child instanceof TokenNode) {
			TokenNode tokenNode = (TokenNode) child;
			return !(tokenNode.getRule() instanceof WhiteSpace);
		} else {
			return false;
		}
	}
}

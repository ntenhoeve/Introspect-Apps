package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.TokenNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResultOld;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.WhiteSpace;
/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public class ZeroOrMoreWhitespaceInbetween implements Predicate<MatchResultOld> {

	@Override
	public boolean test(MatchResultOld matchResultOld) {
		if (!matchResultOld.found()) {
			return false;
		}

		Integer previousfoundIndex=null;
		for (Integer foundIndex : matchResultOld.getFoundIndexes()) {
			if (previousfoundIndex==null) {
				previousfoundIndex=foundIndex;
			} else if (foundIndex>previousfoundIndex) {
				boolean ok = inbetweenAreZeroOrMoreWhitespaces(matchResultOld, previousfoundIndex, foundIndex);
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

	private boolean inbetweenAreZeroOrMoreWhitespaces(MatchResultOld matchResultOld, Integer startIndex,
			Integer stopIndex) {
		if (stopIndex - startIndex < 2) {
			return true;
		}
		for (int index = startIndex + 1; index < stopIndex - 1; index++) {
			Node child = matchResultOld.getChildren().get(index);
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

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;

/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public class ZeroOrMoreNodesInbetween implements Predicate<MatchResult> {

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
				previousfoundIndex=foundIndex;
			} else {
				return false;
			}
		}
		return true;
	}
}

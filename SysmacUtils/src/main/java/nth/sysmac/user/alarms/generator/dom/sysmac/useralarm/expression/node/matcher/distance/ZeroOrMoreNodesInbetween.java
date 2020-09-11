package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResultOld;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;

/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public class ZeroOrMoreNodesInbetween implements Predicate<MatchResultOld> {

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
				previousfoundIndex=foundIndex;
			} else {
				return false;
			}
		}
		return true;
	}
}

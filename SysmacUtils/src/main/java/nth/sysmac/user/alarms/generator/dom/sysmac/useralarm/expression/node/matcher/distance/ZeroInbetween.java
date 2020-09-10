package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchPattern;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResult;

/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public class ZeroInbetween implements Predicate<MatchResult> {

	@Override
	public boolean test(MatchResult matchResult) {
		if (!matchResult.found()) {
			return false;
		}
		Integer previousIndex=null;
		for (Integer foundIndex : matchResult.getFoundIndexes()) {
			if (previousIndex==null) {
				previousIndex=foundIndex;
			} else if (foundIndex==previousIndex+1) {
				previousIndex=foundIndex;
			} else {
				return false;
			}
		}
		return true;
	}

}

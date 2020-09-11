package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.distance;

import java.util.function.Predicate;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.MatchResultOld;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.MatchPattern;

/**
 * @deprecated use {@link MatchPattern}
 * @author nilsth
 *
 */
public class ZeroInbetween implements Predicate<MatchResultOld> {

	@Override
	public boolean test(MatchResultOld matchResultOld) {
		if (!matchResultOld.found()) {
			return false;
		}
		Integer previousIndex=null;
		for (Integer foundIndex : matchResultOld.getFoundIndexes()) {
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

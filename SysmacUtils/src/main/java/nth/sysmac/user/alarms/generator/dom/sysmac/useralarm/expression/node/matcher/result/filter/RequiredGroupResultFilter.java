package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.Necessity;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.NoResultsFoundException;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.ResultGroup;

public class RequiredGroupResultFilter extends ResultFilter {

	@Override
	public int getFirst(List<ResultGroup> resultGroups) {
		throwErrorWhenNoResultsAreFound(resultGroups);
		ResultGroup firstResultGroup = findFirstResultGroup(resultGroups);
		int first=firstResultGroup.getFirst();
		return first;
	}

	private ResultGroup findFirstResultGroup(List<ResultGroup> resultGroups) {
		for (ResultGroup resultGroup : resultGroups) {
			if ( resultGroup.getNesessity()==Necessity.REQUIRED) {
				return resultGroup;
			}
		}
		throw new RuntimeException("Could not find a required group.");
	}

	@Override
	public int getLast(List<ResultGroup> resultGroups) {
		throwErrorWhenNoResultsAreFound(resultGroups);
		ResultGroup lastResultGroup = findLastResultGroup(resultGroups);
		int last=lastResultGroup.getLast();
		return last;
	}

	private ResultGroup findLastResultGroup(List<ResultGroup> resultGroups) {
		boolean foundFirst=false;
		for (ResultGroup resultGroup : resultGroups) {
			if ( resultGroup.getNesessity()==Necessity.REQUIRED) {
				foundFirst=true;
			} else if (foundFirst) {
				return resultGroup;
			}
		}
		return resultGroups.get(resultGroups.size()-1);
	}

}

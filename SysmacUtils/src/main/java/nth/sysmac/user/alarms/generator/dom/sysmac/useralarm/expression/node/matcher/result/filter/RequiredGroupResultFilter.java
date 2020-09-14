package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Result;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Necessity;

public class RequiredGroupResultFilter extends ResultFilter {

	@Override
	public int getFirst(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		Result firstResultGroup = findFirstResultGroup(results);
		int first=firstResultGroup.getFirst();
		return first;
	}

	private Result findFirstResultGroup(List<Result> results) {
		for (Result result : results) {
			if ( result.getNesessity()==Necessity.REQUIRED) {
				return result;
			}
		}
		throw new RuntimeException("Could not find a required group.");
	}

	@Override
	public int getLast(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		Result lastResultGroup = findLastResultGroup(results);
		int last=lastResultGroup.getLast();
		return last;
	}

	private Result findLastResultGroup(List<Result> results) {
		boolean foundFirst=false;
		for (Result result : results) {
			if ( result.getNesessity()==Necessity.REQUIRED) {
				foundFirst=true;
			} else if (foundFirst) {
				return result;
			}
		}
		return results.get(results.size()-1);
	}

}

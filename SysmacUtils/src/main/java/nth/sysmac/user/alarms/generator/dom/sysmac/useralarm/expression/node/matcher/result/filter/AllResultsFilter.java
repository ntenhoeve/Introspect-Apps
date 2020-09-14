package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Result;

public class AllResultsFilter extends ResultFilter {

	
	@Override
	public int getFirst(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		Result firstResult = results.get(0);
		int first=firstResult.getFirst();
		return first;
	}

	@Override
	public int getLast(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		Result lastResult = results.get(results.size()-1);
		int last=lastResult.getLast();
		return last;
	}


}

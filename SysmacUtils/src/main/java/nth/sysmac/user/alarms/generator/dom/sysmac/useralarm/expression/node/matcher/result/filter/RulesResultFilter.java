package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.List;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Result;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rules;

public class RulesResultFilter extends ResultFilter {

	private final Rules rulesToFind;

	public RulesResultFilter(Rules rulesToFind) {
		this.rulesToFind = rulesToFind;
	}
	
	@Override
	public int getFirstNodeIndex(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		List<Result> filteredResults = findResults(results);
		Result firstResult = filteredResults.get(0);
		int firstNodeIndex=firstResult.getFirstNodeIndex();
		return firstNodeIndex;
	}

	@Override
	public int getLastNodeIndex(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		List<Result> filteredResults = findResults(results);
		Result lastResult=filteredResults.get(filteredResults.size()-1);
		int lastNodeIndex=lastResult.getLastNodeIndex();
		return lastNodeIndex;
	}

	private List<Result> findResults(List<Result> results) {
		return results.stream().filter(result-> result.getRule().getParent().isPresent() && result.getRule().getParent().get()==rulesToFind).collect(Collectors.toList());
	}

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Result;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rule;

public class RuleResultFilter extends ResultFilter {

	private final Rule ruleToFind;

	public RuleResultFilter(Rule ruleToFind) {
		this.ruleToFind = ruleToFind;
	}
	
	@Override
	public int getFirstNodeIndex(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		Result result = findResultGroup(results);
		int first=result.getFirstNodeIndex();
		return first;
	}

	@Override
	public int getLastNodeIndex(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		Result result = findResultGroup(results);
		int last=result.getLastNodeIndex();
		return last;
	}

	private Result findResultGroup(List<Result> results) {
		return results.stream().filter(result-> result.getRule()==ruleToFind).findAny().orElseThrow(() -> new RuntimeException("Could not find rule: "+ruleToFind));
	}

}

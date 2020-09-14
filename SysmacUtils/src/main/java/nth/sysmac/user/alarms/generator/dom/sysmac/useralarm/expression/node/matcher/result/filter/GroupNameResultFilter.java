package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.Result;

public class GroupNameResultFilter extends ResultFilter {

	private final String groupNameToFind;

	public GroupNameResultFilter(String groupNameToFind) {
		this.groupNameToFind = groupNameToFind;
	}
	
	@Override
	public int getFirst(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		Result result = findResultGroup(results);
		int first=result.getFirst();
		return first;
	}

	@Override
	public int getLast(List<Result> results) {
		throwErrorWhenNoResultsAreFound(results);
		Result result = findResultGroup(results);
		int last=result.getLast();
		return last;
	}

	private Result findResultGroup(List<Result> results) {
		return results.stream().filter(resultGroup-> resultGroup.getGroupName().equals(groupNameToFind)).findAny().orElseThrow(() -> new RuntimeException("Could not find group name: "+groupNameToFind));
	}

}

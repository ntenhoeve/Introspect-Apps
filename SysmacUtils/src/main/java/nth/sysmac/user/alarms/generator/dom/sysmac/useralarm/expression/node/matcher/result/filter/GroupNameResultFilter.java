package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.filter;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result.ResultGroup;

public class GroupNameResultFilter extends ResultFilter {

	private final String groupNameToFind;

	public GroupNameResultFilter(String groupNameToFind) {
		this.groupNameToFind = groupNameToFind;
	}
	
	@Override
	public int getFirst(List<ResultGroup> resultGroups) {
		throwErrorWhenNoResultsAreFound(resultGroups);
		ResultGroup resultGroup = findResultGroup(resultGroups);
		int first=resultGroup.getFirst();
		return first;
	}

	@Override
	public int getLast(List<ResultGroup> resultGroups) {
		throwErrorWhenNoResultsAreFound(resultGroups);
		ResultGroup resultGroup = findResultGroup(resultGroups);
		int last=resultGroup.getLast();
		return last;
	}

	private ResultGroup findResultGroup(List<ResultGroup> resultGroups) {
		return resultGroups.stream().filter(resultGroup-> resultGroup.getName().equals(groupNameToFind)).findAny().orElseThrow(() -> new RuntimeException("Could not find group name: "+groupNameToFind));
	}

}

package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

public class GroupNames extends ArrayList<String> {

	private static final long serialVersionUID = -9132859674221644142L;

	public GroupNames(DataType root) {
		List<DataType> children = root.getChildren();
		List<String> childNames = getChildNamesContainingStructures(children);
		for (String childName : childNames) {
			groupNameStartsWithChildName(childName).ifPresent(groupName -> {
				remove(groupName);
				add(childName);
			});
			if (!childNameStartsWithGroupName(childName)) {
				add(childName);
			}
		}

	}

	private List<String> getChildNamesContainingStructures(List<DataType> children) {
		List<String> childNames = children.stream().filter(c -> c.getBaseType().getReference().isPresent()).map(c -> c.getName())
				.collect(Collectors.toList());
		return childNames;
	}

	private Optional<String> groupNameStartsWithChildName(String childName) {
		for (String groupName : this) {
			if (groupName.startsWith(childName)) {
				return Optional.of(groupName);
			}
		}
		return Optional.empty();

	}

	private boolean childNameStartsWithGroupName(String childName) {
		for (String groupName : this) {
			if (childName.startsWith(groupName)) {
				return true;
			}
		}
		return false;
	}

}

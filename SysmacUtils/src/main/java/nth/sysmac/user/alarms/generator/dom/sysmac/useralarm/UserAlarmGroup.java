package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypePath;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

public class UserAlarmGroup {

	private static final String SEPARATOR = ", ";
	private static final String NEW_LINE = "\n";
	private static final String INDENT = "  ";
	private final String groupName;
	private final List<UserAlarm> userAlarms;

	public UserAlarmGroup(String groupName, Variable eventVariable, List<DataTypePath> dataTypePaths) {
		this.groupName = groupName;
		List<DataTypePath> dataTypePathsForGroup = filter(dataTypePaths);
		userAlarms = createUserAlarms(eventVariable, dataTypePathsForGroup);
	}

	public UserAlarmGroup(String groupName, List<UserAlarm> userAlarms) {
		this.groupName = groupName;
		this.userAlarms = userAlarms;
	}

	private List<DataTypePath> filter(List<DataTypePath> dataTypePaths) {
		List<DataTypePath> dataTypePathsForGroup = dataTypePaths.stream()
				.filter(d -> d.get(1).getName().startsWith(groupName)).collect(Collectors.toList());
		return dataTypePathsForGroup;
	}

	private List<UserAlarm> createUserAlarms(Variable eventVariable, List<DataTypePath> dataTypePaths) {
		List<UserAlarm> userAlarms = new ArrayList<>();
		List<UserAlarmParseException> exceptions = new ArrayList<>();

		for (DataTypePath dataTypePath : dataTypePaths) {
			try {
				List<UserAlarm> newUserAlarms = createUserAlarms(eventVariable, dataTypePath);
				userAlarms.addAll(newUserAlarms);
			} catch (Throwable e) {
				e.printStackTrace();
				UserAlarmParseException userAlarmParseException = new UserAlarmParseException(dataTypePath, e);
				exceptions.add(userAlarmParseException);
			}
		}
		if (!exceptions.isEmpty()) {
			throw new UserAlarmParseException(exceptions);
		}
		return userAlarms;
	}

	private List<UserAlarm> createUserAlarms(Variable eventVariable, DataTypePath dataTypePath) {
		dataTypePath.verifyOnlyOneArray();

		List<UserAlarm> userAlarms = new ArrayList<>();
		DataTypePathConverter dataTypePathConverter = new DataTypePathConverter(eventVariable, dataTypePath);
		boolean done = false;
		while (!done) {
			UserAlarm userAlarm = new UserAlarm(groupName, dataTypePathConverter);
			userAlarms.add(userAlarm);
			if (dataTypePathConverter.canGoToNext()) {
				dataTypePathConverter.goToNext();
			} else {
				done = true;
			}
		}
		return userAlarms;
	}

	public String getGroupName() {
		return groupName;
	}

	public List<UserAlarm> getUserAlarms() {
		return userAlarms;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAlarmGroup other = (UserAlarmGroup) obj;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (userAlarms == null) {
			if (other.userAlarms != null)
				return false;
		} else if (!userAlarms.equals(other.userAlarms))
			return false;
		return true;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(UserAlarmGroup.class.getSimpleName());
		title.append(SEPARATOR + "groupName=", groupName);
		title.contact(NEW_LINE);

		for (UserAlarm userAlarm : userAlarms) {
			String[] childStrings = userAlarm.toString().split("\\n");
			for (String childString : childStrings) {
				title.append(INDENT, childString + NEW_LINE);
			}
		}

		return title.toString();
	}

}

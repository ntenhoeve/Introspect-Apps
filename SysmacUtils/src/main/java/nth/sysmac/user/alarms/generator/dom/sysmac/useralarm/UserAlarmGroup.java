package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseTypeArray;
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
		userAlarms=new ArrayList<>();
		addUserAlarms(eventVariable, dataTypePathsForGroup);
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

	private void addUserAlarms(Variable eventVariable, List<DataTypePath> dataTypePaths) {
		List<UserAlarmParseException> exceptions = new ArrayList<>();

		List<UserAlarm> userAlarmsWithArray=new ArrayList<>();
		
		Optional<BaseTypeArray> foundArray, previousFoundArray;
		previousFoundArray=Optional.empty();
		
		for (DataTypePath dataTypePath : dataTypePaths) {
			try {
				boolean isLast=dataTypePaths.indexOf(dataTypePath)==dataTypePaths.size()-1;
				UserAlarm userAlarm = new UserAlarm(groupName, eventVariable, dataTypePath);
				userAlarms.add(userAlarm);
				foundArray=userAlarm.findArray();
				if (foundArray.isPresent()) {
					userAlarmsWithArray.add(userAlarm);
				}
				if (isLast || isEndOfArray(foundArray, previousFoundArray) ) {
					ArrayList<UserAlarmParseException> userAlarmParseExceptions = addUserAlarmsForOtherArrays(eventVariable, userAlarmsWithArray);
					exceptions.addAll(userAlarmParseExceptions);

					userAlarmsWithArray=new ArrayList<>();
				}

				previousFoundArray=foundArray;

			} catch (Throwable e) {
				UserAlarmParseException userAlarmParseException = new UserAlarmParseException(dataTypePath, e);
				exceptions.add(userAlarmParseException);
			}
		}
		
		if (!exceptions.isEmpty()) {
			throw new UserAlarmParseException(exceptions);
		}
	}

	private boolean isEndOfArray(Optional<BaseTypeArray> foundArray, Optional<BaseTypeArray> previousFoundArray) {
		if (previousFoundArray.isPresent() && foundArray.isEmpty()) {
			return true;
		}
		if (previousFoundArray.isPresent() && foundArray.isPresent()) {
			boolean startedNewArray = previousFoundArray.get()!=foundArray.get();
			return startedNewArray;
		}
		return false;
	}

	private ArrayList<UserAlarmParseException> addUserAlarmsForOtherArrays(Variable eventVariable,
			List<UserAlarm> userAlarmsWithArray) {
		ArrayList<UserAlarmParseException> exceptions=new ArrayList<>();
		
		if (!userAlarmsWithArray.isEmpty()) {
			UserAlarm firstUserAlarmWithArray=userAlarmsWithArray.get(0);
			DataTypePathConverter dataTypePathConverter = firstUserAlarmWithArray.getDataTypePathConverter();
			
			while (dataTypePathConverter.canGoToNext()) {
				dataTypePathConverter.goToNext();
				
				for (UserAlarm userAlarmWithArray : userAlarmsWithArray) {
					try {
						DataTypePathConverter dataTypePathConverter2 = userAlarmWithArray.getDataTypePathConverter();
						UserAlarm userAlarm = new UserAlarm(groupName, eventVariable, dataTypePathConverter2);
						userAlarms.add(userAlarm);
					} catch (Throwable e) {
						DataTypePath dataTypePath = userAlarmWithArray.getDataTypePathConverter().getDataTypePath();
						UserAlarmParseException userAlarmParseException = new UserAlarmParseException(dataTypePath, e);
						exceptions.add(userAlarmParseException);
					}
				}

			}
		}
		
		return exceptions;
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

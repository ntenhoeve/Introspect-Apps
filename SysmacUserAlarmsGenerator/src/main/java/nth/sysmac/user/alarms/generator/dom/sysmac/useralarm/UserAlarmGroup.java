package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.TextExpression;
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
		int min = dataTypePath.getMin();
		int max = dataTypePath.getMax();
		for (int i = min; i <= max; i++) {
			String varExpr = dataTypePath.getVariableExpression(eventVariable, i);
			String textExprString = dataTypePath.getTextExpression();
			 TextExpression textExpr = new TextExpression(textExprString, i);
			UserAlarm userAlarm = new UserAlarm(groupName, varExpr, textExpr);
			userAlarms.add(userAlarm);
			textExpr.nextComponentCodes();
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

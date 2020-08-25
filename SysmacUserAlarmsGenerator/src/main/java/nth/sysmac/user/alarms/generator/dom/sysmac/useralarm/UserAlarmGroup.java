package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypePath;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

public class UserAlarmGroup {

	private static final String SEPARATOR = ", ";
	private static final String NEW_LINE = "\n";
	private static final String INDENT = "  ";
	private final String groupName;
	private final List<UserAlarm> userAlarms;

	public UserAlarmGroup(String groupName, Variable eventVariable, DataType eventDataType) {
		this.groupName = groupName;

		List<DataTypePath> dataTypePaths = eventDataType
				.findPaths(d -> d.isLeaf() && d.getBaseType().getOmronType().isPresent()
						&& OmronBaseType.BOOL.equals(d.getBaseType().getOmronType().get()));
		userAlarms = createUserAlarms(eventVariable, dataTypePaths);
	}

	private List<UserAlarm> createUserAlarms(Variable eventVariable, List<DataTypePath> dataTypePaths) {
		List<UserAlarm> userAlarms=new ArrayList<>();
		for (DataTypePath dataTypePath : dataTypePaths) {
			String varExpr = dataTypePath.getVariableExpression(eventVariable);
			String textExpr = dataTypePath.getTextExpression();
			
			UserAlarm userAlarm=new UserAlarm(groupName, varExpr, textExpr);
			userAlarms.add(userAlarm);
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

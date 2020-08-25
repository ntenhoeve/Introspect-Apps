package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypePath;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

public class UserAlarmGroup {

	private final String groupName;
	private final List<UserAlarm> userAlarms;

	public UserAlarmGroup(String groupName, Variable eventVariable, DataType eventDataType) {
		this.groupName = groupName;

		List<DataTypePath> dataPaths = eventDataType
				.findPaths(d -> d.isLeaf() && d.getBaseType().getOmronType().isPresent()
						&& OmronBaseType.BOOL.equals(d.getBaseType().getOmronType().get()));
		userAlarms = createUserAlarms(eventVariable, dataPaths);
	}

	private List<UserAlarm> createUserAlarms(Variable eventVariable, List<DataTypePath> dataPaths) {
		for (DataTypePath dataTypePath : dataPaths) {
			String varExpr = dataTypePath.getVariableExpression(eventVariable);
			String textExpr = dataTypePath.getTextExpression();
			
			UserAlarm userAlarm=new UserAlarm(groupName, varExpr, textExpr);
		}
		// TODO Auto-generated method stub
		return null;
	}

	public String getGroupName() {
		return groupName;
	}

	public List<UserAlarm> getUserAlarms() {
		return userAlarms;
	}

}

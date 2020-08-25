package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.List;
import java.util.Optional;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypeService;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.VariableService;

public class UserAlarmGenerationService {

	private static final String S_EVENT = "sEvent";
	private final DataTypeService dataTypeService;
	private final VariableService variableService;

	public UserAlarmGenerationService() {
		dataTypeService = new DataTypeService();
		variableService = new VariableService();
	}

	public void generateUserAlarms(SysmacProject sysmacProject) {
		List<DataType> dataTypes = dataTypeService.getDataTypes(sysmacProject);

		Variable eventVariable = variableService.getGlobalHmiEventVariable(sysmacProject);
		System.out.println(eventVariable);

		DataType eventDataType = findRootEventDataType(dataTypes);
		System.out.println(eventDataType);

		GroupNames groupNames = new GroupNames(eventDataType);
		System.out.println(groupNames);

		for (String groupName : groupNames) {
			UserAlarmGroup u = new UserAlarmGroup(groupName, eventVariable, eventDataType);
		}
	}

	}

	private List<UserAlarm> createUserAlarms(DataType eventDataType, String groupName) {
		for (DataType child : eventDataType.getChildren()) {
			if (child.getName().startsWith(groupName)) {
				UserAlarm userAlarm = createUserAlarm(child, groupName);
			}
		}
		return null;
	}

	private UserAlarm createUserAlarm(DataType child, String groupName) {
		// TODO Auto-generated method stub
		return null;
	}

	private DataType findRootEventDataType(List<DataType> dataTypes) {
		for (DataType dataType : dataTypes) {
			Optional<DataType> result = dataType.find(d -> S_EVENT.equals(d.getName()) && d.getNameSpace() == null
					&& "STRUCT".equals(d.getBaseTypeExpression()));
			if (result.isPresent()) {
				return result.get();
			}
		}
		throw new RuntimeException("Could not find a Structure named: " + S_EVENT + " in the root of the DataTypes");
	}
}

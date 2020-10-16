package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypePath;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypeService;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.VariableService;

public class UserAlarmGroupFactory {

	private static final String S_EVENT = "sEvent";
	private final DataTypeService dataTypeService;
	private final VariableService variableService;

	public UserAlarmGroupFactory() {
		dataTypeService = new DataTypeService();
		variableService = new VariableService();
	}

	public List<UserAlarmGroup> create(SysmacProject sysmacProject) {
		List<DataType> dataTypes = dataTypeService.getDataTypes(sysmacProject);
		Variable eventVariable = variableService.getGlobalHmiEventVariable(sysmacProject);
		DataType eventDataType = findRootEventDataType(dataTypes);
		
		
		return create(eventVariable, eventDataType);
	}

	public List<UserAlarmGroup> create(Variable eventVariable, DataType eventDataType) {
		GroupNames groupNames = new GroupNames(eventDataType);
		
		List<DataTypePath> dataTypePaths = eventDataType
				.findPaths(d -> d.isLeaf() && d.getBaseType().getOmronType().isPresent()
						&& OmronBaseType.BOOL.equals(d.getBaseType().getOmronType().get())); 

		List<UserAlarmGroup> userAlarmGroups = create(eventVariable, groupNames, dataTypePaths);
		return userAlarmGroups;
	}

	private List<UserAlarmGroup> create(Variable eventVariable, GroupNames groupNames, List<DataTypePath> dataTypePaths) {
		List<UserAlarmGroup> userAlarmGroups =new ArrayList<>();
		for (String groupName : groupNames) {
			UserAlarmGroup userAlarmGroup = new UserAlarmGroup(groupName, eventVariable, dataTypePaths);
			userAlarmGroups.add(userAlarmGroup);
		}
		return userAlarmGroups;
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

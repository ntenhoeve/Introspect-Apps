package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroup;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroupFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;
/**
*
* <h3>User alarm group example</h3>
* Note: All members within EventGlobal, that refer to another structure become an alarm group. The UserAlarm messages are all data comments chained together
* <table cellspacing="1" cellpadding="4" bgcolor="#000000">
* <tr bgcolor="#ffffff">
* <th colspan=6>Variable: EventGlobal of type: sEvent<br>Data Type's:</th>
* </tr>
* <tr bgcolor="#ffffff">
* <th align="left">Namespace</th>
* <th align="left">Name</th>
* <th align="left">Base Type</th>
* <th align="left" colspan=3 >Comment</th>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">sEvent</td>
* <td align="left">STRUCT</td>
* <td align="left" colspan=3 ></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">&nbsp;&nbsp;Venter</td>
* <td align="left">Venter\sEvent</td>
* <td align="left" colspan=3 >Venter</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">&nbsp;&nbsp;Maestro</td>
* <td align="left">Maestro\sEvent</td>
* <td align="left" colspan=3 >Maestro</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Venter</td>
* <td align="left">sEvent</td>
* <td align="left">STRUCT</td>
* <td align="left" colspan=3 ></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Venter</td>
* <td align="left">&nbsp;&nbsp;waterLevelLow</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >water level too low</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">sEvent</td>
* <td align="left">STRUCT</td>
* <td align="left" colspan=3 ></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">&nbsp;&nbsp;airPressureLow</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >air pressure too low</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">&nbsp;&nbsp;waterLevelLow</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >water level too low</td>
* </tr>
* <tr bgcolor="#ffffff">
* <th colspan=6>Results in UserAlarm(s):</th>
* </tr>
* <tr bgcolor="#ffffff">
* <th align="left">Group</th>
* <th align="left">Expression</th>
* <th align="left">Acknowledge</th>
* <th align="left">Message</th>
* <th align="left">Priority</th>
* <th align="left">Details</th>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Venter</td>
* <td align="left">EventGlobal.Venter.waterLevelLow</td>
* <td align="left">false</td>
* <td align="left">Venter water level too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">EventGlobal.Maestro.airPressureLow</td>
* <td align="left">false</td>
* <td align="left">Maestro air pressure too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">EventGlobal.Maestro.waterLevelLow</td>
* <td align="left">false</td>
* <td align="left">Maestro water level too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/
public class UserAlarmGroupExampleTest extends ExampleTest {

	private static final String AIR_PRESSURE_LOW_VAR = "airPressureLow";
	private static final String AIR_PRESSURE_LOW_MSG = "air pressure too low";
	private static final String WATER_LEVEL_LOW_VAR = "waterLevelLow";
	private static final String WATER_LEVEL_LOW_MSG = "water level too low";
	private static final String S_EVENT = "sEvent";
	private static final String MAESTRO = "Maestro";
	private static final String VENTER = "Venter";
	


	public UserAlarmGroupExampleTest() {
		setNote("Note: All members within EventGlobal, that refer to another structure become an alarm group. The UserAlarm messages are all data comments chained together");
		addGivenDataType(ExampleTest.NO_NAMESPACE, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE,VENTER, VENTER+"\\"+S_EVENT, VENTER);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE,MAESTRO, MAESTRO+"\\"+S_EVENT, MAESTRO);
		
		addGivenDataType(VENTER, S_EVENT,BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(VENTER, WATER_LEVEL_LOW_VAR, OmronBaseType.BOOL.toString(),
				WATER_LEVEL_LOW_MSG);

		addGivenDataType(MAESTRO, S_EVENT,BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(MAESTRO, AIR_PRESSURE_LOW_VAR, OmronBaseType.BOOL.toString(),
				AIR_PRESSURE_LOW_MSG);
		addGivenDataTypeChild(MAESTRO, WATER_LEVEL_LOW_VAR, OmronBaseType.BOOL.toString(),
				WATER_LEVEL_LOW_MSG);

		addExpectedUserAlarm(VENTER, variableName+"."+VENTER+"."+WATER_LEVEL_LOW_VAR, VENTER+" "+WATER_LEVEL_LOW_MSG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(MAESTRO, variableName+"."+MAESTRO+"."+AIR_PRESSURE_LOW_VAR, MAESTRO+" "+AIR_PRESSURE_LOW_MSG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(MAESTRO, variableName+"."+MAESTRO+"."+WATER_LEVEL_LOW_VAR, MAESTRO+" "+WATER_LEVEL_LOW_MSG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
	}

	/**
	 * Used to create the JavaDoc for this class
	 * @param args
	 */
	public static void main(String[] args) {
		UserAlarmGroupExampleTest v = new UserAlarmGroupExampleTest();
		System.out.println(v.toJavaDoc());
	}
	

	@Test
	void testExample() {
		ExampleTest exampleTest=new UserAlarmGroupExampleTest();
		Variable eventVariable=new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType=exampleTest.getEventDataType();
		UserAlarmGroupFactory factory=new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}
}

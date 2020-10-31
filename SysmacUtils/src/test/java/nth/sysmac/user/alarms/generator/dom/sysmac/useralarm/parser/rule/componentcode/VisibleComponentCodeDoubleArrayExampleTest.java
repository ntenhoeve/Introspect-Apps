package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroup;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroupFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.ExampleTest;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

/**
*
* <h3>Visible component code double array example</h3>
* Note that you can use multidimensional arrays.
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
* <td align="left">&nbsp;&nbsp;TorqueSensor</td>
* <td align="left">ARRAY[0..2,1..2] OF BOOL</td>
* <td align="left" colspan=3 >110s4 over torque detected</td>
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
* <td align="left"></td>
* <td align="left">EventGlobal.TorqueSensor(0,1)</td>
* <td align="left">false</td>
* <td align="left">110S4 Over torque detected</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.TorqueSensor(0,2)</td>
* <td align="left">false</td>
* <td align="left">110S5 Over torque detected</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.TorqueSensor(1,1)</td>
* <td align="left">false</td>
* <td align="left">110S6 Over torque detected</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.TorqueSensor(1,2)</td>
* <td align="left">false</td>
* <td align="left">110S7 Over torque detected</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.TorqueSensor(2,1)</td>
* <td align="left">false</td>
* <td align="left">110S8 Over torque detected</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.TorqueSensor(2,2)</td>
* <td align="left">false</td>
* <td align="left">111S1 Over torque detected</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/



public class VisibleComponentCodeDoubleArrayExampleTest extends ExampleTest {

	private static final String TORQUE_SENSOR_MSG = "Over torque detected";
	private static final String TORQUE_SENSOR_VAR = "TorqueSensor";
	private static final String COMPONENT_CODE_LETTER = "S";
	private static final String TORQUE_SENSOR_CODE_MSG = createMessage(110, 4);

	public VisibleComponentCodeDoubleArrayExampleTest() {
		setNote("Note that you can use multidimensional arrays.");
		addGivenDataType(ExampleTest.NO_NAMESPACE, "sEvent", BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, TORQUE_SENSOR_VAR,
				"ARRAY[0..2,1..2] OF " + OmronBaseType.BOOL.toString(), TORQUE_SENSOR_CODE_MSG.toLowerCase());
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(0,1), createMessage(110, 4),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(0,2), createMessage(110, 5),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(1,1), createMessage(110, 6),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(1,2), createMessage(110, 7),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(2,1), createMessage(110, 8),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(2,2), createMessage(111, 1),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);

	}

	private String createVariable(int high, int low) {
		return variableName + "." + TORQUE_SENSOR_VAR+"("+high+","+low+")";
	}

	private static String createMessage(int componentCodePage, int componentCodeColumn) {
		return componentCodePage + COMPONENT_CODE_LETTER + componentCodeColumn + " " + TORQUE_SENSOR_MSG;
	}

	/**
	 * Used to create the JavaDoc for this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		VisibleComponentCodeDoubleArrayExampleTest v = new VisibleComponentCodeDoubleArrayExampleTest();
		System.out.println(v.toJavaDoc());
	}

	@Test
	void testExample() {
		ExampleTest exampleTest = new VisibleComponentCodeDoubleArrayExampleTest();
		Variable eventVariable = new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType = exampleTest.getEventDataType();
		UserAlarmGroupFactory factory = new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}
}

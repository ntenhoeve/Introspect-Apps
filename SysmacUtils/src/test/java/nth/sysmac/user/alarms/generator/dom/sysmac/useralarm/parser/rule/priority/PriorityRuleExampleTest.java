package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroup;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroupFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.ExampleTest;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

/**
*
* <h3>Priority rule example</h3>
* Note: the user needs to acknowledge the alarm when the DataType comment has the {ack} text
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
* <td align="left">&nbsp;&nbsp;temperatrueLow</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >Temperature too low</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">&nbsp;&nbsp;airPressureLow</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >{ack}Air pressure too low</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">&nbsp;&nbsp;levelLow</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >Level too low{ack}</td>
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
* <td align="left">EventGlobal.temperatrueLow</td>
* <td align="left">false</td>
* <td align="left">Temperature too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.airPressureLow</td>
* <td align="left">true</td>
* <td align="left">Air pressure too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.levelLow</td>
* <td align="left">true</td>
* <td align="left">Level too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/

public class PriorityRuleExampleTest extends ExampleTest {

	private static final String TEMPERATURE_LOW_MSG = "Temperature too low";
	private static final String TEMPERATURE_LOW_VAR = "temperatrueLow";
	private static final String AIR_PRESSURE_LOW_MSG = "Air pressure too low";
	private static final String AIR_PRESSURE_LOW_VAR = "airPressureLow";
	private static final String LEVEL_LOW_MSG = "Level too low";
	private static final String LEVEL_LOW_VAR = "levelLow";
	private static final String ACK = "{ack}";

	public PriorityRuleExampleTest() {
		setNote("Note: the user needs to acknowledge the alarm when the DataType comment has the {ack} text");
		addGivenDataType(ExampleTest.NO_NAMESPACE, "sEvent", BaseType.STRUCT, ExampleTest.NO_COMMENT);
		
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, TEMPERATURE_LOW_VAR, OmronBaseType.BOOL.toString(),
				TEMPERATURE_LOW_MSG);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, AIR_PRESSURE_LOW_VAR, OmronBaseType.BOOL.toString(),
				ACK+AIR_PRESSURE_LOW_MSG);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, LEVEL_LOW_VAR, OmronBaseType.BOOL.toString(),
				LEVEL_LOW_MSG+ACK);
		
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, variableName + "." + TEMPERATURE_LOW_VAR,
				TEMPERATURE_LOW_MSG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, variableName + "." + AIR_PRESSURE_LOW_VAR,
				AIR_PRESSURE_LOW_MSG, Priority.MEDIUM, ExampleTest.ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, variableName + "." + LEVEL_LOW_VAR,
				LEVEL_LOW_MSG, Priority.MEDIUM, ExampleTest.ACKNOWLEDGE, ExampleTest.NO_DETAILS);
	}

	/**
	 * Used to create the JavaDoc for this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityRuleExampleTest v = new PriorityRuleExampleTest();
		System.out.println(v.toJavaDoc());
	}

	@Test
	void testExample() {
		ExampleTest exampleTest = new PriorityRuleExampleTest();
		Variable eventVariable = new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType = exampleTest.getEventDataType();
		UserAlarmGroupFactory factory = new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}
}

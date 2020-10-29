package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroup;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroupFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.ExampleTest;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.UserAlarmExampleTest;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

/**
*
* <h3>Visble component code simple example</h3>
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
* <td align="left">&nbsp;&nbsp;airPressureLow</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >110s3 system air pressure too low</td>
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
* <td align="left">EventGlobal.airPressureLow</td>
* <td align="left">false</td>
* <td align="left">110S3 System air pressure too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/


public class VisibleComponentCodeSimpleExampleTest extends ExampleTest {

	private static final String AIR_PRESSURE_LOW_MSG = "System air pressure too low";
	private static final String AIR_PRESSURE_LOW_VAR = "airPressureLow";
	private static final String AIR_PRESSURE_LOW_CODE = "110S3";
	private static final String AIR_PRESSURE_LOW_CODE_MSG = AIR_PRESSURE_LOW_CODE + " " + AIR_PRESSURE_LOW_MSG;

	public VisibleComponentCodeSimpleExampleTest() {
		addGivenDataType(ExampleTest.NO_NAMESPACE, "sEvent", BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, AIR_PRESSURE_LOW_VAR, OmronBaseType.BOOL.toString(),
				AIR_PRESSURE_LOW_CODE_MSG.toLowerCase());
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, variableName + "." + AIR_PRESSURE_LOW_VAR,
				AIR_PRESSURE_LOW_CODE_MSG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
	}

	/**
	 * Used to create the JavaDoc for this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		VisibleComponentCodeSimpleExampleTest v = new VisibleComponentCodeSimpleExampleTest();
		System.out.println(v.toJavaDoc());
	}

	@Test
	void testExample() {
		ExampleTest exampleTest = new UserAlarmExampleTest();
		Variable eventVariable = new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType = exampleTest.getEventDataType();
		UserAlarmGroupFactory factory = new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}
}

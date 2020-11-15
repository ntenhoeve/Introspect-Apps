package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter;

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
* <h3>Counter rule array example</h3>
* {cnt} is increased when array 1 or array 2 increases
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
* <td align="left">&nbsp;&nbsp;Overload</td>
* <td align="left">ARRAY[0..2,1..2] OF BOOL</td>
* <td align="left" colspan=3 >30q4 scalder {cnt array=2} pump {cnt array=1} overload protection</td>
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
* <td align="left">EventGlobal.Overload(0,1)</td>
* <td align="left">false</td>
* <td align="left">30Q4 Scalder 0 pump 0 overload protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.Overload(0,2)</td>
* <td align="left">false</td>
* <td align="left">30Q5 Scalder 0 pump 1 overload protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.Overload(1,1)</td>
* <td align="left">false</td>
* <td align="left">30Q6 Scalder 1 pump 0 overload protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.Overload(1,2)</td>
* <td align="left">false</td>
* <td align="left">30Q7 Scalder 1 pump 1 overload protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.Overload(2,1)</td>
* <td align="left">false</td>
* <td align="left">30Q8 Scalder 2 pump 0 overload protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">EventGlobal.Overload(2,2)</td>
* <td align="left">false</td>
* <td align="left">31Q1 Scalder 2 pump 1 overload protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/

class CounterRuleArrayExampleTest extends ExampleTest {

	private static final String OVERLOAD_PROTECTION_MSG = "Scalder {cnt array=2} pump {cnt array=1} overload protection";
	private static final String OVERLOAD_PROTECTION_VAR = "Overload";
	private static final String COMPONENT_CODE_LETTER = "Q";
	private static final String OVERLOAD_PROTECTION_CODE_MSG = createMessage(30, 4,"{cnt array=2}","{cnt array=1}");

	public CounterRuleArrayExampleTest() {
		setNote("{cnt} is increased when array 1 or array 2 increases");
		addGivenDataType(ExampleTest.NO_NAMESPACE, "sEvent", BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, OVERLOAD_PROTECTION_VAR,
				"ARRAY[0..2,1..2] OF " + OmronBaseType.BOOL.toString(), OVERLOAD_PROTECTION_CODE_MSG.toLowerCase());
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(0,1), createMessage(30, 4,"0","0"),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(0,2), createMessage(30, 5,"0","1"),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(1,1), createMessage(30, 6,"1","0"),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(1,2), createMessage(30, 7,"1","1"),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(2,1), createMessage(30, 8,"2","0"),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(ExampleTest.NO_NAMESPACE, createVariable(2,2), createMessage(31, 1,"2","1"),
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);

	}

	private String createVariable(int high, int low) {
		return variableName + "." + OVERLOAD_PROTECTION_VAR+"("+high+","+low+")";
	}

	private static String createMessage(int componentCodePage, int componentCodeColumn, String array2, String array1) {
		return componentCodePage + COMPONENT_CODE_LETTER + componentCodeColumn + " " + OVERLOAD_PROTECTION_MSG.replace("{cnt array=2}", array2).replace("{cnt array=1}", array1);
	}

	/**
	 * Used to create the JavaDoc for this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CounterRuleArrayExampleTest v = new CounterRuleArrayExampleTest();
		System.out.println(v.toJavaDoc());
	}

	@Test
	void testExample() {
		ExampleTest exampleTest = new CounterRuleArrayExampleTest();
		Variable eventVariable = new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType = exampleTest.getEventDataType();
		UserAlarmGroupFactory factory = new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}

}

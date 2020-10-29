package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
* <h3>Visble component code group example</h3>
* Note that the component code moves to the front! <br>Also Note that data type comments normaly start with a lowercase character unles it starts with an abbreviation. User alarm texts are generated, starting with a uppercase character.
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
* <td align="left" colspan=3 >venter</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left"></td>
* <td align="left">&nbsp;&nbsp;Maestro</td>
* <td align="left">Maestro\sEvent</td>
* <td align="left" colspan=3 >maestro</td>
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
* <td align="left" colspan=3 >110S1 water level too low</td>
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
* <td align="left" colspan=3 >120S3 system air pressure too low</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">&nbsp;&nbsp;waterLevelLow</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >120S4 water level too low</td>
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
* <td align="left">110S1 Venter water level too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">EventGlobal.Maestro.airPressureLow</td>
* <td align="left">false</td>
* <td align="left">120S3 Maestro system air pressure too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">EventGlobal.Maestro.waterLevelLow</td>
* <td align="left">false</td>
* <td align="left">120S4 Maestro water level too low</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/

public class VisibleComponentCodeGroupExampleTest extends ExampleTest {

	private static final String _110S1 = "110S1";
	private static final String _120S3 = "120S3";
	private static final String _120S4 = "120S4";
	private static final String AIR_PRESSURE_LOW_MSG = "system air pressure too low";
	private static final String AIR_PRESSURE_LOW_VAR = "airPressureLow";
	private static final String WATER_LEVEL_LOW_VAR = "waterLevelLow";
	private static final String WATER_LEVEL_LOW_MSG = "water level too low";
	private static final String S_EVENT = "sEvent";
	private static final String MAESTRO = "Maestro";
	private static final String VENTER = "Venter";
	

	public VisibleComponentCodeGroupExampleTest() {
		setNote("Note that the component code moves to the front! <br>Also Note that data type comments normaly start with a lowercase character unles it starts with an abbreviation. User alarm texts are generated, starting with a uppercase character.");
		addGivenDataType(ExampleTest.NO_NAMESPACE, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, VENTER, VENTER + "\\" + S_EVENT, VENTER.toLowerCase());
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, MAESTRO, MAESTRO + "\\" + S_EVENT, MAESTRO.toLowerCase());

		addGivenDataType(VENTER, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(VENTER, WATER_LEVEL_LOW_VAR, OmronBaseType.BOOL.toString(),
				_110S1 + " " + WATER_LEVEL_LOW_MSG);

		addGivenDataType(MAESTRO, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(MAESTRO, AIR_PRESSURE_LOW_VAR, OmronBaseType.BOOL.toString(),
				_120S3 + " " + AIR_PRESSURE_LOW_MSG);
		addGivenDataTypeChild(MAESTRO, WATER_LEVEL_LOW_VAR, OmronBaseType.BOOL.toString(),
				_120S4 + " " + WATER_LEVEL_LOW_MSG);

		addExpectedUserAlarm(VENTER, variableName + "." + VENTER + "." + WATER_LEVEL_LOW_VAR,
				_110S1 + " " +  VENTER + " " + WATER_LEVEL_LOW_MSG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE,
				ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(MAESTRO, variableName + "." + MAESTRO + "." + AIR_PRESSURE_LOW_VAR,
				_120S3 + " " + MAESTRO + " " + AIR_PRESSURE_LOW_MSG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE,
				ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(MAESTRO, variableName + "." + MAESTRO + "." + WATER_LEVEL_LOW_VAR,
				_120S4 + " " + MAESTRO + " " + WATER_LEVEL_LOW_MSG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE,
				ExampleTest.NO_DETAILS);
	}

	
	/**
	 * Used to create the JavaDoc for this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		VisibleComponentCodeGroupExampleTest v = new VisibleComponentCodeGroupExampleTest();
		System.out.println(v.toJavaDoc());
	}

	@Test
	void testExample() {
		ExampleTest exampleTest = new VisibleComponentCodeGroupExampleTest();
		Variable eventVariable = new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType = exampleTest.getEventDataType();
		UserAlarmGroupFactory factory = new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}
}

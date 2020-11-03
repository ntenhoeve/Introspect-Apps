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
* <h3>Hidden component code with derived is visible example</h3>
* Note that hidden component codes are not displayed but derived component codes are derived hidden component codes.
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
* <td align="left">&nbsp;&nbsp;Maestro</td>
* <td align="left">Maestro\sEvent</td>
* <td align="left" colspan=3 >maestro</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">sEvent</td>
* <td align="left">STRUCT</td>
* <td align="left" colspan=3 ></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">&nbsp;&nbsp;Brush</td>
* <td align="left">Cm\MtrCtrl\sEventDol</td>
* <td align="left" colspan=3 >{30M2} {110S7} brush</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Cm</td>
* <td align="left">MtrCtrl</td>
* <td align="left">STRUCT</td>
* <td align="left" colspan=3 ></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Cm</td>
* <td align="left">&nbsp;&nbsp;sEvent</td>
* <td align="left">Cm\MtrCtrl\sEventDol</td>
* <td align="left" colspan=3 ></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Cm\MtrCtrl</td>
* <td align="left">sEventDol</td>
* <td align="left">STRUCT</td>
* <td align="left" colspan=3 ></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Cm\MtrCtrl</td>
* <td align="left">&nbsp;&nbsp;MtrSw</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >{s} motor disconnect switch</td>
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
* <td align="left">Maestro</td>
* <td align="left">EventGlobal.Maestro.Brush.MtrSw</td>
* <td align="left">false</td>
* <td align="left">110S7 Maestro brush motor disconnect switch</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/

public class HiddenComponentCodeWithDerivedIsVisibleExampleTest extends ExampleTest {

	private static final String MOTOR_SWITCH = "MtrSw";
	private static final String MOTOR_COMPONENT_CODE = "30M2";
	private static final String MOTOR_COMPONENT_CODE_HIDDEN = "{"+MOTOR_COMPONENT_CODE+"}";
	private static final String MOTOR_DISCONNECT_SWITCH_COMPONENT_CODE = "110S7";
	private static final String MOTOR_DISCONNECT_SWITCH_COMPONENT_CODE_HIDDEN = "{"+MOTOR_DISCONNECT_SWITCH_COMPONENT_CODE +"}";

	private static final String MOTOR_DISCONNECT_SWITCH = "{s} motor disconnect switch";
	private static final String MOTOR_DISCONNECT_SWITCH_WITHOUT_TAG = MOTOR_DISCONNECT_SWITCH.replace("{s} ", "");

	private static final String S_EVENT_DOL = "sEventDol";
	private static final String MTR_CTRL = "MtrCtrl";
	private static final String CM = "Cm";
	private static final String BRUSH_MSG = "brush";
	private static final String BRUSH_VAR = "Brush";
	private static final String S_EVENT = "sEvent";
	private static final String MAESTRO = "Maestro";
	
	public HiddenComponentCodeWithDerivedIsVisibleExampleTest() {
		setNote("Note that hidden component codes are not displayed but derived component codes are derived hidden component codes.");
		
		
		addGivenDataType(ExampleTest.NO_NAMESPACE, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, MAESTRO, MAESTRO + "\\" + S_EVENT, MAESTRO.toLowerCase());


		addGivenDataType(MAESTRO, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(MAESTRO, BRUSH_VAR, CM + "\\" + MTR_CTRL + "\\" + S_EVENT_DOL,
				MOTOR_COMPONENT_CODE_HIDDEN + " " +MOTOR_DISCONNECT_SWITCH_COMPONENT_CODE_HIDDEN + " " + BRUSH_MSG);
		
		addGivenDataType(CM, MTR_CTRL, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(CM, S_EVENT, CM + "\\" + MTR_CTRL + "\\" + S_EVENT_DOL,"");

		addGivenDataType(CM + "\\" + MTR_CTRL, S_EVENT_DOL, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, MOTOR_SWITCH, OmronBaseType.BOOL.toString(),MOTOR_DISCONNECT_SWITCH);


		addExpectedUserAlarm(MAESTRO, variableName + "." + MAESTRO + "." + BRUSH_VAR+"."+MOTOR_SWITCH,
				MOTOR_DISCONNECT_SWITCH_COMPONENT_CODE+ " " + MAESTRO + " " + BRUSH_MSG+ " "+MOTOR_DISCONNECT_SWITCH_WITHOUT_TAG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE,
				ExampleTest.NO_DETAILS);
	}
	
	/**
	 * Used to create the JavaDoc for this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HiddenComponentCodeWithDerivedIsVisibleExampleTest v = new HiddenComponentCodeWithDerivedIsVisibleExampleTest();
		System.out.println(v.toJavaDoc());
	}

	@Test
	void testExample() {
		ExampleTest exampleTest = new HiddenComponentCodeWithDerivedIsVisibleExampleTest();
		Variable eventVariable = new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType = exampleTest.getEventDataType();
		UserAlarmGroupFactory factory = new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}
}

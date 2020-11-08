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
* <h3>Hidden component code array skip example</h3>
* Note that every hidden component code can have its own skip rules.
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
* <td align="left">&nbsp;&nbsp;Plucker</td>
* <td align="left">Plucker\sEvent</td>
* <td align="left" colspan=3 >plucker</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">sEvent</td>
* <td align="left">STRUCT</td>
* <td align="left" colspan=3 ></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">&nbsp;&nbsp;Belt</td>
* <td align="left">ARRAY[0..2] OF Cm\MtrCtrl\sEventDol</td>
* <td align="left" colspan=3 >{30M2 skip=u,30.4-31.6} {110K5 skip=e} belt</td>
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
* <td align="left">Cm\MtrCtrl</td>
* <td align="left">&nbsp;&nbsp;MtrProt</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >motor {q} protection</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Cm\MtrCtrl</td>
* <td align="left">&nbsp;&nbsp;NotRunning</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >motor not running {K}</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Cm\MtrCtrl</td>
* <td align="left">&nbsp;&nbsp;NotStopped</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >motor not stopped {K}</td>
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
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(0).MtrSw</td>
* <td align="left">false</td>
* <td align="left">30S2 Plucker belt motor disconnect switch</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(0).MtrProt</td>
* <td align="left">false</td>
* <td align="left">30Q2 Plucker belt motor protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(0).NotRunning</td>
* <td align="left">false</td>
* <td align="left">110K5 Plucker belt motor not running</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(0).NotStopped</td>
* <td align="left">false</td>
* <td align="left">110K5 Plucker belt motor not stopped</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(1).MtrSw</td>
* <td align="left">false</td>
* <td align="left">31S8 Plucker belt motor disconnect switch</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(1).MtrProt</td>
* <td align="left">false</td>
* <td align="left">31Q8 Plucker belt motor protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(1).NotRunning</td>
* <td align="left">false</td>
* <td align="left">110K7 Plucker belt motor not running</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(1).NotStopped</td>
* <td align="left">false</td>
* <td align="left">110K7 Plucker belt motor not stopped</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(2).MtrSw</td>
* <td align="left">false</td>
* <td align="left">32S2 Plucker belt motor disconnect switch</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(2).MtrProt</td>
* <td align="left">false</td>
* <td align="left">32Q2 Plucker belt motor protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(2).NotRunning</td>
* <td align="left">false</td>
* <td align="left">111K1 Plucker belt motor not running</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Plucker</td>
* <td align="left">EventGlobal.Plucker.Belt(2).NotStopped</td>
* <td align="left">false</td>
* <td align="left">111K1 Plucker belt motor not stopped</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/



public class HiddenComponentCodeArraySkipExampleTest extends ExampleTest {

	private static final String NOT_STOPPED = "NotStopped";
	private static final String NOT_RUNNING = "NotRunning";
	private static final String MTR_PROT = "MtrProt";
	private static final String MTR_SW = "MtrSw";
	private static final String MOTOR_DISCONNECT_SWITCH = "{s} motor disconnect switch";
	private static final String MOTOR_DISCONNECT_SWITCH_WITHOUT_TAG = MOTOR_DISCONNECT_SWITCH.replace("{s} ", "");

	private static final String MOTOR_PROTECTION = "motor {q} protection";
	private static final String MOTOR_PROTECTION_WITHOUT_TAG = MOTOR_PROTECTION.replace("{q} ", "");
	private static final String MOTOR_NOT_RUNNING = "motor not running {K}";
	private static final String MOTOR_NOT_RUNNING_WITHOUT_TAG = MOTOR_NOT_RUNNING.replace(" {K}", "");
	private static final String MOTOR_NOT_STOPPED = "motor not stopped {K}";
	private static final String MOTOR_NOT_STOPPED_WITHOUT_TAG = MOTOR_NOT_STOPPED.replace(" {K}", "");

	private static final int MOTOR_COMPONENT_CODE_COLUMN = 2;
	private static final String MOTOR_COMPONENT_CODE_PAGE = "30";
	private static final int CONTACTOR_COMPONENT_CODE_COLUMN = 5;
	private static final String CONTACTOR_COMPONENT_CODE_PAGE = "110";

	private static final String S_EVENT_DOL = "sEventDol";
	private static final String MTR_CTRL = "MtrCtrl";
	private static final String CM = "Cm";
	private static final String BELT_MSG = "belt";
	private static final String BELT_VAR = "Belt";
	private static final String S_EVENT = "sEvent";
	private static final String PLUCKER = "Plucker";

	public HiddenComponentCodeArraySkipExampleTest() {
		setNote("Note that every hidden component code can have its own skip rules.");

		addGivenDataType(ExampleTest.NO_NAMESPACE, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, PLUCKER, PLUCKER + "\\" + S_EVENT, PLUCKER.toLowerCase());

		addGivenDataType(PLUCKER, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);

		addGivenDataTypeChild(PLUCKER, BELT_VAR, "ARRAY[0..2] OF " + CM + "\\" + MTR_CTRL + "\\" + S_EVENT_DOL,
				"{" + MOTOR_COMPONENT_CODE_PAGE + "M" + MOTOR_COMPONENT_CODE_COLUMN + " skip=u,30.4-31.6} {"
						+ CONTACTOR_COMPONENT_CODE_PAGE + "K" + CONTACTOR_COMPONENT_CODE_COLUMN + " skip=e} "
						+ BELT_MSG);
		addGivenDataType(CM, MTR_CTRL, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(CM, S_EVENT, CM + "\\" + MTR_CTRL + "\\" + S_EVENT_DOL, "");

		addGivenDataType(CM + "\\" + MTR_CTRL, S_EVENT_DOL, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, MTR_SW, OmronBaseType.BOOL.toString(), MOTOR_DISCONNECT_SWITCH);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, MTR_PROT, OmronBaseType.BOOL.toString(), MOTOR_PROTECTION);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, NOT_RUNNING, OmronBaseType.BOOL.toString(), MOTOR_NOT_RUNNING);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, NOT_STOPPED, OmronBaseType.BOOL.toString(), MOTOR_NOT_STOPPED);

		addExpectedUserAlarms(0,30,2,110,5);
		addExpectedUserAlarms(1,31,8,110,7);
		addExpectedUserAlarms(2,32,2,111,1);

	}

	private void addExpectedUserAlarms(int index, int motorPage, int motorColumn, int contactorPage, int contactorColumn) {
		addExpectedUserAlarm(PLUCKER, variableName + "." + PLUCKER + "." + BELT_VAR + "(" + index + ")." + MTR_SW,
				motorPage + "S" + motorColumn + " " + PLUCKER + " " + BELT_MSG
						+ " " + MOTOR_DISCONNECT_SWITCH_WITHOUT_TAG,
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(PLUCKER, variableName + "." + PLUCKER + "." + BELT_VAR + "(" + index + ")." + MTR_PROT,
				motorPage+ "Q" + motorColumn + " " + PLUCKER + " " + BELT_MSG
						+ " " + MOTOR_PROTECTION_WITHOUT_TAG,
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(PLUCKER, variableName + "." + PLUCKER + "." + BELT_VAR + "(" + index + ")." + NOT_RUNNING,
				contactorPage + "K" + contactorColumn + " " + PLUCKER + " "
						+ BELT_MSG + " " + MOTOR_NOT_RUNNING_WITHOUT_TAG,
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(PLUCKER, variableName + "." + PLUCKER + "." + BELT_VAR + "(" + index + ")." + NOT_STOPPED,
				contactorPage + "K" + contactorColumn+ " " + PLUCKER + " "
						+ BELT_MSG + " " + MOTOR_NOT_STOPPED_WITHOUT_TAG,
				Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE, ExampleTest.NO_DETAILS);
	}

	/**
	 * Used to create the JavaDoc for this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HiddenComponentCodeArraySkipExampleTest v = new HiddenComponentCodeArraySkipExampleTest();
		System.out.println(v.toJavaDoc());
	}

	@Test
	void testExample() {
		ExampleTest exampleTest = new HiddenComponentCodeArraySkipExampleTest();
		Variable eventVariable = new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType = exampleTest.getEventDataType();
		UserAlarmGroupFactory factory = new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}
}

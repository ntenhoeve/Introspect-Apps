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
* <h3>Hidden component code multitude example</h3>
* You can use multiple hidden component codes. These are used by derived component codes.<br>Note that you can use a number when there are multiple component codes with the same letter: e.g.:<br>{k1} referes to the first hidden component code (with a K) = 60K3<br>{k2} referes to the second hidden component code (with a K) = 60K4
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
* <td align="left" colspan=3 >{30Q3}{60K3}{60K4}{110S7} brush</td>
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
* <td align="left" colspan=3 >motor {Q} protection</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Cm\MtrCtrl</td>
* <td align="left">&nbsp;&nbsp;NotRunning</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >motor not running forward {k1}</td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Cm\MtrCtrl</td>
* <td align="left">&nbsp;&nbsp;NotStopped</td>
* <td align="left">BOOL</td>
* <td align="left" colspan=3 >motor not running backward {K2}</td>
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
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">EventGlobal.Maestro.Brush.MtrProt</td>
* <td align="left">false</td>
* <td align="left">30Q3 Maestro brush motor protection</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">EventGlobal.Maestro.Brush.NotRunning</td>
* <td align="left">false</td>
* <td align="left">60K3 Maestro brush motor not running forward</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* <tr bgcolor="#ffffff">
* <td align="left">Maestro</td>
* <td align="left">EventGlobal.Maestro.Brush.NotStopped</td>
* <td align="left">false</td>
* <td align="left">60K4 Maestro brush motor not running backward</td>
* <td align="left">UserFaultLevel5</td>
* <td align="left"></td>
* </tr>
* </table>
*
*/




public class HiddenComponentCodeMultipleExampleTest extends ExampleTest {

	
	
	private static final String NOT_STOPPED = "NotStopped";
	private static final String NOT_RUNNING = "NotRunning";
	private static final String MTR_PROT = "MtrProt";
	private static final String MTR_SW = "MtrSw";
	private static final String MOTOR_DISCONNECT_SWITCH = "{s} motor disconnect switch";
	private static final String MOTOR_DISCONNECT_SWITCH_WITHOUT_TAG = MOTOR_DISCONNECT_SWITCH.replace("{s} ", "");
	
	private static final String MOTOR_PROTECTION_MSG = "motor {Q} protection";
	private static final String MOTOR_PROTECTION_MSG_WITHOUT_TAG = MOTOR_PROTECTION_MSG.replace("{Q} ", "");
	private static final String MOTOR_NOT_RUNNING_MSG = "motor not running forward {k1}";
	private static final String MOTOR_NOT_RUNNING_MSG_WITHOUT_TAG = MOTOR_NOT_RUNNING_MSG.replace(" {k1}", "");
	private static final String MOTOR_NOT_STOPPED_MSG = "motor not running backward {K2}";
	private static final String MOTOR_NOT_STOPPED_MSG_WITHOUT_TAG = MOTOR_NOT_STOPPED_MSG.replace(" {K2}", "");
	

	private static final String MOTOR_DISCONNECT_SWITCH_CODE = "110S7";
	private static final String MOTOR_DISCONNECT_SWITCH_CODE_HIDDEN = "{"+MOTOR_DISCONNECT_SWITCH_CODE+"}";
	private static final String MOTOR_PROTECTION_CODE = "30Q3";
	private static final String MOTOR_PROTECTION_CODE_HIDDEN = "{"+MOTOR_PROTECTION_CODE+"}";
	private static final String CONTACTOR1_CODE = "60K3";
	private static final String CONTACTOR1_CODE_HIDDEN = "{"+CONTACTOR1_CODE+"}";
	private static final String CONTACTOR2_CODE = "60K4";
	private static final String CONTACTOR2_CODE_HIDDEN = "{"+CONTACTOR2_CODE+"}";
	private static final String S_EVENT_DOL = "sEventDol";
	private static final String MTR_CTRL = "MtrCtrl";
	private static final String CM = "Cm";
	private static final String BRUSH_MSG = "brush";
	private static final String BRUSH_VAR = "Brush";
	private static final String S_EVENT = "sEvent";
	private static final String MAESTRO = "Maestro";
	

	public HiddenComponentCodeMultipleExampleTest() {
		setNote("You can use multiple hidden component codes. These are used by derived component codes.<br>Note that you can use a number when there are multiple component codes with the same letter: e.g.:<br>{k1} referes to the first hidden component code (with a K) = 60K3<br>{k2} referes to the second hidden component code (with a K) = 60K4");
		
		
		addGivenDataType(ExampleTest.NO_NAMESPACE, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(ExampleTest.NO_NAMESPACE, MAESTRO, MAESTRO + "\\" + S_EVENT, MAESTRO.toLowerCase());


		addGivenDataType(MAESTRO, S_EVENT, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(MAESTRO, BRUSH_VAR, CM + "\\" + MTR_CTRL + "\\" + S_EVENT_DOL,
				MOTOR_PROTECTION_CODE_HIDDEN+CONTACTOR1_CODE_HIDDEN+CONTACTOR2_CODE_HIDDEN+MOTOR_DISCONNECT_SWITCH_CODE_HIDDEN+" " + BRUSH_MSG);
		
		addGivenDataType(CM, MTR_CTRL, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(CM, S_EVENT, CM + "\\" + MTR_CTRL + "\\" + S_EVENT_DOL,"");

		addGivenDataType(CM + "\\" + MTR_CTRL, S_EVENT_DOL, BaseType.STRUCT, ExampleTest.NO_COMMENT);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, MTR_SW, OmronBaseType.BOOL.toString(),MOTOR_DISCONNECT_SWITCH);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, MTR_PROT, OmronBaseType.BOOL.toString(),MOTOR_PROTECTION_MSG);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, NOT_RUNNING, OmronBaseType.BOOL.toString(),MOTOR_NOT_RUNNING_MSG);
		addGivenDataTypeChild(CM + "\\" + MTR_CTRL, NOT_STOPPED, OmronBaseType.BOOL.toString(),MOTOR_NOT_STOPPED_MSG);


		addExpectedUserAlarm(MAESTRO, variableName + "." + MAESTRO + "." + BRUSH_VAR+"."+MTR_SW,
				MOTOR_DISCONNECT_SWITCH_CODE+ " " + MAESTRO + " " + BRUSH_MSG+ " "+MOTOR_DISCONNECT_SWITCH_WITHOUT_TAG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE,
				ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(MAESTRO, variableName + "." + MAESTRO + "." + BRUSH_VAR+"."+MTR_PROT,
				MOTOR_PROTECTION_CODE+ " " + MAESTRO + " " + BRUSH_MSG+ " "+MOTOR_PROTECTION_MSG_WITHOUT_TAG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE,
				ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(MAESTRO, variableName + "." + MAESTRO + "." + BRUSH_VAR+"."+NOT_RUNNING,
				CONTACTOR1_CODE+ " " + MAESTRO + " " + BRUSH_MSG+ " "+MOTOR_NOT_RUNNING_MSG_WITHOUT_TAG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE,
				ExampleTest.NO_DETAILS);
		addExpectedUserAlarm(MAESTRO, variableName + "." + MAESTRO + "." + BRUSH_VAR+"."+NOT_STOPPED,
				CONTACTOR2_CODE+ " " + MAESTRO + " " + BRUSH_MSG+ " "+MOTOR_NOT_STOPPED_MSG_WITHOUT_TAG, Priority.MEDIUM, ExampleTest.NO_ACKNOWLEDGE,
				ExampleTest.NO_DETAILS);
	}
	
	/**
	 * Used to create the JavaDoc for this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HiddenComponentCodeMultipleExampleTest v = new HiddenComponentCodeMultipleExampleTest();
		System.out.println(v.toJavaDoc());
	}

	@Test
	void testExample() {
		ExampleTest exampleTest = new HiddenComponentCodeMultipleExampleTest();
		Variable eventVariable = new Variable();
		eventVariable.setName(exampleTest.getVariableName());
		DataType eventDataType = exampleTest.getEventDataType();
		UserAlarmGroupFactory factory = new UserAlarmGroupFactory();
		List<UserAlarmGroup> userAlarmGroups = factory.create(eventVariable, eventDataType);
		assertThat(userAlarmGroups).containsAll(exampleTest.getUserAlarmsGroups());
	}
}

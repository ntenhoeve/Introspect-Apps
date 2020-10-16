package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nth.reflect.fw.generic.util.StringUtil;
import nth.sysmac.user.alarms.generator.SysmacUserAlarmsGeneratorDocumentation;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroup;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

/**
 * An {@link ExampleTest} is two things into one:
 * <ul>
 * <li>It is an example for how the {@link DataType} is converted to a {@link UserAlarm}. This example can be converted {@link #toHtml()} or {@link #toJavaDoc()} for documentation purposes (see {@link SysmacUserAlarmsGeneratorDocumentation}).</li>
 * <li>It is a JUnit test to proof that this works correctly.</li>
 * </ul>
 * @author nilsth
 *
 */
public class ExampleTest {

	public static final String NO_NAMESPACE = "";
	public static final String NO_COMMENT = "";
	public static final String NO_DETAILS = "";
	public static final String NO_GROUP_NAME = "";
	public static final boolean NO_ACKNOWLEDGE = false;
	public static final boolean ACKNOWLEDGE=true;
	public final List<DataType> dataTypes = new ArrayList<>();
	public final List<UserAlarm> userAlarms = new ArrayList<>();
	public final String variableName = "EventGlobal";
	private String note="";

	
	

	public void addGivenDataType(String nameSpace, String name, String baseTypeExpression, String comment) {
		DataType dataType = createDataType(nameSpace, name, baseTypeExpression, comment);
		dataTypes.add(dataType);
	}

	public void addGivenDataTypeChild(String nameSpace, String name, String baseTypeExpression, String comment) {
		if (dataTypes.isEmpty())
			throw new RuntimeException("First create a parent DataType with the addDataType method");
		DataType parent = dataTypes.get(dataTypes.size() - 1);
		DataType child = createDataType(nameSpace, name, baseTypeExpression, comment);
		parent.getChildren().add(child);
	}
	
	public void addExpectedUserAlarm(String groupName, String expression, String message, Priority priority, boolean acknowledge, String details) {
		UserAlarm userAlarm =new UserAlarm( groupName,  expression,  message,  priority,  acknowledge,  details);
		userAlarms.add(userAlarm);
	}

	private DataType createDataType(String nameSpace, String name, String baseTypeExpression, String comment) {
		DataType dataType = new DataType();
		dataType.setNameSpace(nameSpace);
		dataType.setName(name);
		dataType.setBaseTypeExpression(baseTypeExpression);
		dataType.setComment(comment);
		return dataType;
	}

	public void setNote(String note) {
		this.note=note;
	}
	
	public List<DataType> getDataTypes() {
		return dataTypes;
	}

	public List<UserAlarm> getUserAlarms() {
		return userAlarms;
	}

	public String getVariableName() {
		return variableName;
	}

	private String toHtml() {
		StringBuilder html = new StringBuilder();
		appendHtmlHeader(html);
		appendHtmlTable(html);
		return html.toString();
	}

	private void appendHtmlTable(StringBuilder html) {
		html.append("<table cellspacing=\"1\" cellpadding=\"4\" bgcolor=\"#000000\">\n");
		appendHtmlDataTypeHeader(html);
		appendHtmlDataTypes(html);
		appendHtmlUserAlarmHeader(html);
		appendHtmlUserAlarm(html);
		html.append("</table>\n");
	}

	private void appendHtmlUserAlarm(StringBuilder html) {
		for (UserAlarm userAlarm : userAlarms) {
			html.append("<tr bgcolor=\"#ffffff\">\n");
			html.append("<td align=\"left\">" + userAlarm.getGroupName1() + "</td>\n");
			html.append("<td align=\"left\">" + userAlarm.getExpression() + "</td>\n");
			html.append("<td align=\"left\">" + userAlarm.isAcknowledge() + "</td>\n");
			html.append("<td align=\"left\">" + userAlarm.getMessage() + "</td>\n");
			html.append("<td align=\"left\">" + userAlarm.getPriority() + "</td>\n");
			html.append("<td align=\"left\">" + userAlarm.getDetails() + "</td>\n");
			html.append("</tr>\n");
		}
	}

	private void appendHtmlUserAlarmHeader(StringBuilder html) {
		html.append("<tr bgcolor=\"#ffffff\">\n");
		html.append("<th colspan=6>Results in UserAlarm(s):</th>\n");
		html.append("</tr>\n");
		html.append("<tr bgcolor=\"#ffffff\">\n");
		html.append("<th align=\"left\">Group</th>\n");
		html.append("<th align=\"left\">Expression</th>\n");
		html.append("<th align=\"left\">Acknowledge</th>\n");
		html.append("<th align=\"left\">Message</th>\n");
		html.append("<th align=\"left\">Priority</th>\n");
		html.append("<th align=\"left\">Details</th>\n");
		html.append("</tr>\n");
	}

	private void appendHtmlDataTypes(StringBuilder html) {
		for (DataType dataType : dataTypes) {
			int siblingIndex=0;
			appendHtmlDataType(html, dataType, siblingIndex);
			if (!dataType.getChildren().isEmpty()) {
				siblingIndex++;
				for (DataType children : dataType.getChildren()) {
					appendHtmlDataType(html, children, siblingIndex);
				}
			}
		}
	}

	private void appendHtmlDataType(StringBuilder html, DataType dataType, int siblingIndex) {
		html.append("<tr bgcolor=\"#ffffff\">\n");
		html.append("<td align=\"left\">" + dataType.getNameSpace() + "</td>\n");
		html.append("<td align=\"left\">" + "&nbsp;".repeat(siblingIndex*2)+  dataType.getName() + "</td>\n");
		html.append("<td align=\"left\">" + dataType.getBaseTypeExpression() + "</td>\n");
		html.append("<td align=\"left\" colspan=3 >" + dataType.getComment() + "</td>\n");
		html.append("</tr>\n");
	}

	private void appendHtmlDataTypeHeader(StringBuilder html) {
		html.append("<tr bgcolor=\"#ffffff\">\n");
		html.append("<th colspan=6>Variable: ");
		html.append(variableName);
		html.append(" of type: ");
		html.append(getDataTypes().get(0).getName());
		html.append("<br>Data Type's:</th>\n");
		html.append("</tr>\n");
		html.append("<tr bgcolor=\"#ffffff\">\n");
		html.append("<th align=\"left\">Namespace</th>\n");
		html.append("<th align=\"left\">Name</th>\n");
		html.append("<th align=\"left\">Base Type</th>\n");
		html.append("<th align=\"left\" colspan=3 >Comment</th>\n");
		html.append("</tr>\n");
	}

	private void appendHtmlHeader(StringBuilder html) {
		html.append("<h3>");
		html.append(StringUtil.convertToNormalCase(this.getClass().getSimpleName().replace("Test", "")));
		html.append("</h3>\n");
		if (!note.trim().isBlank()) {
			html.append(note);
			html.append("\n");	
		}
		
	}

	public String toJavaDoc() {
		StringBuilder javaDoc = new StringBuilder();
		javaDoc.append("/**\n");
		javaDoc.append(" *\n");
		String[] htmlLines = toHtml().split("\\n");
		for (String htmlLine : htmlLines) {
			javaDoc.append(" * ");
			javaDoc.append(htmlLine);
			javaDoc.append("\n");
		}
		javaDoc.append(" *\n");
		javaDoc.append(" */\n");
		return javaDoc.toString();
	}


	public DataType getEventDataType() {
		DataType root = dataTypes.get(0);
		root.addReferencedChilderen(dataTypes);
		return root;
	}

	public List<UserAlarmGroup> getUserAlarmsGroups() {
		List<UserAlarmGroup> userAlarmGroups=new ArrayList<>();
		Set<String> groupNames = userAlarms.stream().map(ua-> ua.getGroupName1()).collect(Collectors.toSet());
		for (String groupName : groupNames) {
			List<UserAlarm> userAlarmsForGroup = userAlarms.stream().filter(ua-> ua.getGroupName1().equals(groupName)).collect(Collectors.toList());
			UserAlarmGroup userAlarmGroup=new UserAlarmGroup(groupName, userAlarmsForGroup);
			userAlarmGroups.add(userAlarmGroup);
		}
		return userAlarmGroups;
	}
	
	
}

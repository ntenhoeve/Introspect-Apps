package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.TextExpression;

public class UserAlarm {
	private static final String SEPERATOR = ", ";
	private String groupName1;
	private String groupName2;
	private String groupName3;
	private String alarmID;
	private String alarmCode;
	private String expression;
	private String priority;
	private String message;
	private boolean popup;
	private boolean acknowledge;
	private String page;
	private String details;
	
	public UserAlarm(String groupName, String varExpr, TextExpression textExpression) {
		groupName1=groupName;
		expression=varExpr;
		message=textExpression.getMessage();
	}


	@Override
	public String toString() {
		TitleBuilder title=new TitleBuilder();
		title.append(UserAlarm.class.getSimpleName());
		title.append(SEPERATOR+"groupName1=",groupName1);
		title.append(SEPERATOR+"groupName2=",groupName2);
		title.append(SEPERATOR+"groupName3=",groupName3);
		title.append(SEPERATOR+"alarmID=",alarmID);
		title.append(SEPERATOR+"alarmCode=",alarmCode);
		title.append(SEPERATOR+"expression=",expression);
		title.append(SEPERATOR+"priority=",priority);
		title.append(SEPERATOR+"message=",message);
		title.append(SEPERATOR+"popup=",popup);
		title.append(SEPERATOR+"acknowledge=",acknowledge);
		title.append(SEPERATOR+"page=",page);
		title.append(SEPERATOR+"details=",details);
		return title.toString();
	}

}

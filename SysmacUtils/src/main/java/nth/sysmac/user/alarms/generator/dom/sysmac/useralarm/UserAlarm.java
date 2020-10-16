package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;

public class UserAlarm {
	private static final String SEPERATOR = ", ";
	private String groupName1;
	private String groupName2;
	private String groupName3;
	private String alarmID;
	private String alarmCode;
	private String expression;
	private Priority priority;
	private String message;
	private boolean popup;
	private boolean acknowledge;
	private String page;
	private String details;
	
	public UserAlarm(String groupName, DataTypePathConverter dataTypePathConverter) {
		groupName1=groupName;
		expression=dataTypePathConverter.getExpression();
		message=dataTypePathConverter.getComponentCodeAndMessage();
		priority=dataTypePathConverter.getPriority();
		acknowledge=dataTypePathConverter.getAcknowlegde();
		details=dataTypePathConverter.getDetails();
	}

	public UserAlarm(String groupName, String expression, String message, Priority priority, boolean acknowledge, String details) {
		this.groupName1=groupName;
		this.expression=expression;
		this.message=message;
		this.priority=priority;
		this.acknowledge=acknowledge;
		this.details=details;
	}
	

	public String getGroupName1() {
		return groupName1;
	}



	public void setGroupName1(String groupName1) {
		this.groupName1 = groupName1;
	}



	public String getGroupName2() {
		return groupName2;
	}



	public void setGroupName2(String groupName2) {
		this.groupName2 = groupName2;
	}



	public String getGroupName3() {
		return groupName3;
	}



	public void setGroupName3(String groupName3) {
		this.groupName3 = groupName3;
	}



	public String getAlarmID() {
		return alarmID;
	}



	public void setAlarmID(String alarmID) {
		this.alarmID = alarmID;
	}



	public String getAlarmCode() {
		return alarmCode;
	}



	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}



	public String getExpression() {
		return expression;
	}



	public void setExpression(String expression) {
		this.expression = expression;
	}



	public String getPriority() {
		return priority.getOmronPriority();
	}



	public void setPriority(Priority priority) {
		this.priority = priority;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public boolean isPopup() {
		return popup;
	}



	public void setPopup(boolean popup) {
		this.popup = popup;
	}



	public boolean isAcknowledge() {
		return acknowledge;
	}



	public void setAcknowledge(boolean acknowledge) {
		this.acknowledge = acknowledge;
	}



	public String getPage() {
		return page;
	}



	public void setPage(String page) {
		this.page = page;
	}



	public String getDetails() {
		return details;
	}



	public void setDetails(String details) {
		this.details = details;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAlarm other = (UserAlarm) obj;
		if (acknowledge != other.acknowledge)
			return false;
		if (alarmCode == null) {
			if (other.alarmCode != null)
				return false;
		} else if (!alarmCode.equals(other.alarmCode))
			return false;
		if (alarmID == null) {
			if (other.alarmID != null)
				return false;
		} else if (!alarmID.equals(other.alarmID))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (groupName1 == null) {
			if (other.groupName1 != null)
				return false;
		} else if (!groupName1.equals(other.groupName1))
			return false;
		if (groupName2 == null) {
			if (other.groupName2 != null)
				return false;
		} else if (!groupName2.equals(other.groupName2))
			return false;
		if (groupName3 == null) {
			if (other.groupName3 != null)
				return false;
		} else if (!groupName3.equals(other.groupName3))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		if (popup != other.popup)
			return false;
		if (priority != other.priority)
			return false;
		return true;
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

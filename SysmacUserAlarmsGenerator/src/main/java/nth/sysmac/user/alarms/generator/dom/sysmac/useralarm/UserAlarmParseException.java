package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypePath;

public class UserAlarmParseException extends RuntimeException {

	private static final long serialVersionUID = -8628077208214710770L;

	public UserAlarmParseException( DataTypePath dataTypePath, Throwable e) {
		super("Error creating an UserAlarm for: "+dataTypePath,e);
	}

	public UserAlarmParseException(List<UserAlarmParseException> exceptions) {
		super(createSummary(exceptions));
	}

	private static String createSummary(List<UserAlarmParseException> exceptions) {
		StringBuilder summary=new StringBuilder();
		for (UserAlarmParseException exception : exceptions) {
			summary.append(exception.getMessage());
			summary.append("    : ");
			summary.append(exception.getCause().getMessage());
			summary.append("\n");
		}
		return summary.toString();
	}

}

package nth.meyn.control.systems.dom.activity;

import nth.meyn.control.systems.dom.employee.Employee;

public enum ActivityStatus {

	COMPLETED, NOT_APPLICABLE, ASSIGNED, UNASSIGNED, LATE;

	public static ActivityStatus get(Activity activity) {
		Employee scheduledFor = activity.getScheduledFor();
		Employee completedBy = activity.getCompletedBy();
		if (completedBy != null) {
			return COMPLETED;
		} else if (scheduledFor != null && scheduledFor.isNotApplicable()) {
			return NOT_APPLICABLE;
		} else if (scheduledFor != null) {
			return ASSIGNED;
		} else
			return UNASSIGNED;
	}

}

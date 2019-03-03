package nth.meyn.control.system.configurator.dom.alarm;

public enum AlarmSeverity {
	/**
	 * Severe alarm that stops the system e.g. an emergency stop or low air
	 * pressure or main drive trip.
	 */
	FATAL,
	/**
	 * A less severe alarm that does not stop the system but may effect the
	 * process and needs direct attention
	 */
	ALARM,
	/**
	 * A less severe alarm that does not stop the system but has almost no
	 * effect the process and needs no direct attention
	 */
	WARNING,
	/**
	 * An event that needs to be logged, e.g. when a user stops the line.
	 */
	EVENT
}

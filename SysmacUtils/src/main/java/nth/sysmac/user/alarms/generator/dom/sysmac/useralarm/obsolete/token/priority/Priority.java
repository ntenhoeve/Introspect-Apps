package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.priority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.regex.Regex;

/**
 * The &lt;value&gt; can be any of the following values in the first column:
 * <p>
 * <table border=1>
 * <tr>
 * <th align="left">Priority</th> 
 * <th align="left">Omron Priority</th>
 * <th align="left">Description</th>
 * </tr>
 * <tr>
 * <td>critical</td>
 * <td>UserFaultLevel1</td>
 * <td>A critical problem that stops the system, e.g Line drive trip, EtherCAT
 * error, Emergency stop</td>
 * </tr>
 * <tr>
 * <td>high</td>
 * <td>UserFaultLevel3</td>
 * <td>When immediate action is required by an operator, e.g. a a hot electric
 * motor, a tripped knife motor</td>
 * </tr>
 * <tr>
 * <td>medium</td>
 * <td>UserFaultLevel5</td>
 * <td>When rapid action is required by an operator, e.g. a low temperature</td>
 * </tr>
 * <tr>
 * <td>low</td>
 * <td>UserFaultLevel7</td>
 * <td>When no urgent action is required by an operator, e.g. a tripped plucker
 * motor</td>
 * </tr>
 * <tr>
 * <td>info</td>
 * <td>UserInformation</td>
 * <td>All events that are not an error, e.g. when a stop button is pressed</td>
 * </tr>
 */
public enum Priority {
	CRITICAL(1, "A critical problem that stops the system, e.g Line drive trip, Ethercat error, Emergency stop"),
	HIGH(3, "When immediate action is required by an operator, e.g. a a hot electric motor, a tripped knife motor"),
	MEDIUM(5, "When rapid action is required by an operator, e.g. a low temperature"),
	LOW(7, "When no urgent action is required by an operator, e.g. a tripped plucker motor"),
	INFO(9, "All events that are not an error, e.g. when a stop button is pressed");

	private final int level;
	private final String description;

	Priority(int level, String description) {
		this.level = level;
		this.description = description;
	}

	public String getOmronPriority() {
		if (level > 0 && level < 9) {
			return "UserFaultLevel" + level;
		} else {
			return "UserInformation";
		}
	}

	public int getLevel() {
		return level;
	}

	public String getDescription() {
		return description;
	}

	public static List<String> valuesToLowCaseStrings() {
		return Arrays.asList(values()).stream().map(p -> p.name().toLowerCase())
				.collect(Collectors.toUnmodifiableList());
	}

	public static List<Regex> valuesToRegex() {
		return valuesToLowCaseStrings().stream().map(s -> new Regex().literal(s))
				.collect(Collectors.toUnmodifiableList());
	}

}

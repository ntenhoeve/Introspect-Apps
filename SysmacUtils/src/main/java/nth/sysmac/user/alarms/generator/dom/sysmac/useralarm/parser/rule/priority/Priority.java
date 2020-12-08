package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.regex.Regex;

/**
 * <p>
 * <table border=1>
 * <tr>
 * <th align="left">Abbreviation</th> 
 * <th align="left">Priority</th> 
 * <th align="left">Omron Priority</th>
 * <th align="left">Description</th>
 * </tr>
 * <tr>
 * <td>F</td>
 * <td>fatal</td>
 * <td>UserFaultLevel1</td>
 * <td>A critical problem that stops the system and can not be restarted before fixed, e.g Line drive trip, Ethercat error, Emergency stop, or an important fuse of the control system</td>
 * </tr>
 * <tr>
 * <td>C</td>
 * <td>critical</td>
 * <td>UserFaultLevel2</td>
 * <td>A critical problem that stops the system but can be restarted, e.g. a motor overheating</td>
 * </tr>
 * <tr>
 * <td>H</td>
 * <td>high</td>
 * <td>UserFaultLevel3</td>
 * <td>When immediate action is required by an operator, e.g. a a hot electric motor, a tripped knife motor</td>
 * </tr>
 * <tr>
 * <td>MH</td>
 * <td>medium_hight</td>
 * <td>UserFaultLevel4</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>M</td>
 * <td>medium</td>
 * <td>UserFaultLevel5</td>
 * <td>When rapid action is required by an operator, e.g. a low temperature</td>
 * </tr>
 * <tr>
 * <td>ML</td>
 * <td>medium_low</td>
 * <td>UserFaultLevel6</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>L</td>
 * <td>low</td>
 * <td>UserFaultLevel7</td>
 * <td>When no urgent action is required by an operator, e.g. a tripped plucker motor</td>
 * </tr>
 * <tr>
 * <td>I</td>
 * <td>info</td>
 * <td>UserInformation</td>
 * <td>All events that are not an error, e.g. when a stop button is pressed</td>
 * </tr>
 * </table>
 */

public enum Priority {
	FATAL(
			"F",
			1,
			"A critical problem that stops the system and can not be restarted before fixed, e.g Line drive trip, Ethercat error, Emergency stop, or an important fuse of the control system"),
	CRITICAL(
			"C", //
			2, //
			"A critical problem that stops the system but can be restarted, e.g. a motor overheating"),
	HIGH(
			"H",
			3,
			"When immediate action is required by an operator, e.g. a a hot electric motor, a tripped knife motor"),
	MEDIUM_HIGHT(
			"MH", //
			4, //
			""),
	MEDIUM(
			"M", //
			5, //
			"When rapid action is required by an operator, e.g. a low temperature"),
	MEDIUM_LOW(
			"ML", //
			6, //
			""),
	LOW(
			"L", //
			7, //
			"When no urgent action is required by an operator, e.g. a tripped plucker motor"),
	INFO(
			"I", //
			9, //
			"All events that are not an error, e.g. when a stop button is pressed");

	private final int level;
	private final String description;
	private final String abbreviation;

	Priority(String abbreviation, int level, String description) {
		this.abbreviation = abbreviation;
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public static List<String> valuesToLowCaseStrings() {
		return Arrays.asList(values()).stream().map(p -> p.name().toLowerCase())
				.collect(Collectors.toUnmodifiableList());
	}

	public static List<Regex> valuesToRegex() {
		return valuesToLowCaseStrings().stream().map(s -> new Regex().literal(s))
				.collect(Collectors.toUnmodifiableList());
	}

	public static String toJavaDoc() {
		StringBuilder javaDoc = new StringBuilder();
		javaDoc.append("/**\n" //
				+ " * <p>\n" + " * <table border=1>\n"//
				+ " * <tr>\n" + " * <th align=\"left\">Abbreviation</th> \n" //
				+ " * <th align=\"left\">Priority</th> \n"//
				+ " * <th align=\"left\">Omron Priority</th>\n" //
				+ " * <th align=\"left\">Description</th>\n"//
				+ " * </tr>\n");
		for (Priority priority : values()) {
			javaDoc.append(" * <tr>\n" //
					+ " * <td>" + priority.getAbbreviation() + "</td>\n" //
					+ " * <td>" + priority.name().toLowerCase() + "</td>\n"//
					+ " * <td>" + priority.getOmronPriority() + "</td>\n"//
					+ " * <td>" + priority.getDescription() + "</td>\n"//
					+ " * </tr>\n");
		}
		javaDoc.append(" * </table>\n"//
				+ " */");
		return javaDoc.toString();
	}

	public static void main(String[] args) {
		System.out.println(Priority.toJavaDoc());
	}

	public static Priority valueOfAbbreviation(String abbreviation) {
		for (Priority priority : values()) {
			if (priority.getAbbreviation().equals(abbreviation.toUpperCase())) {
				return priority;
			}
		}
		throw new RuntimeException("Could not find a matching "+Priority.class.getSimpleName()+" for abbreviation: "+abbreviation);
	}
}

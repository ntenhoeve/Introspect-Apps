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
 * <td>A fatal problem that prevents the system from working (fatal for system).<br>e.g. an EtherCAT error, an important fuse of the control system, missing IO cards, critical IO card errors, etc.</td>
 * </tr>
 * <tr>
 * <td>C</td>
 * <td>critical</td>
 * <td>UserFaultLevel2</td>
 * <td>A critical problem that stops the system <br>e.g. an Emergency stop, a critical motor tripped, low hydraulic level, etc.</td>
 * </tr>
 * <tr>
 * <td>H</td>
 * <td>high</td>
 * <td>UserFaultLevel3</td>
 * <td>A problem with major consequences, but system keeps running<br>Immediate action is required<br>e.g. an important motor tripped, etc.</td>
 * </tr>
 * <tr>
 * <td>MH</td>
 * <td>medium_hight</td>
 * <td>UserFaultLevel4</td>
 * <td>A problem with moderate consequences<br>Urgent action is required</td>
 * </tr>
 * <tr>
 * <td>M</td>
 * <td>medium</td>
 * <td>UserFaultLevel5</td>
 * <td>A problem with some consequences<br>Action within 5 minutes is required<br>e.g. a low temperature, etc.</td>
 * </tr>
 * <tr>
 * <td>ML</td>
 * <td>medium_low</td>
 * <td>UserFaultLevel6</td>
 * <td>A problem with minor consequences<br>Action within 15 minutes is required</td>
 * </tr>
 * <tr>
 * <td>L</td>
 * <td>low</td>
 * <td>UserFaultLevel7</td>
 * <td>A problem with almost no consequences<br>Eventually action is required<br>e.g. a tripped plucker motor</td>
 * </tr>
 * <tr>
 * <td>I</td>
 * <td>info</td>
 * <td>UserInformation</td>
 * <td>All events that are not an error<br>e.g. when a stop button is pressed, external stop. Etc.</td>
 * </tr>
 * </table>
 */

public enum Priority {
	FATAL(
			"F",
			1,
			"A fatal problem that prevents the system from working (fatal for system).\r\n" + 
			"e.g. an EtherCAT error, an important fuse of the control system, missing IO cards, critical IO card errors, etc."),
	CRITICAL(
			"C", //
			2, //
			"A critical problem that stops the system \r\n" + 
			"e.g. an Emergency stop, a critical motor tripped, low hydraulic level, etc."),
	HIGH(
			"H",
			3,
			"A problem with major consequences, but system keeps running\r\n" + 
			"Immediate action is required\r\n" + 
			"e.g. an important motor tripped, etc."),
	MEDIUM_HIGHT(
			"MH", //
			4, //
			"A problem with moderate consequences\r\n" + 
			"Urgent action is required"),
	MEDIUM(
			"M", //
			5, //
			"A problem with some consequences\r\n" + 
			"Action within 5 minutes is required\r\n" + 
			"e.g. a low temperature, etc."),
	MEDIUM_LOW(
			"ML", //
			6, //
			"A problem with minor consequences\r\n" + 
			"Action within 15 minutes is required"),
	LOW(
			"L", //
			7, //
			"A problem with almost no consequences\r\n" + 
			"Eventually action is required\r\n" + 
			"e.g. a tripped plucker motor"),
	INFO(
			"I", //
			9, //
			"All events that are not an error\r\n" + 
			"e.g. when a stop button is pressed, external stop. Etc.");

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
					+ " * <td>" + priority.getDescription().replace("\r\n","<br>") + "</td>\n"//
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

package nth.synchronizefolder.domain;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {
	
	public static List<String> parse(String commandLine) {
		boolean betweenBrackets=false;
		commandLine=commandLine.trim()+" ";
		StringBuilder argument=new StringBuilder();
		ArrayList<String> arguments = new ArrayList<String>();
		for (char ch:commandLine.toCharArray()) {
			if (ch=='"' || ch=='\'') {
				betweenBrackets=!betweenBrackets;
			} else 	if (Character.isWhitespace(ch) && !betweenBrackets) {
				if (argument.length()>0) {
					arguments.add(argument.toString());
					argument=new StringBuilder();
				}
			} else {
				argument.append(ch);
			}
		}
		return arguments;
	}
}

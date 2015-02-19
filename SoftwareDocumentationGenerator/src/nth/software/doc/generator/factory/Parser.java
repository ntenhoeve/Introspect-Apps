package nth.software.doc.generator.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nth.software.doc.generator.regex.Regex;


public class Parser {

	private static final int NOT_FOUND = -1;
	private String result;

	public Parser(String text) {
		this.result = new String(text);
	}

	public List<String> findAll(Regex regEx) {
		List<String> results=new ArrayList<>();
		Matcher matcher = Pattern.compile(regEx.toString()).matcher(result);
        while (matcher.find()) {
            String result=matcher.group();
            results.add(result);
        }
		return results;
	}

	public void removeAll(Regex regex) {
		result=result.replaceAll(regex.toString(), "");
	}

	public String getResult() {
		return result;
	}

	public boolean startsWith(String prefix) {
		return result.startsWith(prefix);
	}

	public void removeFirst(String textToRemove) {
		int startPos = result.indexOf(textToRemove);
		if (startPos!=NOT_FOUND) {
			startPos+=textToRemove.length();
			result=result.substring(startPos);
		}
	}

	public void removeFrom(String textToFind) {
		int startPos = result.indexOf(textToFind);
		if (startPos!=NOT_FOUND) {
			result=result.substring(0,startPos);
		}
	}

	public void replaceAll(Regex regex,String  replacement) {
		result=result.replaceAll(regex.toString(), replacement);
	}

	@Override
	public String toString() {
		return result;
	}

	
	
}

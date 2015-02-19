package nth.software.doc.generator.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringParser {


	private List<String> lines;

	public StringParser(List<String> lines) {
		this.lines = new ArrayList<String>();
		createCopy(lines);
	}

	private void createCopy(List<String> lines) {
		for (String line:lines) {
			this.lines.add(new String(line));
		}
	}

	public void removeLinesStartingWith(String textToFind) {
		Iterator<String> iterator = lines.iterator();
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line.startsWith(textToFind)) {
				iterator.remove();
			}
		}
	}


	public void removeEmptyLines() {
		Iterator<String> iterator = lines.iterator();
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line==null || line.length()==0) {
				iterator.remove();
			}
		}
	}

	public boolean startsWith(String textToFind) {
		if (lines.size()<1) {
			return false;
		}
		String firstLine=lines.get(0);
		return firstLine.startsWith(textToFind);
	}
	public void removeFrom(String textToFind) {
		boolean found=false;
		Iterator<String> iterator = lines.iterator();
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (!found && line.contains(textToFind)) {
				found=true;
			}
			if(found) {
				iterator.remove();
			}
		}
	}



	public void removeUpTo(String textToFind) {
		boolean found=false;
		Iterator<String> iterator = lines.iterator();
		while (iterator.hasNext()) {
			String line = iterator.next();
			if(!found) {
				iterator.remove();
			}
			if (!found && line.contains(textToFind)) {
				found=true;
			}
		}
	}

	public void removeAllRegEx(String regex) {
		ArrayList<String> resultLines = new ArrayList<String>();
		for (String line: lines) {
			String resultLine = line.replaceAll(regex, "");
			resultLines.add(resultLine);
		}
		lines=resultLines;
	}

	public String getLinesAsString() {
		StringBuilder result=new StringBuilder();
		for (String line : lines) {
			result.append(line);
		}
		return result.toString();
	}

	
}

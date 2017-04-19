package nth.meyn.cx.sysmac.converter.cx.ladder.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated use xml from clipboard instead
 * @author nilsth
 *
 */
public class Tokenizer {

	private static final int NOT_FOUND = -1;
	

	public static List<String> createTokens(String text, List<String> delimiters) {
		
		sortDelimitersLongToShort(delimiters);
		
		List<String> tokens=new ArrayList<>();
		String remainingText = text;
		while (remainingText.length()>0) {
		int indexNextToken = getIndexNextDelimiter(remainingText,delimiters);
		if (indexNextToken==NOT_FOUND) {
			tokens.add(remainingText);
			remainingText="";
		} else if (indexNextToken==0) {
			String delimiter=findDelimiter(remainingText,delimiters);
			tokens.add(delimiter);
			remainingText=remainingText.substring(delimiter.length());
		} else {
			String textBetweenDelimiters = remainingText.substring(0, indexNextToken);
			tokens.add(textBetweenDelimiters);
			remainingText=remainingText.substring(indexNextToken);
		}
		}
		return tokens;
	}

	private static void sortDelimitersLongToShort(List<String> delimiters) {
		delimiters.sort((s1, s2) -> s2.length()-s1.length());
	}

	private static String findDelimiter(String remainingText, List<String> delimiters) {
		for (String delimiter : delimiters) {
			if (remainingText.startsWith(delimiter)) {
				return delimiter;
			}
		}
		return null;
	}

	private static int getIndexNextDelimiter(String remainingText, List<String> delimiters) {
		int index=NOT_FOUND;
		for (String delimiter : delimiters) {
			int foundIndex = remainingText.indexOf(delimiter);
			if (foundIndex>=0 && (index>foundIndex || index==NOT_FOUND)) {
				index=foundIndex;
			}
					
		}
		return index;
	}

	
}

package nth.meyn.display.translate.dom.componentcode;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import nth.reflect.util.regex.LetterTypes;
import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class ComponentCodes {
	private static final Regex REGEX_COMPONENT_CODE = new Regex()
			.wordBoundary().digit(Repetition.minMax(1, 4))
			.letter(LetterTypes.UPPER_CASE).digit().wordBoundary();
	private static final Regex REGEX_COMPONENT_CODE_NOT_USED = new Regex()
			.wordBoundary().literal("--").letter(LetterTypes.UPPER_CASE)
			.literal("-").wordBoundary();
	private static final Regex REGEX_COMPONENT_CODE_CABINET = new Regex()
			.wordBoundary().literal("DE").digit(Repetition.times(2))
			.wordBoundary();
	private static final Regex REGEX_COMPONENT_CODE_TC = new Regex()
			.wordBoundary().literal("TC").digit(Repetition.times(2))
			.wordBoundary();
	private static final Regex[] ALL_REGEXS = new Regex[] {
			REGEX_COMPONENT_CODE, REGEX_COMPONENT_CODE_NOT_USED,
			REGEX_COMPONENT_CODE_CABINET, REGEX_COMPONENT_CODE_TC };

	private static final String DO_NO_TRANSLATE = "Component code (do not translate)";

	public boolean hasMatchIn(String text) {
		for (Regex regex : ALL_REGEXS) {
			if (regex.hasMatchIn(text)) {
				return true;
			}
		}
		return false;
	}

	public Map<Regex, String> asRegexMap() {
		Map<Regex, String> regexMap = new HashMap<>();
		for (Regex regex : ALL_REGEXS) {
			regexMap.put(regex, DO_NO_TRANSLATE);

		}
		return regexMap;
	}

//	public String getFirstIn(String englishText) {
//		String firstCode = REGEX_COMPONENT_CODE.findFirstMatchIn(englishText);
//		if (firstCode == null) {
//			firstCode = REGEX_COMPONENT_CODE_NOT_USED
//					.findFirstMatchIn(englishText);
//		}
//		if (firstCode == null) {
//			return "";
//		}
//		return firstCode;
//	}
	
	public int findLastPos(String input) {
		int lastPos=0;
		for (Regex regex : ALL_REGEXS) {
			Matcher matcher = regex.toMatcher(input);
			while (matcher.find()) {
				int foundLastPos = matcher.end();
				if (foundLastPos>lastPos) {
					lastPos=foundLastPos;
				}
			}
		}
		return lastPos;
	}
	

}

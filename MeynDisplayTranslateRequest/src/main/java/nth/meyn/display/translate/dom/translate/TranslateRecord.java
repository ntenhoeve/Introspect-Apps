package nth.meyn.display.translate.dom.translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import nth.introspect.generic.regex.Regex;
import nth.introspect.generic.regex.Repetition;
import nth.meyn.display.translate.dom.abbreviation.Abbreviations;
import nth.meyn.display.translate.dom.componentcode.ComponentCodes;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

/**
 * Adapter of the {@link CSVRecord} to add additional values
 * 
 * @author nilsth
 *
 */
public class TranslateRecord {

	private static final String NEW_LINE = "\n";
	static final String ENGLISH = "English";
	public static final Regex REGEX_NUMBERS_ONLY = new Regex().literal("-",
			Repetition.minMax(0, 1)).decimal(Repetition.oneOrMoreTimes()); // "-{0,1}\\d+";
	private static final String PROPERTY = "Property";
	private final List<String> values;

	public TranslateRecord(CSVRecord csvRecord, Abbreviations abbreviations) {
		ComponentCodes componentCodes = new ComponentCodes();
		values = new ArrayList<>();
		for (String value : csvRecord) {
			values.add(value);
		}
		String englishText = csvRecord.get(ENGLISH);

		String textToTranslate = "";
		TranslationType translationType = TranslationType.TRANSLATE_FROM_ENGLISH;
		String maxSize = "";
		String translationTips = "";

		if (englishText != null) {
			englishText = englishText.trim();
			translationTips = getTranslationTips(englishText, abbreviations,
					componentCodes);
			maxSize = getmaxSize(csvRecord);
			if (abbreviations.isAbbreviation(englishText)) {
				translationType = TranslationType.TRANSLATE_FROM_ENGLISH_ABBREVIATION;
			} else if (isNoTranslationNeeded(englishText)) {
				textToTranslate = englishText;
				translationType = TranslationType.DO_NOT_TRANSLATE;
				maxSize = "";
				translationTips = "";
			} else if (componentCodes.hasMatchIn(englishText)) {
				int lastPos = componentCodes.findLastPos(englishText);
				textToTranslate = englishText.substring(0,lastPos)+" ";
				translationType = TranslationType.TRANSLATE_AFTER_COMPONENT_CODES;
			}
		}

		values.add(textToTranslate);
		values.add(translationType.toString());
		values.add(maxSize);
		values.add(translationTips);

	}

	private String getTranslationTips(String englishText,
			Abbreviations abbreviations, ComponentCodes componentCodes) {
		Map<Regex, String> regexTips = createRegexTips(abbreviations,
				componentCodes);
		Map<String, String> tips = new TreeMap<>();

		for (Regex regex : regexTips.keySet()) {
			if (regex.hasMatchIn(englishText)) {
				String key = regex.findFirstMatchIn(englishText);
				String value = regexTips.get(regex);
				tips.put(key, value);
			}
		}
		return tips.toString().replaceAll("^\\{", "").replaceAll("\\}$", "");
	}

	private Map<Regex, String> createRegexTips(Abbreviations abbreviations,
			ComponentCodes componentCodes) {
		Map<Regex, String> regexTips = new HashMap<>();
		regexTips.putAll(abbreviations.asRegexMap());
		regexTips.putAll(componentCodes.asRegexMap());
		return regexTips;
	}

	private String getmaxSize(CSVRecord csvRecord) {
		int maxNrOfLines = getMaxNrOfLines(csvRecord);
		int maxNrOfCharachters = getMaxNrOfCharacters(csvRecord);
		StringBuilder maxSize = new StringBuilder();
		maxSize.append(maxNrOfLines);
		if (maxNrOfLines == 1) {
			maxSize.append(" line with ");
		} else {
			maxSize.append(" lines with each ");
		}
		maxSize.append(maxNrOfCharachters);
		maxSize.append(" characters.");
		return maxSize.toString();
	}

	private int getMaxNrOfCharacters(CSVRecord csvRecord) {
		String property = csvRecord.get(PROPERTY);
		if (property.contains("AlarmMessage")) {
			return 65;
		} else {
			String englishText = csvRecord.get(ENGLISH);
			return (int) (getMaxNrOfCharacters(englishText) * 1.2);
		}
	}

	private int getMaxNrOfLines(CSVRecord csvRecord) {
		String property = csvRecord.get(PROPERTY);
		if (property.contains("AlarmMessage")) {
			return 1;
		} else {
			String englishText = csvRecord.get(ENGLISH);
			if (englishText.contains(NEW_LINE)) {
				System.out.println();
			}
			return getMaxNrOfLines(englishText);
		}
	}

	private int getMaxNrOfLines(String text) {
		return StringUtils.countMatches(text, "\\n") + 1;
	}

	private int getMaxNrOfCharacters(String text) {
		String[] lines = text.split(NEW_LINE);
		int maxLength = 0;
		for (String line : lines) {
			if (line.length() > maxLength) {
				maxLength = line.length();
			}
		}
		return maxLength;
	}

	private boolean isNoTranslationNeeded(String englishText) {
		if (englishText.length() < 2) {
			return true;
		} else if (englishText == "-") {
			return true;
		} else if (englishText.matches(REGEX_NUMBERS_ONLY.toString())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return values.toString();
	}

	public List<String> getValues() {
		return values;
	}
}

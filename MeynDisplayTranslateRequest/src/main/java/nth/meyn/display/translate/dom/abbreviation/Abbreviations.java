package nth.meyn.display.translate.dom.abbreviation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import nth.reflect.util.regex.LetterTypes;
import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class Abbreviations {

	private final Map<String, String> abbreviations;
	private Regex REGEX_ABBREVIATION_CANDIDATE_INLINE = new Regex()
			.whiteSpace(Repetition.oneOrMoreTimes().reluctant())
			.letters(LetterTypes.UPPER_CASE, Repetition.minMax(2, 5))
			.literal(".", Repetition.minMax(0, 1).reluctant())
			.whiteSpace(Repetition.oneOrMoreTimes().reluctant());
	private final Regex REGEX_ABBREVIATION_CANDIDATE_SINGLE = new Regex()
			.beginOfLine().whiteSpace(Repetition.zeroOrMoreTimes().reluctant())
			.letters(Repetition.minMax(1, 3))
			.whiteSpace(Repetition.zeroOrMoreTimes().reluctant()).endOfLine();

	public Abbreviations() {
		this.abbreviations = new HashMap<>();
	}

	public void put(String abbreviationInEnglish, String fullTextInEnglish) {
		if (abbreviationInEnglish == null
				|| abbreviationInEnglish.trim().length() == 0) {
			throw new IllegalArgumentException(
					"abbreviationInEnglish is missing");
		}
		if (fullTextInEnglish == null || fullTextInEnglish.trim().length() < 2) {
			throw new IllegalArgumentException("fullTextInEnglish is missing");
		}
		abbreviations.put(getNormilizedAbbreviation(abbreviationInEnglish),
				fullTextInEnglish.trim());
	}

	private String getNormilizedAbbreviation(String abbreviationInEnglish) {
		return abbreviationInEnglish.trim().toUpperCase();
	}

	public String getFulltextInEnglish(String abbreviationInEnglish) {
		abbreviationInEnglish = getNormilizedAbbreviation(abbreviationInEnglish);
		return abbreviations.get(abbreviationInEnglish);
	}

	public boolean isAbbreviation(String abbreviationInEnglish) {
		abbreviationInEnglish = getNormilizedAbbreviation(abbreviationInEnglish);
		return abbreviations.containsKey(abbreviationInEnglish);
	}

	// public boolean isAbbreviationCanidate(String abbreviationCanidate) {
	// return (abbreviationCanidate.length() > 1
	// && abbreviationCanidate.length() < 4
	// && !isAbbreviation(abbreviationCanidate) && !abbreviationCanidate
	// .matches(TranslateRecord.REGEX_NUMBERS_ONLY.toString()));
	// }

	public Collection<String> findAbbreviationCandidates(String line) {
		Set<String> candidates = new HashSet<>();

		candidates.addAll( findCandidates( REGEX_ABBREVIATION_CANDIDATE_INLINE, line));
		candidates.addAll( findCandidates( REGEX_ABBREVIATION_CANDIDATE_SINGLE, line));
		
		Iterator<String> iterator = candidates.iterator();
		if (iterator.hasNext()) {
			String candidate = iterator.next();
			if (isAbbreviation(candidate)) {
				iterator.remove();
			}
		}
		return candidates;
	}

	private Set<String> findCandidates(Regex regex, String line) {
		Set<String> candidates = new HashSet<>();
		Matcher matcher = regex.toMatcher(line);
		while (matcher.find()) {
			String candidate = getNormilizedAbbreviation(matcher.group());
			candidates.add(candidate);
		}
		return candidates;
	}

	public Map<Regex, String> asRegexMap() {
		Map<Regex, String> map=new HashMap<>();
		for (String abbreviation : abbreviations.keySet()) {
			Regex regex=createRegex(abbreviation);
			String fullText = getFulltextInEnglish(abbreviation);
			map.put(regex, fullText);
		}
		return map;
	}

	private Regex createRegex(String abbreviation) {
		return new Regex().ignoreCase().wordBoundary().literal(abbreviation).wordBoundary();
//		return new Regex().ignoreCase().literals("\\s^", Repetition.min(1).reluctant()).literal(abbreviation).literals("\\s$",Repetition.min(1).reluctant());
	}
	
//	public Set<String> findAbbreviations(String line) {
//		Set<String> found = new HashSet<>();
//		String lineInUpperCase = line.toUpperCase();
//		for (String abbreviation : abbreviations.keySet()) {
//			Regex regex=new Regex().ignoreCase().wordBoundary().literal(abbreviation).wordBoundary();
//			if ()
//			if (lineInUpperCase.contains(abbreviationWithSpaces)) {
//				found.add(abbreviation);
//			}
//		}
//		return found;
//	}

	public int size() {
		return abbreviations.size();
	}

}

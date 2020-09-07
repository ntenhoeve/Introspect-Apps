package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.testexpression;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.text.CharacterSet;

public class InsertWhiteSpacesConverter implements StringConverter {

	private final static List<Character> SPLIT_CHARACTERS = Arrays.asList('=', '-', ',');
	private final int minNrOfWhiteSpaces;
	private final int maxNrOfWhiteSpaces;
	private final StringConverter parentConverter;

	public InsertWhiteSpacesConverter(int minNrOfWhiteSpaces, int maxNrOfWhiteSpaces, StringConverter parentConverter) {
		this.minNrOfWhiteSpaces = minNrOfWhiteSpaces;
		this.maxNrOfWhiteSpaces = maxNrOfWhiteSpaces;
		this.parentConverter = parentConverter;
	}

	public InsertWhiteSpacesConverter(int minNrOfWhiteSpaces, int maxNrOfWhiteSpaces) {
		this(minNrOfWhiteSpaces, maxNrOfWhiteSpaces, new NoChangeConverter());
	}

	@Override
	public String convert(String input) {
		String intermediateResult = parentConverter.convert(input);

		String result = insertWhiteSpaces(intermediateResult);

		return result.toString();
	}

	private String insertWhiteSpaces(String intermediateResult) {
		StringBuilder result = new StringBuilder();
		result.append(createWhiteSpaces());

		for (char ch : intermediateResult.toCharArray()) {
			if (SPLIT_CHARACTERS.contains(ch)) {
				result.append(createWhiteSpaces());
				result.append(ch);
				result.append(createWhiteSpaces());
			} else {
				result.append(ch);
			}
		}

		result.append(createWhiteSpaces());
		return result.toString();
	}

	private String createWhiteSpaces() {
		String whiteSpaces = Random.string().forCharSet(new CharacterSet(" \t"))
				.forLength(minNrOfWhiteSpaces, maxNrOfWhiteSpaces).generate();
		return whiteSpaces;
	}

}

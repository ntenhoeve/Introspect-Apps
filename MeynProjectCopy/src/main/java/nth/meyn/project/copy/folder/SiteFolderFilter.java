package nth.meyn.project.copy.folder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class SiteFolderFilter implements Predicate<Path> {

	private static final Regex NUMBER_PATTERN = new Regex().digit(Repetition.times(4));
	private static final Regex ONLY_NUMBER_PATTERN = new Regex().beginOfLine().append(NUMBER_PATTERN).endOfLine();
	private final Regex namePattern;
	
	public SiteFolderFilter() {
		namePattern = createNamePattern(NUMBER_PATTERN);
	}

	public SiteFolderFilter(String number) {
		if (isSiteFolderNumber(number)) {
			Regex numberPattern = new Regex().literal(number);
			namePattern = createNamePattern(numberPattern);
		} else {
			throw new RuntimeException("Invalid site number: " + number);
		}
	}

	private static Regex createNamePattern(Regex numberPattern) {
		Regex namePattern = new Regex()//
				.beginOfLine()//
				.append(numberPattern)//
				.literal("-")//
				.anyCharacter(Repetition.oneOrMoreTimes())//
				.endOfLine();
		return namePattern;
	}

	@Override
	public boolean test(Path path) {
		if (!Files.isDirectory(path)) {
			return false;
		}
		String name = path.getFileName().toString();
		boolean nameMatches = namePattern.hasMatchIn(name);
		return nameMatches;
	}

	public static boolean isSiteFolderNumber(String number) {
		boolean isValid = ONLY_NUMBER_PATTERN.hasMatchIn(number);
		return isValid;
	}

}

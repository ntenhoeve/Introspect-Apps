package nth.meyn.project.copy.folder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class SystemFolderFilter implements Predicate<Path> {

	private final static Regex DE_PATTERN = new Regex().ignoreCase().literal("de");
	private static final Regex NUMBER_PATTERN = new Regex().digit(Repetition.times(4))
			.append(DE_PATTERN, Repetition.zeroOrOneTime()).digit(Repetition.times(2));
	private static final Regex ONLY_NUMBER_PATTERN = new Regex().beginOfLine().append(NUMBER_PATTERN).endOfLine();
	private final Regex namePattern;

	public SystemFolderFilter() {
		namePattern = createNamePattern(NUMBER_PATTERN);
	}

	public SystemFolderFilter(String systemNumber) {
		if (isSystemFolderNumber(systemNumber)) {
			String number = systemNumber.replaceAll(DE_PATTERN.toString(), "");
			Regex numberPattern = new Regex().literal(number);
			namePattern = createNamePattern(numberPattern);
		} else {
			throw new RuntimeException("Invalid system number: " + systemNumber);
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

	public static boolean isSystemFolderNumber(String number) {
		boolean isValid = ONLY_NUMBER_PATTERN.hasMatchIn(number);
		return isValid;
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

}

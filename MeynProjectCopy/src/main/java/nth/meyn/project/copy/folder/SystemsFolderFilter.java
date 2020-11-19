package nth.meyn.project.copy.folder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;

public class SystemsFolderFilter implements Predicate<Path> {

	public static Regex REGEX_SYSTEMS=new Regex().ignoreCase().literal("systems");
	public static Regex REGEX_SYSTEMEN=new Regex().ignoreCase().literal("systemen");
	public static List<Regex> REGEX_LIST=Arrays.asList(REGEX_SYSTEMS, REGEX_SYSTEMEN);
	public static Regex REGEX=new Regex().beginOfLine() .or(REGEX_LIST).endOfLine();
	
	@Override
	public boolean test(Path path) {
		if (!Files.isDirectory(path)) {
			return false;
		}
		String name = path.getFileName().toString();
		boolean nameMatches=REGEX.hasMatchIn(name);
		return nameMatches;
	}

}

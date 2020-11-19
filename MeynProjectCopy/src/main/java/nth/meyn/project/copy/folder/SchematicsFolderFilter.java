package nth.meyn.project.copy.folder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;

public class SchematicsFolderFilter implements Predicate<Path> {

	public static Regex REGEX_SCHEMATICS=new Regex().ignoreCase().literal("schematics");
	public static Regex REGEX_TEKENINGEN=new Regex().ignoreCase().literal("tekeningen");
	public static List<Regex> REGEX_LIST=Arrays.asList(REGEX_SCHEMATICS, REGEX_TEKENINGEN);
	public static Regex REGEX=new Regex().beginOfLine() .or(REGEX_LIST).endOfLine();
	
	@Override
	public boolean test(Path path) {
		if (!Files.isDirectory(path)) {
			return false;
		}
		String name = path.getFileName().toString();
		boolean nameMatches = REGEX.hasMatchIn(name);
		return nameMatches;
	}

}

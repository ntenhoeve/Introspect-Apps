package nth.meyn.project.copy.folder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;

public class SoftwareFolderFilter implements Predicate<Path> {

	public static Regex REGEX_SOFTWARE=new Regex().ignoreCase().beginOfLine(). literal("software").endOfLine();
	
	@Override
	public boolean test(Path path) {
		if (!Files.isDirectory(path)) {
			return false;
		}
		String name = path.getFileName().toString();
		boolean nameMatches = REGEX_SOFTWARE.hasMatchIn(name);
		return nameMatches;
	}

}

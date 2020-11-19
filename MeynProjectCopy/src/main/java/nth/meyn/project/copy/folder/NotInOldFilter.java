package nth.meyn.project.copy.folder;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.regex.Regex;

/**
 * Filters all paths that has the name old or oud in the path name
 * @author nilsth
 *
 */
public class NotInOldFilter implements Predicate<Path> {

	public static Regex REGEX_OLD=new Regex().ignoreCase().literal("old");
	public static Regex REGEX_OUD=new Regex().ignoreCase().literal("oud");
	public static List<Regex> REGEX_LIST=Arrays.asList(REGEX_OLD, REGEX_OUD);
	public static Regex REGEX=new Regex().beginOfLine() .or(REGEX_LIST).endOfLine();
	
	
	@Override
	public boolean test(Path path) {
		for (int i=0; i<path.getNameCount();i++) {
			String name=path.getName(i).getFileName().toString();
			if (REGEX.hasMatchIn(name)) {
				return false;
			}
		}
		return true;
	}

}

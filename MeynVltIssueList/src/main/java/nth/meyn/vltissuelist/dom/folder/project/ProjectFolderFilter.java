package nth.meyn.vltissuelist.dom.folder.project;

import java.io.File;
import java.io.FileFilter;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class ProjectFolderFilter implements FileFilter {

	private final Regex regex;

	public ProjectFolderFilter() {
		regex = new Regex().beginOfLine().decimal(Repetition.times(6)).whiteSpace(Repetition.zeroOrMoreTimes()).literal("-");
	}

	public ProjectFolderFilter(String electricalSchematic) {
		Regex electricalSchematicRegex = new Regex().ignoreCase().beginOfLine()
				.decimal(Repetition.times(4))
				.literal(".", Repetition.onceOrNotAtAll())
				.literal("DE", Repetition.onceOrNotAtAll())
				.decimal(Repetition.times(2));
		if (electricalSchematic == null
				|| !electricalSchematicRegex.hasMatchIn(electricalSchematic)) {
			throw new IllegalArgumentException("Illegal electricSchematic nr: "+electricalSchematic);
		}
		String panelNr = electricalSchematic.toUpperCase().replace(".DE", "").replace("DE", "")
				.substring(0, 6);
		regex = new Regex().beginOfLine().literal(panelNr).whiteSpace(Repetition.zeroOrMoreTimes()).literal("-");
	}

	public boolean accept(File file) {
		return file.isDirectory() && regex.hasMatchIn(file.getName());
	}

}

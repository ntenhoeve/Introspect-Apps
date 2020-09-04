package nth.meyn.vltissuelist.dom.folder.plc;

import java.io.File;
import java.io.FileFilter;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class PlcFolderFilter implements FileFilter {

	private final Regex regex;

	public PlcFolderFilter() {
		regex = new Regex().ignoreCase().beginOfLine().literals("a-z\\s", Repetition.minMax(2, 15))
				.digit(Repetition.times(2)).literal("-").literals("0-9a-z", Repetition.times(1));
	}

	@Override
	public boolean accept(File file) {
		return file.isDirectory() && regex.hasMatchIn(file.getName());
	}

}

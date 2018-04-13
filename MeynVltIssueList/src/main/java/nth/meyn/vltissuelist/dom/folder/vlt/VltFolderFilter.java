package nth.meyn.vltissuelist.dom.folder.vlt;

import java.io.File;
import java.io.FileFilter;

public class VltFolderFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		return file.isDirectory() && file.getName().toLowerCase().contains("vlt");
	}

}

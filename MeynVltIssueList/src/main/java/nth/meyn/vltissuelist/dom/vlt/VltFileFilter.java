package nth.meyn.vltissuelist.dom.vlt;

import java.io.File;
import java.io.FileFilter;

public class VltFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		return file.isFile() && file.getName().toLowerCase().endsWith(".ssp");
	}

}

package nth.meyn.vltissuelist.dom.folder.software;

import java.io.File;
import java.io.FileFilter;

public class SoftwareFolderFilter implements FileFilter {

	public boolean accept(File file) {
		String fileName = file.getName().toLowerCase(); 
		return file.isDirectory() && (fileName.equals("software")) ;
	}

}

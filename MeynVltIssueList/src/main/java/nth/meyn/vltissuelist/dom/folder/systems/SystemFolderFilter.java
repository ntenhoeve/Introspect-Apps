package nth.meyn.vltissuelist.dom.folder.systems;

import java.io.File;
import java.io.FileFilter;

public class SystemFolderFilter implements FileFilter {

	public boolean accept(File file) {
		String fileName = file.getName().toLowerCase(); 
		return file.isDirectory() && (fileName.equals("systemen") || fileName.equals("systems")) ;
	}

}

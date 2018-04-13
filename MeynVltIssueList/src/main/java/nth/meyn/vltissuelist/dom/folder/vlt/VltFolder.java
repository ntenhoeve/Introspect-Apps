package nth.meyn.vltissuelist.dom.folder.vlt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nth.meyn.vltissuelist.dom.issue.VltReport;
import nth.meyn.vltissuelist.dom.issue.VltReportImpl;
import nth.meyn.vltissuelist.dom.vlt.VltFile;
import nth.meyn.vltissuelist.dom.vlt.VltFileFilter;

public class VltFolder {

	private File vltFolder;

	public VltFolder(File vltFolder) {
		this.vltFolder = vltFolder;
	}
	
	public List<VltFile> getVltFiles() throws IOException {
		File[] foundFiles = vltFolder.listFiles(new VltFileFilter());
		if (foundFiles.length==0) {
			throw new FileNotFoundException("No Vlt files found in: "+vltFolder.getPath());
		}
		List<VltFile> vltFiles=new ArrayList<>();
		for (File foundFile : foundFiles) {
			VltFile vltFile = new VltFile(foundFile);
			vltFiles.add(vltFile);
		}
		return vltFiles;
	}
	
	public VltReport getVltreport() throws IOException {
		List<VltFile> vltFiles = getVltFiles();
		VltReportImpl vltReport=null;
		for (VltFile vltFile : vltFiles) {
			if (vltReport==null) {
				vltReport=new VltReportImpl(vltFile);
			} else {
				vltReport.and(vltFile);
			}
		}
		return vltReport;
	}

	
	public File getPath() {
		return vltFolder;
	}

}

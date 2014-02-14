package nth.playlistmanager.domain;

import java.io.File;

public class Source {
	private File fileOrFolder;

	public Source() {
	}
	
	public Source(File fileOrFolder) {
		this.fileOrFolder = fileOrFolder;
	}

	public File getFileOrFolder() {
		return fileOrFolder;
	}

	public void setFileOrFolder(File fileOrFolder) {
		this.fileOrFolder = fileOrFolder;
	}
}

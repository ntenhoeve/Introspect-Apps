package nth.playlistmanager.domain;

import java.io.File;

public class SourceAndDestination {
	private File sourceFolder;
	private File destinationFolder;

	public File getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(File sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public File getDestinationFolder() {
		return destinationFolder;
	}

	public void setDestinationFolder(File destinationFolder) {
		this.destinationFolder = destinationFolder;
	}

}

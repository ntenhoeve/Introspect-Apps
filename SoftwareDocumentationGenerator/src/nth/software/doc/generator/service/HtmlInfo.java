package nth.software.doc.generator.service;

import java.io.File;

public class HtmlInfo extends DocumentationInfo {

	private File destinationFolder;

	public File getDestinationFolder() {
		return destinationFolder;
	}

	public void setDestinationFolder(File destinationFolder) {
		this.destinationFolder = destinationFolder;
	}
}

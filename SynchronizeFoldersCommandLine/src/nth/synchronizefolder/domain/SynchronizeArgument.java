package nth.synchronizefolder.domain;

import java.io.File;

import nth.introspect.layer5provider.domain.info.valuemodel.annotations.OrderInForm;

public class SynchronizeArgument {
	private File source;
	private File destination;
	private boolean removeOldFiles;
	private boolean skipSameNameAndSize;

	@OrderInForm(1)
	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}
	@OrderInForm(2)
	public File getDestination() {
		return destination;
	}

	public void setDestination(File destination) {
		this.destination = destination;
	}

	@OrderInForm(3)
	public boolean isRemoveOldFiles() {
		return removeOldFiles;
	}

	public void setRemoveOldFiles(Boolean removeOldFiles) {
		this.removeOldFiles = removeOldFiles;
	}

	public boolean isSkipSameNameAndSize() {
		return skipSameNameAndSize;
	}

	public void setSkipSameNameAndSize(Boolean skipSameNameAndSize) {
		this.skipSameNameAndSize = skipSameNameAndSize;
	}


	
}

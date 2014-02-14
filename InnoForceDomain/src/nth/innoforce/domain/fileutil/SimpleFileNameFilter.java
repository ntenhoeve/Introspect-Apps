package nth.innoforce.domain.fileutil;

import java.io.File;
import java.io.FileFilter;

public class SimpleFileNameFilter implements FileFilter {


	private final String partialName;

	public SimpleFileNameFilter(String partialName) {
		this.partialName = partialName.toLowerCase();
	}

	@Override
	public boolean accept(File file) {
		return file.getName().toLowerCase().contains(partialName);
	}

}

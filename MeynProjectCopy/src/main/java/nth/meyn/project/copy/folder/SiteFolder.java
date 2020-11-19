package nth.meyn.project.copy.folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class SiteFolder extends Folder {

	public SiteFolder(Path path) {
		super(path);
	}

	public Optional<SystemsFolder> getSystemsFolder() {
		try {
			Optional<SystemsFolder> result = Files.walk(getPath(),0)//
					.filter(new SystemsFolderFilter())//
					.map(p -> new SystemsFolder(p)).findFirst();
			return result;
		} catch (IOException e) {
			return Optional.empty();
		}
	}

	public void copy(Path destinationPath) {
		System.out.println("Site Folder: "+getPath());
	}

	public Optional<SystemFolder> getSystemFolder(String systemNumber) {
		Optional<SystemsFolder> optionalSystemsFolder = getSystemsFolder();
		if (optionalSystemsFolder.isPresent()) {
			SystemsFolder systemsFolder=optionalSystemsFolder.get();
			Optional<SystemFolder> optionalSystemFolder = systemsFolder.getSystemFolder(systemNumber);
			return optionalSystemFolder;
		}
		return Optional.empty();
	}


}

package nth.meyn.project.copy.folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class SystemFolder extends Folder {

	public SystemFolder(Path path) {
		super(path);
	}

	public Optional<SchematicsFolder> getSchematicsFolder() {
		try {
			Optional<SchematicsFolder> result = Files.walk(getPath(), 0)//
					.filter(new SchematicsFolderFilter())//
					.map(p -> new SchematicsFolder(p))//
					.findFirst();
			return result;
		} catch (IOException e) {
			return Optional.empty();
		}
	}

	public Optional<SoftwareFolder> getSoftwareFolder() {
		try {
			Optional<SoftwareFolder> result = Files.walk(getPath(), 0)//
					.filter(new SoftwareFolderFilter())//
					.map(p -> new SoftwareFolder(p))//
					.findFirst();
			return result;
		} catch (IOException e) {
			return Optional.empty();
		}
	}

	public void copy(Path destinationPath) {
		System.out.println("Systems Folder: "+getPath());
	}

}

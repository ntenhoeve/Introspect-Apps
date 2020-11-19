package nth.meyn.project.copy.folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SoftwareFolder extends Folder {

	public SoftwareFolder(Path path) {
		super(path);
	}

	public List<Path> getPlcProjectFoldersAndFiles() {
		try {
			List<Path> results = Files.walk(getPath())//
					.filter(new NotInOldFilter())//
					.collect(Collectors.toList());
			return results;
		} catch (IOException e) {
			return new ArrayList<>();
		}

	}

}

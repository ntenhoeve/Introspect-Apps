package nth.meyn.project.copy.folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchematicsFolder extends Folder {

	public SchematicsFolder(Path path) {
		super(path);
	}

	public List<Path> getPdfFiles() {
		try {
			List<Path> results = Files.walk(getPath(),0)//
					.filter(new PdfFileFilter())//
					.collect(Collectors.toList());
			return results;
		} catch (IOException e) {
			return new ArrayList<>();
		}

	}

}

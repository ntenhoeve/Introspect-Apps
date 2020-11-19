package nth.meyn.project.copy.folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SystemsFolder extends Folder {

	public SystemsFolder(Path path) {
		super(path);
	}

	public List<SystemFolder> getSystemsFolders() {
		try {
			List<SystemFolder> systemFolders = Files.walk(getPath(),0)//
					.filter(new SystemFolderFilter())//
					.map(p -> new SystemFolder(p))//
					.collect(Collectors.toList());
			return systemFolders;
		} catch (IOException e) {
			return new ArrayList<>();
		}
	}

	/**
	 * 
	 * @param systemNumber can be site number (4 digits) + system number (2 digits), e.g. 123456 or site number (4 digits) + DE+ system number (2 digits), e.g. 1234DE56 
	 * @return
	 */
	public Optional<SystemFolder> getSystemFolder(String systemNumber) {
		try {
			Optional<SystemFolder> result = Files.walk(getPath(), 0)//
					.filter(new SystemFolderFilter(systemNumber))//
					.map(p -> new SystemFolder(p))//
					.findFirst();
			return result;
		} catch (Throwable e) {
			return Optional.empty();
		}
	}
}

package nth.meyn.project.copy.folder;

import java.nio.file.Path;

public abstract class Folder {

	private final Path path;

	public Folder(Path path) {
		this.path = path;
	}
	
	public Path getPath() {
		return path;
	}
	
//	private void copyFolderContent(Predicate<Path> filter, Path destination ) throws IOException {
//		List<Path> sources = Files.walk(path).filter(filter).collect(Collectors.toList());
//		copyFiles( sources,destination);
//	}
//	
//	private void copyFolderContent(Path destination) throws IOException {
//		List<Path> sources = Files.walk(path).collect(Collectors.toList());
//		copyFiles( sources,destination);
//	}
//
//	
//	private void copyFiles(List<Path>  sources, Path destination) throws IOException {
//		List<Path> destinations = sources.stream()//
//				.map(path::relativize)//
//				.map(destination::resolve)//
//				.collect(Collectors.toList());
//
//		for (int i = 0; i < sources.size(); i++) {
//			Files.copy(sources.get(i), destinations.get(i));
//		}
//	}
}

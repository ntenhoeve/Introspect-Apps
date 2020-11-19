package nth.meyn.project.copy.folder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class PdfFileFilter implements Predicate<Path> {

	@Override
	public boolean test(Path path) {
		if (!Files.isRegularFile(path)) {
			return false;
		}
		boolean hasPdfExtention = path.toString().toLowerCase().endsWith(".pdf") ;
		return hasPdfExtention;
	}

}

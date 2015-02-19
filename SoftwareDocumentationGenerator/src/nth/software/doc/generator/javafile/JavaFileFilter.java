package nth.software.doc.generator.javafile;

import java.io.File;
import java.io.FileFilter;

public class JavaFileFilter implements FileFilter{

	private static final String JAVA_EXTENSION = ".java";
	private final String fileNameToFind;

	public JavaFileFilter (String className) {
		fileNameToFind=className+JAVA_EXTENSION;
	}

	@Override
	public boolean accept(File file) {
		return file.isFile() && file.getName().equals(fileNameToFind);
	}

}

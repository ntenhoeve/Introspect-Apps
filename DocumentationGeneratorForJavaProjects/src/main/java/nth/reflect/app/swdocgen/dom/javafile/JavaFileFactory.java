package nth.reflect.app.swdocgen.dom.javafile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JavaFileFactory {

	public static Map<String, JavaFile> findAllJavaFilesInFolder(File folder) {
		Map<String, JavaFile> foundFiles = new HashMap<>();
		File[] children = folder.listFiles();
		if (children != null) {
			for (File child : children) {
				if (child.isDirectory()) {
					foundFiles.putAll(findAllJavaFilesInFolder(child));
				} else if (JavaFile.isJavaFile(child)) {
					JavaFile javaFile = new JavaFile(child);
					String name = javaFile.getNameWithoutExtention();
					foundFiles.put(name, javaFile);
				}
			}
		}
		return foundFiles;
	}
}

package nth.software.doc.generator.javafile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class JavaFileFactory {

	private static List<File> findAllJavaFiles(File folder, String className) {
		List<File> results=new ArrayList<>();
		JavaFileFilter javaFileFilter = new JavaFileFilter(className);
		 File[] foundFiles = folder.listFiles(javaFileFilter);
		if (foundFiles.length==0) {
			DirectoryFilter directoryFilter=new DirectoryFilter();
			File[] subFolders = folder.listFiles(directoryFilter);
			for (File subFolder : subFolders) {
				results.addAll( findAllJavaFiles(subFolder,className));
			}
		} else {
			for (File foundFile : foundFiles) {
				results.add(foundFile);
			}
		}
		return results;
	}

	private static File findSingleJavaFile(File folder,
			String className) throws FileNotFoundException, MultipleFileException {
		 List<File> allFoundJavaFiles = findAllJavaFiles(folder, className);
		if (allFoundJavaFiles.size()==0) {
			throw new FileNotFoundException("Could not find file: "+className+".java");
		}
		if (allFoundJavaFiles.size()>1) {
			throw new MultipleFileException("Found multuple files with the same name: "+className+".java");
		}
		return allFoundJavaFiles.get(0);
	}

	public static JavaFile create(File projectsFolder, String className) throws FileNotFoundException, MultipleFileException {
		File file=findSingleJavaFile(projectsFolder, className);
		JavaFile javaFile=new JavaFile(file);
		return javaFile;
	}
}

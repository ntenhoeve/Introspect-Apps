package nth.software.doc.generator.factory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import nth.software.doc.generator.javafile.MultipleFileException;
import nth.software.doc.generator.model.Manual;

public class DocumentationFactory {
	public static Manual createManual(File projectsFolder, String className)
			throws IOException, MultipleFileException {

		String documentation=DocumentationReader.readAllDocumentation(projectsFolder, className);
		
		System.out.println(documentation);
		Manual manual = new Manual();
		return manual;//TODO addToManual(manual, projectsFolder, contentsFile);
	}


//	private static Manual addToManual(String documentation) throws IOException {
//		
//		DocParser docParser=new DocParser(documentation);
//		
//		
//		
//		for (String line : documentation) {
//			System.out.println(line);
//		}
//
//		return manual;
//	}
	
	
	public static void main(String[] args) throws IOException, MultipleFileException {
		File projectsFolder = new File("M:/My Git/Introspect-Framework");
		createManual(projectsFolder, "IntrospectManual");
	}
}

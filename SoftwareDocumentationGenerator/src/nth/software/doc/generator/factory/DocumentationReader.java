package nth.software.doc.generator.factory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import nth.software.doc.generator.javafile.JavaFile;
import nth.software.doc.generator.javafile.JavaFileFactory;
import nth.software.doc.generator.javafile.MultipleFileException;
import nth.software.doc.generator.regex.Regex;
import nth.software.doc.generator.regex.Repetition;
import nth.software.doc.generator.service.DocumentationInfo;

public class DocumentationReader {


	
	private static final Regex INSERT_TAG_BEGIN = new Regex().literal("{@insert").whiteSpace(Repetition.oneOrMoreTimes());
	private static final Regex INSERT_TAG_CLASSNAME =  new Regex().literals("a-zA-Z", Repetition.oneOrMoreTimes()).wordChar(Repetition.zeroOrMoreTimes());  
	private static final Regex INSERT_TAG_END = new Regex().whiteSpace(Repetition.zeroOrMoreTimes()).literal("}"); 
	private static final Regex INSERT_TAG=new Regex().append(INSERT_TAG_BEGIN).append(INSERT_TAG_CLASSNAME).append( INSERT_TAG_END);
	
	public static String readAllDocumentation(DocumentationInfo documentationInfo) throws IOException, MultipleFileException {
		return readAllDocumentation(documentationInfo.getProjectsFolder(), documentationInfo.getClassName());
	}
	
	public static String readAllDocumentation(File projectsFolder, String className) throws IOException, MultipleFileException {
		JavaFile javaFile = JavaFileFactory.create(projectsFolder, className);

		String documentation = javaFile.getDocumentation();
		
		documentation=insertDocumentation(projectsFolder, documentation);
		
		return documentation;
	}

	private static String insertDocumentation(File projectsFolder, String documentation) throws MultipleFileException, IOException {
		Parser parser=new Parser(documentation);
		List<String> insertTags=parser.findAll(INSERT_TAG);
		
		for (String insertTag : insertTags) {
			String classNameFromInsertTag=getClassNameFromInsertTag(insertTag);
			
			String documentationToInsert = readAllDocumentation(projectsFolder, classNameFromInsertTag);//recursive call
			
			documentation=documentation.replace(insertTag, documentationToInsert);
		}
		return documentation;
	}

	private static String getClassNameFromInsertTag(String insertTag) {
		String insertTagWithoutHeader = insertTag.replaceAll(INSERT_TAG_BEGIN.toString(), "");
		String className=insertTagWithoutHeader.replaceAll(INSERT_TAG_END.toString(), "");
		return className;
	}

}

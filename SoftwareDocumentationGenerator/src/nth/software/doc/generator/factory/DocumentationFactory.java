package nth.software.doc.generator.factory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import nth.software.doc.generator.framer.ConsoleFramer;
import nth.software.doc.generator.framer.DocumentationFramer;
import nth.software.doc.generator.framer.HtmlSingleFileFramer;
import nth.software.doc.generator.javafile.MultipleFileException;
import nth.software.doc.generator.model.DocumentationModel;
import nth.software.doc.generator.model.Manual;
import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;

public class DocumentationFactory {
	public static void createDocumentation(File projectsFolder, String className)
			throws IOException, MultipleFileException {

		String javaDoc=DocumentationReader.readAllDocumentation(projectsFolder, className);
		
//		System.out.println(javaDoc);
		
		JavaDocTokenizer javaDocTokenizer=new JavaDocTokenizer(javaDoc);
		while (javaDocTokenizer.hasNextToken())		{
			FoundToken foundToken = javaDocTokenizer.nextToken();
			System.out.println(foundToken);
		}
		
		JavaDocParser javaDocParser=new JavaDocParser(javaDoc); 
		List<Node> nodes = javaDocParser.parse();
		DocumentationModel documentationModel=new DocumentationModel(projectsFolder, nodes);
		

//		ConsoleFramer framer=new ConsoleFramer();
//		framer.frame(nodes);
		
		File destinationFile=new File("C:/Users/nilsth/My Git/Introspect-Apps/SoftwareDocumentationGenerator/dist/htmlOutput.html");
		HtmlSingleFileFramer htmlSingleFileFramer=new HtmlSingleFileFramer(documentationModel, destinationFile);
		htmlSingleFileFramer.frame();
	}

	public static void main(String[] args) throws IOException, MultipleFileException {
		File projectsFolder = new File("M:/My Git/Introspect-Framework");
		createDocumentation(projectsFolder, "IntrospectDocumentation");
	}
}

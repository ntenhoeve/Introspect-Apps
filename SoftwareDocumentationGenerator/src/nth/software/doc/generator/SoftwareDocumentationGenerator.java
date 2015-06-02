package nth.software.doc.generator;

import java.util.Arrays;
import java.util.List;

import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.software.doc.generator.repository.GitRepository;
import nth.software.doc.generator.service.DocumentationService;

/**
 * The {@link SoftwareDocumentationGenerator} is a
 * {@link IntrospectApplicationForCommandLine} which will parse JavaDoc of a
 * given class to generate different styles of documents (see
 * {@link DocumentationService}).
 * 
 * <h3>The advantages</h3>
 * <ul>
 * <li>All your project documentation will be consistent (JavaDoc, manual, web
 * site and Wiki)</li>
 * <li>All your project documentation is directly available to the developer in
 * the form of JavaDoc</li>
 * 
 * <li>Basic formating can be used with HTML in JavaDoc</li>
 * <li>GitHub Html page and or Wiki pages can automatically be committed
 * (uploaded)</li>
 * <li>All your project documentation is spelling checked and grammar checked
 * when during editing (basic functionality of most EDI's)</li>
 * </ul>
 * 
 * <h3>Conventions</h3>
 * <ul>
 * <li>You can use images with the HTM tag &lt;image href="pictureName.png"&gt;.
 * This image must be located in the same package.</li>
 * <li>You can import other class or interface files using the Javadoc in-line
 * tag &lt;@insert ClassName&gt;</li>
 * <li>It is good practice to create a documentation class that ties all the
 * java files together in one document.</li>
 * </ul>
 * 
 * <h3>Usage</h3>
 * <p>
 * To run this application run the {@link SoftwareDocumentationGenerator} class
 * with your IDE (or create a Jar and run it from the command line with Java
 * -jar {@link SoftwareDocumentationGenerator}.jar). You will need to provide
 * several parameters, starting with a method name of one of the public methods
 * in the {@link DocumentationService} class, followed by the properties of the
 * method parameter.
 * </p>
 * In example if you want to create HTML documentation you will need to run:
 * 
 * <pre>
 * {@link SoftwareDocumentationGenerator}: createHtmlDocumentation {location of the java projects} {class name} {destination folder}
 * </pre>
 * 
 * In example: To generate Html documentation from the
 * {@link SoftwareDocumentationGenerator} project itself, run the
 * {@link SoftwareDocumentationGenerator} class with the following parameters:
 * 
 * <pre>
 * {@link SoftwareDocumentationGenerator}: createHtmlDocumentation "c:\My Workspace\" SoftwareDocumentationGenerator "c:\Temp\SoftwareDocumentationGenerator\HtmlDocumentation"
 * </pre>
 * 
 * <h3>Documentation Types</h3> {@insert DocumentationService}
 * 
 * @author nilsth
 */
public class SoftwareDocumentationGenerator extends
		IntrospectApplicationForCommandLine {

	public SoftwareDocumentationGenerator(String[] commandLineArguments) {
		super(commandLineArguments);
	}

	public static void main(String[] commandLineArguments) {
		new SoftwareDocumentationGenerator(commandLineArguments);
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(DocumentationService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(GitRepository.class);
	}

}

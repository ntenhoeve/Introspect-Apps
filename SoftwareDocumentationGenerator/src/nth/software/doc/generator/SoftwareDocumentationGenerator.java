package nth.software.doc.generator;

import java.util.Arrays;
import java.util.List;

import nth.introspect.generic.util.ClassList;
import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.software.doc.generator.model.Chapter;
import nth.software.doc.generator.model.SubChapter;
import nth.software.doc.generator.model.SubSubChapter;
import nth.software.doc.generator.repository.GitRepository;
import nth.software.doc.generator.service.DocumentationService;

/**
 * The {@link SoftwareDocumentationGenerator} is a
 * {@link IntrospectApplicationForCommandLine} which will parse <a
 * href="http://en.wikipedia.org/wiki/Javadoc">JavaDoc</a> of a given class to
 * generate different styles of documents (see {@link DocumentationService}).
 * The {@link SoftwareDocumentationGenerator} will only use the class or
 * interface description (the java doc before the class or interface keyword).
 * It will ignore JavaDoc of fields, and methods
 * 
 * <h3>The advantages</h3>
 * <ul>
 * <li>All your project documentation will be consistent (JavaDoc, manual, web
 * site and Wiki)</li>
 * <li>All your project documentation is directly available to the developer in
 * the form of JavaDoc</li>
 * <li>Basic formating can be used with HTML in JavaDoc</li>
 * <li>GitHub Html page and or Wiki pages can automatically be committed
 * (uploaded)</li>
 * <li>All your project documentation is spelling checked and grammar checked
 * when during editing (basic functionality of most EDI's)</li>
 * </ul>
 * 
 * <h3>Conventions</h3>
 * <ul>
 * <li>You can use images with the HTM tag &lt;img href="pictureName.png"&gt;.
 * This image must be located in the same package.</li>
 * <li>You can include a main picture, which will be added at the beginning of
 * the documentation. This image must have the same name as the class name, have
 * the png extension and be located in the same package.This .</li>
 * <li>You can insert other class or interface files using the Javadoc in-line
 * tag &lt;@insert ClassName&gt;</li>
 * <li>You can include {@link Chapter}s by adding a title between &lt;H1&gt; &lt;/H1&gt;
 * tags</li>
 * <li>You can include {@link SubChapter}s by adding a title between &lt;H2&gt;
 * &lt;/H2&gt; tags</li>
 * <li>You can include {@link SubSubChapter}s by adding a title between &lt;H3&gt;
 * &lt;/H3&gt; tags. Sub {@link SubSubChapter}s are normally not rendered in the table of
 * contents</li>
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
 * In example: If you want to create HTML documentation you will need to run:
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
		return new ClassList(DocumentationService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ClassList(GitRepository.class);
	}

}

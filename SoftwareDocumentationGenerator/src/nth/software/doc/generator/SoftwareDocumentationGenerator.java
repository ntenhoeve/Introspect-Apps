package nth.software.doc.generator;

import java.util.Arrays;
import java.util.List;

import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.software.doc.generator.model.Chapter;
import nth.software.doc.generator.model.SubChapter;
import nth.software.doc.generator.model.SubSubChapter;
import nth.software.doc.generator.repository.GitRepository;
import nth.software.doc.generator.service.DocumentationService;

 TODO replace this project with DocumentationGeneratorForJavaProjects

public class SoftwareDocumentationGenerator extends
		IntrospectApplicationForCommandLine {


	public static void main(String[] commandLineArguments) {
		launch(commandLineArguments);
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

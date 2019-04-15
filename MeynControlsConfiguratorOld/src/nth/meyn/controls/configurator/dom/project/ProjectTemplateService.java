package nth.meyn.controls.configurator.dom.project;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;

public class ProjectTemplateService {

	public List<ProjectTemplate> allProjectTemplates() {
		List<ProjectTemplate> projectTemplates = new ArrayList<>();
		projectTemplates.add(ProjectTemplateExampleFactory.create());
		return projectTemplates;
	}

	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public ProjectTemplate viewProjectTemplate(ProjectTemplate projectTemplate) {
		return projectTemplate;
	}

}

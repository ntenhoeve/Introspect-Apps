package nth.meyn.controls.configurator.dom.project;

import java.util.ArrayList;
import java.util.List;

public class ProjectTemplateService {

	public List<ProjectTemplate> allProjectTemplates() {
		List<ProjectTemplate> projectTemplates = new ArrayList<>();
		projectTemplates.add(ProjectTemplateExampleFactory.create());
		return projectTemplates;
	}

	public ProjectTemplate viewProjectTemplate(ProjectTemplate projectTemplate) {
		return projectTemplate;
	}

}

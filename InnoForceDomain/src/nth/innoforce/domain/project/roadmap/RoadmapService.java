package nth.innoforce.domain.project.roadmap;

import java.util.ArrayList;
import java.util.List;

import nth.innoforce.domain.project.Project;
import nth.innoforce.domain.project.ProjectDataAccess;
import nth.introspect.Introspect;
import nth.introspect.container.inject.annotation.Inject;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.util.CloneUtil;

public class RoadmapService {

@Inject
private ProjectDataAccess projectDataAccess;
	
	@GenericReturnType(ProjectConcept.class)
	public List<ProjectConcept> eviscerationRoadmap() {
		//TODO create a roadmap object that contains project concepts and overall expected intake over the years?
		List<Project> projects = projectDataAccess.getAll();//TODO add creteria: projectStage=ProjectConsept && start project
		List<ProjectConcept> projectConcepts =new ArrayList<ProjectConcept>();
		
		for (Project project:projects) {
//			if (project.getStage()!=null && project.getStage()==ProjectStage.ProjectConcept ) {
			ProjectConcept projectConcept=(ProjectConcept) CloneUtil.clone(project, new ProjectConcept());	
			projectConcepts.add(projectConcept);
//			}
		}//TODO order on start project?
		return projectConcepts;
	}


	@ExecutionMode(ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL)
	public void modify(ProjectConcept projectConcept) {
		Project project=projectConcept;
		projectDataAccess.set(project);
	}
	
	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void delete(ProjectConcept projectConcept) {
		projectDataAccess.delete(projectConcept);
	}

	


}

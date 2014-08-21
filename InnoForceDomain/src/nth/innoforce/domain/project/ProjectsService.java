package nth.innoforce.domain.project;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import nth.innoforce.domain.resource.Resource;
import nth.introspect.container.inject.annotation.Inject;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class ProjectsService {

	private final ProjectRepository projectRepository;
	private final AuthorizationProvider authorizationProvider;
	
	public ProjectsService(AuthorizationProvider authorizationProvider, ProjectRepository projectRepository){
		this.authorizationProvider = authorizationProvider;
		this.projectRepository = projectRepository;
	}
	
	// TODO this method was created for testing purposes. remove when no longer needed
	@ExecutionMode(ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyGibletHarvesterProject(Project project) {
		projectRepository.set(project);
	}
	
	// TODO this method was created for testing purposes. remove when no longer needed
	public Project modifyGibletHarvesterProjectParameterFactory() {
		return projectRepository.getGibletHarvesterProject();
	}


	@GenericReturnType(Project.class)
	public List<Project> allProjects() {
		return projectRepository.getAll();
	}

	@GenericReturnType(Project.class)
	public List<Project> scheduledProjectsForUpcommingGateMeeting() {//make query class GateMeetingDate
		//TODO result should include projects that do not have a latestGateScore and projects where nextGateMeetingDate is null or equal or later than given gatemeetingdate
		throw new RuntimeException("This methos needs to be implemented");
	}
	

	@GenericReturnType(Project.class)
	public List<Project> projectsInIdeationStage() {

		return null;
	}

	@GenericReturnType(Project.class)
	public List<Project> projectsInCreativeStage() {
		return null;
	}

	@GenericReturnType(Project.class)
	public List<Project> projectsInDefinitionStage() {
		return null;
	}

	@GenericReturnType(Project.class)
	public List<Project> projectsIboPrimaryProcessing() {
		return null;
	}

	@GenericReturnType(Project.class)
	public List<Project> projectsIboSecondaryProcessing() {
		return null;
	}

	@GenericReturnType(Project.class)
	public List<Project> projectsIboWeighingGradingLogisticsAndIT() {
		return null;
	}

	@GenericReturnType(Project.class)
	public List<Project> projectsIboNewBusinessDevelopment() {
		return null;
	}

	@GenericReturnType(Project.class)
	public List<Project> projectsInLaunchStage() {
		return null;
	}

	@GenericReturnType(Project.class)
	public List<Project> projectsInSalesStage() {
		return null;
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public Project viewProject(Project project) {
		return project;
	}

	@ExecutionMode(ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyProject(Project project) {
	}

	public boolean modifyProjectEnabled() {
		return authorizationProvider.userInRole("ProcessManager");
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public URL modifyProjectInEpm(Project project) throws MalformedURLException {
		StringBuffer url = new StringBuffer("http://epm/PWA/_layouts/PWA/views/EditProjectSummary.aspx?_projectUid=");
		url.append(project.getEpmId());
		url.append("&sender=projectcenter");
		return new URL(url.toString());
	}

	@GenericReturnType(Project.class)
	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public List<Project> findProjectsOfResource(Resource resource) {
		return projectRepository.findProjectsOfResource(resource);
	}

	// TODO openBusinessCaseDocument which returns a list of files that can be opened with a FileService with the following method: public URL open(File file) {return file.toURI().toURL();}
	// TODO openHighLighDocuments which returns a list of files that can be opened with a FileService with the following method: public URL open(File file) {return file.toURI().toURL();}
	// TODO validateProject (does it have a project directory, is it in the right folder (running, finished, etc)? are the project documents available/up to date (for running projects validate on business case, highlight reports or dab documents)
}

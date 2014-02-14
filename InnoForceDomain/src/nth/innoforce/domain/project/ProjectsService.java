package nth.innoforce.domain.project;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import nth.innoforce.domain.resource.Resource;
import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class ProjectsService {

	private ProjectDataAccess getProjectDataAccess() {
		return (ProjectDataAccess) Introspect.getDataAccessProvider(ProjectDataAccess.class);
	}
	
	// TODO this method was created for testing purposes. remove when no longer needed
	@FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	public void modifyGibletHarvesterProject(Project project) {
		getProjectDataAccess().set(project);
	}
	
	// TODO this method was created for testing purposes. remove when no longer needed
	public Project modifyGibletHarvesterProjectParameterFactory() {
		return getProjectDataAccess().getGibletHarvesterProject();
	}


	@GenericReturnType(Project.class)
	public List<Project> allProjects() {
		return getProjectDataAccess().getAll();
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

	@FormMode(FormModeType.showParameterThenClose)
	public void viewProject(Project project) {
	}

	@FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	public void modifyProject(Project project) {
	}

	public boolean modifyProjectEnabled() {
		return Introspect.getAuthorizationProvider().userInRole("ProcessManager");
	}

	@FormMode(FormModeType.executeMethodDirectly)
	public URL modifyProjectInEpm(Project project) throws MalformedURLException {
		StringBuffer url = new StringBuffer("http://epm/PWA/_layouts/PWA/views/EditProjectSummary.aspx?_projectUid=");
		url.append(project.getEpmId());
		url.append("&sender=projectcenter");
		return new URL(url.toString());
	}

	@GenericReturnType(Project.class)
	@FormMode(FormModeType.executeMethodDirectly)
	public List<Project> findProjectsOfResource(Resource resource) {
		return getProjectDataAccess().findProjectsOfResource(resource);
	}

	// TODO openBusinessCaseDocument which returns a list of files that can be opened with a FileService with the following method: public URL open(File file) {return file.toURI().toURL();}
	// TODO openHighLighDocuments which returns a list of files that can be opened with a FileService with the following method: public URL open(File file) {return file.toURI().toURL();}
	// TODO validateProject (does it have a project directory, is it in the right folder (running, finished, etc)? are the project documents available/up to date (for running projects validate on business case, highlight reports or dab documents)
}

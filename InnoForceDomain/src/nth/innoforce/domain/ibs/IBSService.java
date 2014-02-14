package nth.innoforce.domain.ibs;

import java.net.URI;
import java.util.List;

import nth.innoforce.domain.project.Project;

public class IBSService {
	public URI createIBSPMinuteTemplate() {
		// TODO create a word document:

		// List on attendees (based on roles: head of R&D, Head of PSI, Innovation Manager, Head of R&D creative, Head of R&D project office, Project leaders (filterd on projects) and Project managers (filterd on projects)

		// Per gate meeting a summary of the results
		
		// Per product group a summary (project names, gate scores, priorities, gate dates and project status)
		return null;
	}
	
	public URI createIBSProjectProgressPresentation() {
		// TODO create a presentation on the project progress

		// Per project:
		//summary of project status and known dates
		// summary of progress report (from InnoForce database)

		return null;
	}
	
	public List<Project> primaryProcessingProjects() {
		//TODO
		return null;
	}
	
	public List<Project> cutUpProjects() {
		//TODO
		return null;
	}
	
	public List<Project> deboningProjects() {
		//TODO
		return null;
	}
	
	public List<Project> weighingGradingAndLogisiticsProjects() {
		//TODO
		return null;
	}
}

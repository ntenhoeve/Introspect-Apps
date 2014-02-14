package nth.innoforce.domain.ibo;

import java.net.URI;
import java.util.List;

import nth.innoforce.domain.project.Project;

public class IBOService {
	
	public URI createIBOPrimaryProcessingMinuteTemplate() {
		// TODO create a word document:

		// List on attendees (based on roles: head of R&D, Head of PSI, Innovation Manager, Head of R&D creative, Head of R&D project office, Project leaders (filterd on projects) and Project managers (filterd on projects)

		// Per project:
		// Is there a project folder in projects running? If not add warning
		// Is there a business case in the projects folder? If not add warning
		// Did the project go trough the previous gate meeting? If so add a summary of the results (from innoforce database)
		// Is there a new progress report (from InnoForce database)? If not add warning, If so, add a summary
		// Is there a red flag condition? If so add warning (to be discussed during IBS)
		// Is the project scheduled for the upcoming gate meeting? If so add a warning for the team to send deliverables on time (this project is scheduled for the upcomming IBS gate meeting in <GATE DATE>. The following deliverables need to be send to the gate keepers (ib@meyn.net) bofore <GATE DATE -1 WEEK>, <GATE DELIVERABLES (depends on type gate meeting)>

		// Do the projects running, projects killed, projects on hold and projects finished contain the right folders? If not add a warning for the project manager to take the action

		// Does EPM contain new projects that are unknown to the innoforce database?
		// If so add a warning so a decision can be made on :
		// - the project type (innovative, improvement, modification, technology development and process and tool improvements)
		// - Whether or not to develop a business case and schedule it for a gate meeting (in case of a innovation or improvement)

		return null;
	}

	public URI createIBOSecondaryProcessingMinuteTemplate() {
		// TODO create a word document: (see createPrimaryProcessingMinuteTemplate)
		return null;
	}

	public URI createIBOeighingGradingAndLogisiticMinuteTemplate() {
		// TODO create a word document: (see createPrimaryProcessingMinuteTemplate)
		return null;
	}

	public List<Project> primaryProcessingProjects() {
		//TODO
		return null;
	}
	
	public List<Project> secondaryProcessingProjects() {
		//TODO
		return null;
	}
	
	public List<Project> weighingGradingAndLogisiticsProjects() {
		//TODO
		return null;
	}
}

package nth.innoforce.domain.gatereport;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nth.innoforce.domain.gate.decision.GateDecision;
import nth.innoforce.domain.project.stage.ProjectStage;
import nth.innoforce.domain.resource.Resource;
import nth.introspect.dataaccess.hibernate.entity.DeletableEntity;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInTable;

@Entity
@Table(name = "GateReports")
public class Gate extends DeletableEntity {
	private Date meetingDate;
	private GateDecision gateDecision;
	private ProjectStage newProjectStage;
	private Date nextGateMeeting;
	private int nrOfEngineersForNextStage;
	private Resource projectLeaderForNextStage;
	private Resource projectManagerForNextStage;
	private String minutesOfMeeting;
	private int priority;

	
	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public void setGateDecision(GateDecision gateDecision) {
		this.gateDecision = gateDecision;
	}

	@Enumerated(EnumType.STRING)
	public GateDecision getGateDecision() {
		return gateDecision;
	}
	
	@Enumerated(EnumType.STRING)
	public ProjectStage getNewProjectStage() {
		return newProjectStage;
	}

	public void setNewProjectStage(ProjectStage newProjectStage) {
		this.newProjectStage = newProjectStage;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Date getNextGateMeeting() {
		return nextGateMeeting;
	}

	public void setNextGateMeeting(Date nextGateMeeting) {
		this.nextGateMeeting = nextGateMeeting;
	}

	public int getNrOfEngineersForNextStage() {
		return nrOfEngineersForNextStage;
	}

	public void setNrOfEngineersForNextStage(int nrOfEngineersForNextStage) {
		this.nrOfEngineersForNextStage = nrOfEngineersForNextStage;
	}

	@ManyToOne()
	public Resource getProjectLeaderForNextStage() {
		return projectLeaderForNextStage;
	}

	public void setProjectLeaderForNextStage(Resource projectLeaderForNextStage) {
		this.projectLeaderForNextStage = projectLeaderForNextStage;
	}

	@ManyToOne()
	public Resource getProjectManagerForNextStage() {
		return projectManagerForNextStage;
	}

	public void setProjectManagerForNextStage(Resource projectManagerForNextStage) {
		this.projectManagerForNextStage = projectManagerForNextStage;
	}

	@VisibleInTable(false)
	public String getMinutesOfMeeting() {
		return minutesOfMeeting;
	}

	public void setMinutesOfMeeting(String minutesOfMeeting) {
		this.minutesOfMeeting = minutesOfMeeting;
	}



}

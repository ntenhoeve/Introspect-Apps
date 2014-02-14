package nth.innoforce.domain.project.roadmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import nth.innoforce.domain.gatereport.Gate;
import nth.innoforce.domain.project.Project;
import nth.innoforce.domain.project.event.ProjectConceptForecast;
import nth.innoforce.domain.project.event.ProjectEvent;
import nth.innoforce.domain.project.event.gate.GoToCreativeGate;
import nth.innoforce.domain.project.event.gate.GoToDefinitionGate;
import nth.innoforce.domain.project.event.gate.GoToLaunchGate;
import nth.innoforce.domain.project.report.properties.ExpectedLaunchDateProperty;
import nth.innoforce.domain.project.report.properties.ExpectedTurnoverProperties;
import nth.innoforce.domain.project.stage.ProjectStage;
import nth.introspect.provider.domain.info.valuemodel.annotations.Enabled;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInTable;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInForm;

public class ProjectConcept extends Project implements ExpectedTurnoverProperties, ExpectedLaunchDateProperty{

	private List<ProjectEvent> history;


	@Override
	@OrderInTable(4)
	@OrderInForm(7)
	@Enumerated(EnumType.STRING)
	@Enabled(false)
	public ProjectStage getStage() {
		return ProjectStage.ProjectConcept;
	}
	
	@Override
	@VisibleInForm(false)
	@OneToMany(targetEntity = Gate.class, cascade = CascadeType.ALL)
	@GenericReturnType(ProjectEvent.class)
	public List<ProjectEvent> getHistory() {
		if (history==null) {
			history=new ArrayList<ProjectEvent>();
			history.add(new ProjectConceptForecast());
		}
		return history;
	}


	@VisibleInForm(false)
	@Override
	public void historyAddGoToCreativeGate(GoToCreativeGate goToCreativeGate) {
	}


	@VisibleInForm(false)
	@Override
	public void historyAddGoToDefinitionGate(GoToDefinitionGate goToDefinitionGate) {
	}

	@VisibleInForm(false)
	@Override
	public void historyAddGoToLaunchGate(GoToLaunchGate goToLaunchGate) {
	}


	@Override
	public Date getExpectedLaunchDate() {
		return getProjectConceptForecast().getExpectedLaunchDate();
	}

	@Override
	public void setExpectedLaunchDate(Date expectedLaunchDate) {
		getProjectConceptForecast().setExpectedLaunchDate(expectedLaunchDate);
	}


	private ProjectConceptForecast getProjectConceptForecast() {
		return(ProjectConceptForecast) getHistory().get(0);
	}


	@Override
	public int getExpectedTurnoverInEuroForYear1() {
		return getProjectConceptForecast().getExpectedTurnoverInEuroForYear1();
	}


	@Override
	public void setExpectedTurnoverInEuroForYear1(int expectedTurnoverInEuroForYear1) {
		getProjectConceptForecast().setExpectedTurnoverInEuroForYear1(expectedTurnoverInEuroForYear1);
	}


	@Override
	public int getExpectedTurnoverInEuroForYear2() {
		return getProjectConceptForecast().getExpectedTurnoverInEuroForYear2();
	}


	@Override
	public void setExpectedTurnoverInEuroForYear2(int expectedTurnoverInEuroForYear2) {
		getProjectConceptForecast().setExpectedTurnoverInEuroForYear2(expectedTurnoverInEuroForYear2);
	}


	@Override
	public int getExpectedTurnoverInEuroForYear3() {
		return getProjectConceptForecast().getExpectedTurnoverInEuroForYear3();
	}


	@Override
	public void setExpectedTurnoverInEuroForYear3(int expectedTurnoverInEuroForYear3) {
		getProjectConceptForecast().setExpectedTurnoverInEuroForYear3(expectedTurnoverInEuroForYear3);
	}
}

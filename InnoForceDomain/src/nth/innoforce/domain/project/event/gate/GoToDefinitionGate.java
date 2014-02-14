package nth.innoforce.domain.project.event.gate;

import java.util.Date;

import nth.innoforce.domain.gate.decision.GateDecision;
import nth.innoforce.domain.project.report.properties.ExpectedGoToLaunchDateProperty;
import nth.innoforce.domain.project.report.properties.ExpectedLaunchDateProperty;
import nth.introspect.provider.domain.info.valuemodel.annotations.Format;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInTable;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInForm;


public class GoToDefinitionGate extends GateReport implements ExpectedGoToLaunchDateProperty, ExpectedLaunchDateProperty {

	private Date expectedGoToLaunchDate;
	private Date expectedLaunchDate;
	
	@Override
	@VisibleInForm(false)
	@OrderInTable(3)
	public String getSummary() {
		StringBuffer summary = new StringBuffer();
		if (getDecision()!= null) {
			summary.append("Gate desicion: ");
			GateDecision decision = getDecision();
			summary.append(decision);
			if (GateDecision.Hold != decision && GateDecision.Kill != decision) {
				summary.append(", ");
				summary.append("New project stage: ");
				summary.append(getNewStage());
				summary.append(", ");
				summary.append("Go to launch date: ");
				summary.append(DATE_FORMAT.format(getExpectedGoToLaunchDate()));
				summary.append(", ");
				summary.append("Priority: ");
				summary.append(getPriorityInProductGroup());
				// TODO summary.append("( within ");
				// summary.append(getProductGroup());
				// summary.append(")");
			}
		}
		return summary.toString();
	}

	
	@Override
	public Date getExpectedGoToLaunchDate() {
		return expectedGoToLaunchDate;
	}

	public void setExpectedGoToLaunchDate(Date expectedGoToLaunchDate) {
		this.expectedGoToLaunchDate = expectedGoToLaunchDate;
	}
	
	@Override
	@Format("yyyy-MM-dd")
	public Date getExpectedLaunchDate() {
		return expectedLaunchDate;
	}

	@Override
	public void setExpectedLaunchDate(Date expectedLaunchDate) {
		this.expectedLaunchDate = expectedLaunchDate;
	}


}

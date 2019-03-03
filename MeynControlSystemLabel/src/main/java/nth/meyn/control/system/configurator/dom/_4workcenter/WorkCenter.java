package nth.meyn.control.system.configurator.dom._4workcenter;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import nth.meyn.control.system.configurator.dom._2site.Site;
import nth.meyn.control.system.configurator.dom._5workunit.WorkUnitTemplate;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Description(englishDescription = "Physical location on the shop floor that provides specific manufacturing capabilities. This is typically a production line, e.g.: Slaughter Line. At Meyn a Work Center is the equavilant to a Main Control Panel. A work center contains one or more work units, e.g. equipment like scalders or pluckers.")
public class WorkCenter {

	private WorkCenterTemplate workCenterTemplate;
	private Site site;
	private int panelNr;
	private int lineNumber;
	private List<WorkUnitTemplate> workUnitTemplates;

	@Order(value = 1)
	public WorkCenterTemplate getWorkCenterTemplate() {
		return workCenterTemplate;
	}

	public void setWorkCenterTemplate(WorkCenterTemplate workCenterTemplate) {
		this.workCenterTemplate = workCenterTemplate;
	}

	@Order(value = 10)
	@NotEmpty
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@Order(value = 20)
	@Min(1)
	@Max(99)
	public int getPanelNr() {
		return panelNr;
	}

	public void setPanelNr(int panelNr) {
		this.panelNr = panelNr;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public List<WorkUnitTemplate> getWorkUnitTemplates() {
		return workUnitTemplates;
	}

	public void setWorkUnitTemplates(List<WorkUnitTemplate> workUnitTemplates) {
		this.workUnitTemplates = workUnitTemplates;
	}

}

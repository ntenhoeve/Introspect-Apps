package nth.meyn.control.system.configurator.dom._5workunit;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class WorkUnitRule {

	private WorkUnitTemplate workUnitTemplate;
	private int minimumInstances;
	private int maximumInstances;
	private int startPage;
	private int pageOffset;

	@Order(value = 1)
	public WorkUnitTemplate getWorkUnitTemplate() {
		return workUnitTemplate;
	}

	public void setWorkUnitTemplate(WorkUnitTemplate workUnitTemplate) {
		this.workUnitTemplate = workUnitTemplate;
	}

	@ParameterFactory
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	public void workUnitTemplateCreate(WorkUnitTemplate workUnitTemplate) {
		this.workUnitTemplate = workUnitTemplate;
	}

	public boolean workUnitTemplateCreateHidden() {
		return this.workUnitTemplate != null;
	}

	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	public void workUnitTemplateCreateModify(WorkUnitTemplate workUnitTemplate) {
		this.workUnitTemplate = workUnitTemplate;
	}

	public boolean workUnitTemplateCreateModify() {
		return this.workUnitTemplate == null;
	}

	@Order(value = 10)
	public int getMinimumInstances() {
		return minimumInstances;
	}

	public void setMinimumInstances(int minimumInstances) {
		this.minimumInstances = minimumInstances;
	}

	@Order(value = 20)
	public int getMaximumInstances() {
		return maximumInstances;
	}

	public void setMaximumInstances(int maximumInstances) {
		this.maximumInstances = maximumInstances;
	}

	@Order(value = 30)
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	@Order(value = 40)
	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(workUnitTemplate).contact("(min:").contact(minimumInstances).contact(", max:")
				.contact(maximumInstances).contact(", start page:").contact(startPage).contact(", offset:")
				.contact(pageOffset).contact(")").toString();
	}

}

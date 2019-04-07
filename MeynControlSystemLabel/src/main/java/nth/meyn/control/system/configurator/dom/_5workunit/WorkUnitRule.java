package nth.meyn.control.system.configurator.dom._5workunit;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

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

}

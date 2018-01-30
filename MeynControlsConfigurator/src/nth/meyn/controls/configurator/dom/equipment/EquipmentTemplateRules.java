package nth.meyn.controls.configurator.dom.equipment;

import nth.introspect.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription="Tels how many of Equipment (e.g. pluckers) are alowed in a project and on what pages of the electrical diagram they are placed")
public class EquipmentTemplateRules {
	private EquipmentTemplate equipmentTemplate;
	private int minimumInstances;
	private int maximumInstances;
	private int startPage;
	private int pageOffset;
	public EquipmentTemplate getEquipmentTemplate() {
		return equipmentTemplate;
	}
	public void setEquipmentTemplate(EquipmentTemplate equipmentTemplate) {
		this.equipmentTemplate = equipmentTemplate;
	}
	public int getMinimumInstances() {
		return minimumInstances;
	}
	public void setMinimumInstances(int minimumInstances) {
		this.minimumInstances = minimumInstances;
	}
	public int getMaximumInstances() {
		return maximumInstances;
	}
	public void setMaximumInstances(int maximumInstances) {
		this.maximumInstances = maximumInstances;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
	
	
	
}

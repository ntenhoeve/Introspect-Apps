package nth.meyn.control.system.configurator.dom._6equipmentmodule;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Description(englishDescription = "Functional group of control modules to achieve a specific process activity (a function), e.g. a temperature controller, Conveyor, Deskinner, etc")
public class EquipmentModule {

	private String name;// does it need an additional name?
	private EquipmentModuleTemplate equipmentModuleTemplate;

	@Order(value = 1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(value = 10)
	public EquipmentModuleTemplate getEquipmentModuleTemplate() {
		return equipmentModuleTemplate;
	}

	public void setEquipmentModuleTemplate(EquipmentModuleTemplate equipmentModuleTemplate) {
		this.equipmentModuleTemplate = equipmentModuleTemplate;
	}

}

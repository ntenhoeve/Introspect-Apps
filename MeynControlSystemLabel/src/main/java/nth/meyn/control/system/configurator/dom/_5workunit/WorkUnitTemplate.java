package nth.meyn.control.system.configurator.dom._5workunit;

import java.util.List;

import nth.meyn.control.system.configurator.dom._6equipmentmodule.EquipmentModule;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Description(englishDescription = "A machine or equipment made out of several equipment modules. e.g.: a scalder, plucker, maestro, panconveyor, carousel rehanger, etc.")
public class WorkUnitTemplate {
	private String name;
	private List<EquipmentModule> equipmentModules;//equipmentModuleTemplateRules

	@Order(value = 1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(value = 10)
	public List<EquipmentModule> getEquipmentModules() {
		return equipmentModules;
	}

	public void setEquipmentModules(List<EquipmentModule> equipmentModules) {
		this.equipmentModules = equipmentModules;
	}

}

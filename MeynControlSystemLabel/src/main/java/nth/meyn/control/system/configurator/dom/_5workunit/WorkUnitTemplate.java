package nth.meyn.control.system.configurator.dom._5workunit;

import java.util.List;

import nth.meyn.control.system.configurator.dom._6equipmentmodule.EquipmentModule;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(englishDescription = WorkUnitTemplate.DESCRIPTION)
public class WorkUnitTemplate {
	static final String DESCRIPTION = "A template for a logical group of equipment that can be switched on or off together. This is typically a system or machine, e.g.: a scalder, plucker, rehanger. A workunits contains one or more Equipment Modules, e.g. a temperature conroller, line speed controller.";
	private String name;
	private List<EquipmentModule> equipmentModules;// equipmentModuleTemplateRules

	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.INFO_CIRCLE)
	public String info() {
		return getClass().getSimpleName() + "= " + DESCRIPTION;
	}

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

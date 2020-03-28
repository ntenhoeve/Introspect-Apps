package nth.meyn.control.system.configurator.dom._6equipmentmodule;

import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(defaultEnglish = EquipmentModule.DESCRIPTION)
public class EquipmentModule {

	static final String DESCRIPTION = "Functional group of control modules to achieve a specific process activity (a function), e.g. a temperature controller, Conveyor, Deskinner, etc";
	private String name;// does it need an additional name?
	private EquipmentModuleTemplate equipmentModuleTemplate;

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
	public EquipmentModuleTemplate getEquipmentModuleTemplate() {
		return equipmentModuleTemplate;
	}

	public void setEquipmentModuleTemplate(EquipmentModuleTemplate equipmentModuleTemplate) {
		this.equipmentModuleTemplate = equipmentModuleTemplate;
	}

}

package nth.meyn.control.system.configurator.dom._5workunit;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.control.system.configurator.dom._6equipmentmodule.EquipmentModule;
import nth.meyn.control.system.configurator.dom._6equipmentmodule.EquipmentModuleTemplate;
import nth.meyn.control.system.configurator.dom.ethernetdevice.EthernetDeviceTemplate;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.PropertyActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(englishDescription = WorkUnitTemplate.DESCRIPTION)
public class WorkUnitTemplate {
	static final String DESCRIPTION = "A template for a logical group of equipment that can be switched on or off together. This is typically a system or machine, e.g.: a scalder, plucker, rehanger. A workunits contains one or more Equipment Modules, e.g. a temperature conroller, line speed controller.";
	private String name;
	private List<EthernetDeviceTemplate> ethernetDeviceTemplates = new ArrayList<>();
	private List<EquipmentModuleTemplate> equipmentModuleTemplates = new ArrayList<>();

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
	public List<EquipmentModuleTemplate> getEquipmentModuleTemplates() {
		return equipmentModuleTemplates;
	}

	public void setEquipmentModuleTemplates(List<EquipmentModuleTemplate> equipmentModuleTemplates) {
		if (equipmentModuleTemplates != null) {
			this.equipmentModuleTemplates = equipmentModuleTemplates;
		}
	}

	@Order(10.1)
	@PropertyActionMethod("EquipmentModuleTemplates")
	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.EYE)
	public EquipmentModuleTemplate viewWorkUnitTemplate(EquipmentModuleTemplate equipmentModuleTemplate) {
		return equipmentModuleTemplate;
	}

	@Order(10.1)
	@PropertyActionMethod("EquipmentModuleTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyEquipmentModuleTemplate(EquipmentModule equipmentModule) {
	}

	@Order(10.3)
	@ParameterFactory
	@PropertyActionMethod("EquipmentModuleTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	public void addNewEquipmentModuleTemplate(EquipmentModuleTemplate equipmentModuleTemplate) {
		equipmentModuleTemplates.add(equipmentModuleTemplate);
	}

	@Order(10.4)
	@PropertyActionMethod("EquipmentModuleTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.TRASH)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void removeEquipmentModuleTemplate(EquipmentModuleTemplate equipmentModuleTemplate) {
		equipmentModuleTemplates.remove(equipmentModuleTemplate);
	}

	public boolean removeEquipmentModuleTemplateHidden() {
		return equipmentModuleTemplates.size() == 0;
	}

	@Order(10.5)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_UP)
	@PropertyActionMethod("EquipmentModuleTemplates")
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveEquipmentModuleTemplateUp(EquipmentModuleTemplate equipmentModuleTemplate) {
		int index = equipmentModuleTemplates.indexOf(equipmentModuleTemplate);
		if (index > 0) {
			equipmentModuleTemplates.remove(equipmentModuleTemplate);
			equipmentModuleTemplates.add(index - 1, equipmentModuleTemplate);
		}
	}

	public boolean moveEquipmentModuleTemplateUpHidden() {
		return equipmentModuleTemplates.size() < 2;
	}

	@Order(10.6)
	@PropertyActionMethod("EquipmentModuleTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_DOWN)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveEquipmentModuleTemplateDown(EquipmentModuleTemplate equipmentModuleTemplate) {
		int index = equipmentModuleTemplates.indexOf(equipmentModuleTemplate);
		if (index < equipmentModuleTemplates.size() - 1) {
			equipmentModuleTemplates.remove(equipmentModuleTemplate);
			equipmentModuleTemplates.add(index + 1, equipmentModuleTemplate);
		}
	}

	public boolean moveEquipmentModuleTemplateDownHidden() {
		return equipmentModuleTemplates.size() < 2;
	}

	@Order(value = 20)
	public List<EthernetDeviceTemplate> getEthernetDeviceTemplates() {
		return ethernetDeviceTemplates;
	}

	public void setEthernetDeviceTemplates(List<EthernetDeviceTemplate> ethernetDeviceTemplates) {
		if (ethernetDeviceTemplates != null) {
			this.ethernetDeviceTemplates = ethernetDeviceTemplates;
		}
	}

	@Order(20.1)
	@PropertyActionMethod("EthernetDeviceTemplates")
	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.EYE)
	public EthernetDeviceTemplate viewEthernetDeviceTemplate(EthernetDeviceTemplate ethernetDeviceTemplate) {
		return ethernetDeviceTemplate;
	}

	@Order(20.1)
	@PropertyActionMethod("EthernetDeviceTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyEthernetDeviceTemplate(EthernetDeviceTemplate ethernetDeviceTemplate) {
	}

	@Order(20.3)
	@ParameterFactory
	@PropertyActionMethod("EthernetDeviceTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	public void addNewEthernetDeviceTemplate(EthernetDeviceTemplate ethernetDeviceTemplate) {
		ethernetDeviceTemplates.add(ethernetDeviceTemplate);
	}

	@Order(20.4)
	@PropertyActionMethod("EthernetDeviceTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.TRASH)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void removeEthernetDeviceTemplate(EthernetDeviceTemplate ethernetDeviceTemplate) {
		ethernetDeviceTemplates.remove(ethernetDeviceTemplate);
	}

	public boolean removeEthernetDeviceTemplateHidden() {
		return ethernetDeviceTemplates.size() == 0;
	}

	@Order(20.5)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_UP)
	@PropertyActionMethod("EthernetDeviceTemplates")
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveEthernetDeviceTemplateUp(EthernetDeviceTemplate EthernetDeviceTemplate) {
		int index = ethernetDeviceTemplates.indexOf(EthernetDeviceTemplate);
		if (index > 0) {
			ethernetDeviceTemplates.remove(EthernetDeviceTemplate);
			ethernetDeviceTemplates.add(index - 1, EthernetDeviceTemplate);
		}
	}

	public boolean moveEthernetDeviceTemplateUpHidden() {
		return ethernetDeviceTemplates.size() < 2;
	}

	@Order(20.6)
	@PropertyActionMethod("EthernetDeviceTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_DOWN)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveEthernetDeviceTemplateDown(EthernetDeviceTemplate EthernetDeviceTemplate) {
		int index = ethernetDeviceTemplates.indexOf(EthernetDeviceTemplate);
		if (index < ethernetDeviceTemplates.size() - 1) {
			ethernetDeviceTemplates.remove(EthernetDeviceTemplate);
			ethernetDeviceTemplates.add(index + 1, EthernetDeviceTemplate);
		}
	}

	public boolean moveEthernetDeviceTemplateDownHidden() {
		return ethernetDeviceTemplates.size() < 2;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(name).toString();
	}

}

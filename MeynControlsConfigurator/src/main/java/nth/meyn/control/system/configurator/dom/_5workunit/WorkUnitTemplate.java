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
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(defaultEnglish = WorkUnitTemplate.DESCRIPTION)
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
	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.EYE)
	public EquipmentModuleTemplate equipmentModuleTemplatesView(EquipmentModuleTemplate equipmentModuleTemplate) {
		return equipmentModuleTemplate;
	}

	@Order(10.1)
	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void equipmentModuleTemplatesModify(EquipmentModule equipmentModule) {
	}

	@Order(10.3)
	@ParameterFactory
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	public void equipmentModuleTemplatesAddNew(EquipmentModuleTemplate equipmentModuleTemplate) {
		equipmentModuleTemplates.add(equipmentModuleTemplate);
	}

	@Order(10.4)
	@FontIcon(fontIconUrl = FontAwesomeUrl.TRASH)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void equipmentModuleTemplatesRemove(EquipmentModuleTemplate equipmentModuleTemplate) {
		equipmentModuleTemplates.remove(equipmentModuleTemplate);
	}

	public boolean equipmentModuleTemplatesRemoveHidden() {
		return equipmentModuleTemplates.size() == 0;
	}

	@Order(10.5)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_UP)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void equipmentModuleTemplatesMoveUp(EquipmentModuleTemplate equipmentModuleTemplate) {
		int index = equipmentModuleTemplates.indexOf(equipmentModuleTemplate);
		if (index > 0) {
			equipmentModuleTemplates.remove(equipmentModuleTemplate);
			equipmentModuleTemplates.add(index - 1, equipmentModuleTemplate);
		}
	}

	public boolean equipmentModuleTemplatesMoveUpHidden() {
		return equipmentModuleTemplates.size() < 2;
	}

	@Order(10.6)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_DOWN)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void equipmentModuleTemplatesMoveDown(EquipmentModuleTemplate equipmentModuleTemplate) {
		int index = equipmentModuleTemplates.indexOf(equipmentModuleTemplate);
		if (index < equipmentModuleTemplates.size() - 1) {
			equipmentModuleTemplates.remove(equipmentModuleTemplate);
			equipmentModuleTemplates.add(index + 1, equipmentModuleTemplate);
		}
	}

	public boolean equipmentModuleTemplatesMoveDownHidden() {
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
	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.EYE)
	public EthernetDeviceTemplate ethernetDeviceTemplatesView(EthernetDeviceTemplate ethernetDeviceTemplate) {
		return ethernetDeviceTemplate;
	}

	@Order(20.1)
	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void ethernetDeviceTemplatesModify(EthernetDeviceTemplate ethernetDeviceTemplate) {
	}

	@Order(20.3)
	@ParameterFactory
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	public void ethernetDeviceTemplatesAddNew(EthernetDeviceTemplate ethernetDeviceTemplate) {
		ethernetDeviceTemplates.add(ethernetDeviceTemplate);
	}

	@Order(20.4)
	@FontIcon(fontIconUrl = FontAwesomeUrl.TRASH)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void ethernetDeviceTemplatesRemove(EthernetDeviceTemplate ethernetDeviceTemplate) {
		ethernetDeviceTemplates.remove(ethernetDeviceTemplate);
	}

	public boolean ethernetDeviceTemplatesRemoveHidden() {
		return ethernetDeviceTemplates.size() == 0;
	}

	@Order(20.5)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_UP)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void ethernetDeviceTemplatesMoveUp(EthernetDeviceTemplate EthernetDeviceTemplate) {
		int index = ethernetDeviceTemplates.indexOf(EthernetDeviceTemplate);
		if (index > 0) {
			ethernetDeviceTemplates.remove(EthernetDeviceTemplate);
			ethernetDeviceTemplates.add(index - 1, EthernetDeviceTemplate);
		}
	}

	public boolean ethernetDeviceTemplatesMoveUpHidden() {
		return ethernetDeviceTemplates.size() < 2;
	}

	@Order(20.6)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_DOWN)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void ethernetDeviceTemplatesRemoveMoveDown(EthernetDeviceTemplate EthernetDeviceTemplate) {
		int index = ethernetDeviceTemplates.indexOf(EthernetDeviceTemplate);
		if (index < ethernetDeviceTemplates.size() - 1) {
			ethernetDeviceTemplates.remove(EthernetDeviceTemplate);
			ethernetDeviceTemplates.add(index + 1, EthernetDeviceTemplate);
		}
	}

	public boolean ethernetDeviceTemplatesRemoveMoveDownHidden() {
		return ethernetDeviceTemplates.size() < 2;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(name).toString();
	}

}

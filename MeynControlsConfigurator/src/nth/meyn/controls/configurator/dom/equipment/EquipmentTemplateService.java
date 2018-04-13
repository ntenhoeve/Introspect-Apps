package nth.meyn.controls.configurator.dom.equipment;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;

public class EquipmentTemplateService {
	public List<EquipmentTemplateTag> allEquipmentTemplateTags() {
		return null;//TODO
	}
	
	public List<EquipmentTemplate> allEquipmentTemplates() {
		List<EquipmentTemplate> equipmentTemplates=new ArrayList<>();
		equipmentTemplates.add(EquipmentTemplateExampleFactory.createArrivalTurnTable());
		return equipmentTemplates;
	}
	
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public EquipmentTemplate viewEquipmentTemplate(EquipmentTemplate equipmentTemplate) {
		return equipmentTemplate;
	}
}

package nth.meyn.controls.configurator.dom.equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentTemplateService {
	public List<EquipmentTemplateTag> allEquipmentTemplateTags() {
		return null;//TODO
	}
	
	public List<EquipmentTemplate> allEquipmentTemplates() {
		List<EquipmentTemplate> equipmentTemplates=new ArrayList<>();
		equipmentTemplates.add(EquipmentTemplateExampleFactory.createArrivalTurnTable());
		return equipmentTemplates;
	}
}

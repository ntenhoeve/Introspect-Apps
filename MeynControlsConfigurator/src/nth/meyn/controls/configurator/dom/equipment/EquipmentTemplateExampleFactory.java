package nth.meyn.controls.configurator.dom.equipment;

import java.time.LocalDateTime;
import java.util.List;

import nth.meyn.controls.configurator.dom.function.FunctionTemplate;
import nth.meyn.controls.configurator.dom.project.ProjectTemplateExampleFactory;

public class EquipmentTemplateExampleFactory {

	public static EquipmentTemplate createArrivalTurnTable() {
		EquipmentTemplate turnTable=new EquipmentTemplate();
		turnTable.setName("Turn Table");
		turnTable.setAbbreviation("Tt");
		turnTable.setVersion(LocalDateTime.now());
		
		List<FunctionTemplate> functionTemplates = turnTable.getFunctionTemplates();
		functionTemplates.add(ProjectTemplateExampleFactory.createArrivalTurnTableConveyFunction());
		functionTemplates.add(ProjectTemplateExampleFactory.createArrivalTurnTableTurnFunction());
		
		return turnTable;
	}

}

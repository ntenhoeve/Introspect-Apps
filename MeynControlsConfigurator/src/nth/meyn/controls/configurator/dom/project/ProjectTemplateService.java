package nth.meyn.controls.configurator.dom.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.meyn.controls.configurator.dom.component.ComponentTemplateService;
import nth.meyn.controls.configurator.dom.equipment.EquipmentTemplate;
import nth.meyn.controls.configurator.dom.equipment.EquipmentTemplateRule;
import nth.meyn.controls.configurator.dom.function.FunctionOrComponent;
import nth.meyn.controls.configurator.dom.function.FunctionTemplate;

public class ProjectTemplateService {
	public List<ProjectTemplate> allProjectTemplates() {
		List<ProjectTemplate> projectTemplates=new ArrayList<>();
		projectTemplates.add(createProjectTemplateExample());
		return projectTemplates;
	}

	private ProjectTemplate createProjectTemplateExample() {
		ProjectTemplate projectTemplate=new ProjectTemplate();
		projectTemplate.setName("Arrival system");
		projectTemplate.setVersion(LocalDateTime.now());
		List<EquipmentTemplateRule> equipmentTemplateRules = projectTemplate.getEquipmentTemplateRules();
		
		equipmentTemplateRules.add(createArrivalTurnTableRule());
		return projectTemplate;
	}

	private EquipmentTemplateRule createArrivalTurnTableRule() {
		EquipmentTemplateRule equipmentTemplateRule=new EquipmentTemplateRule();
		equipmentTemplateRule.setEquipmentTemplate(createArrivalTurnTableEquipment());
		equipmentTemplateRule.setMinimumInstances(0);
		equipmentTemplateRule.setMaximumInstances(20);
		equipmentTemplateRule.setStartPage(100);
		equipmentTemplateRule.setPageOffset(10);
		return equipmentTemplateRule;
	}

	private EquipmentTemplate createArrivalTurnTableEquipment() {
		EquipmentTemplate turnTable=new EquipmentTemplate();
		turnTable.setName("Turn Table");
		turnTable.setAbbreviation("Tt");
		turnTable.setVersion(LocalDateTime.now());
		
		List<FunctionTemplate> functionTemplates = turnTable.getFunctionTemplates();
		functionTemplates.add(createArrivalTurnTableConveyFunction());
		functionTemplates.add(createArrivalTurnTableTurnFunction());
		
		return turnTable;
	}

	private FunctionTemplate createArrivalTurnTableTurnFunction() {
		FunctionTemplate turnFunction = new FunctionTemplate();
		turnFunction.setName("Turn");
		turnFunction.setAbbreviation("Trn");
		List<FunctionOrComponent> children = turnFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateService.get(ComponentTemplateService.ELECTRIC_MOTOR_GROUP_WITH_FREQUENCY_CONTROLLER));//TODO variant
		children.add(ComponentTemplateService.get(ComponentTemplateService.RELATIVE_ENCODER));//TODO variant
		children.add(createHomeSensorFunction());
		return turnFunction;
	}

	private FunctionOrComponent createHomeSensorFunction() {
		FunctionTemplate homeSensorFunction = new FunctionTemplate();
		homeSensorFunction.setName("Home");
		homeSensorFunction.setAbbreviation("Home");
		List<FunctionOrComponent> children = homeSensorFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateService.get(ComponentTemplateService.INDUCTIVE_PROXIMITY_SENSOR));//TODO variant
		return homeSensorFunction;
	}


	private FunctionTemplate createArrivalTurnTableConveyFunction() {
		FunctionTemplate turnFunction = new FunctionTemplate();
		turnFunction.setName("Convey");
		turnFunction.setAbbreviation("Cnv");
		List<FunctionOrComponent> children = turnFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateService.get(ComponentTemplateService.ELECTRIC_MOTOR_GROUP_WITH_FREQUENCY_CONTROLLER));//TODO variant
		children.add(createBeginSensorFunction());
		children.add(createEndSensorFunction());
		return turnFunction;
	}
	
	private FunctionOrComponent createBeginSensorFunction() {
		FunctionTemplate beginSensorFunction = new FunctionTemplate();
		beginSensorFunction.setName("Begin");
		beginSensorFunction.setAbbreviation("Bgn");
		List<FunctionOrComponent> children = beginSensorFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateService.get(ComponentTemplateService.INDUCTIVE_PROXIMITY_SENSOR));//TODO variant
		return beginSensorFunction;
	}

	private FunctionOrComponent createEndSensorFunction() {
		FunctionTemplate endSensorFunction = new FunctionTemplate();
		endSensorFunction.setName("End");
		endSensorFunction.setAbbreviation("End");
		List<FunctionOrComponent> children = endSensorFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateService.get(ComponentTemplateService.INDUCTIVE_PROXIMITY_SENSOR));//TODO variant
		return endSensorFunction;
	}

}

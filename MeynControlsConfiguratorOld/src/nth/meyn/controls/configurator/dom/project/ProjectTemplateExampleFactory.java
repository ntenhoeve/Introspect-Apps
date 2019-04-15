package nth.meyn.controls.configurator.dom.project;

import java.time.LocalDateTime;
import java.util.List;

import nth.meyn.controls.configurator.dom.component.ComponentTemplateExampleFactory;
import nth.meyn.controls.configurator.dom.component.ComponentTemplateService;
import nth.meyn.controls.configurator.dom.equipment.EquipmentTemplateExampleFactory;
import nth.meyn.controls.configurator.dom.equipment.EquipmentTemplateRule;
import nth.meyn.controls.configurator.dom.function.FunctionOrComponent;
import nth.meyn.controls.configurator.dom.function.FunctionTemplate;

public class ProjectTemplateExampleFactory {

	public static ProjectTemplate create() {
		return createProjectTemplateExample();
	}
	
	private static ProjectTemplate createProjectTemplateExample() {
		ProjectTemplate projectTemplate=new ProjectTemplate();
		projectTemplate.setName("Arrival system");
		projectTemplate.setVersion(LocalDateTime.now());
		List<EquipmentTemplateRule> equipmentTemplateRules = projectTemplate.getEquipmentTemplateRules();
		
		equipmentTemplateRules.add(createArrivalTurnTableRule());
		return projectTemplate;
	}
	
	private static EquipmentTemplateRule createArrivalTurnTableRule() {
		EquipmentTemplateRule equipmentTemplateRule=new EquipmentTemplateRule();
		equipmentTemplateRule.setEquipmentTemplate(EquipmentTemplateExampleFactory.createArrivalTurnTable());
		equipmentTemplateRule.setMinimumInstances(0);
		equipmentTemplateRule.setMaximumInstances(20);
		equipmentTemplateRule.setStartPage(100);
		equipmentTemplateRule.setPageOffset(10);
		return equipmentTemplateRule;
	}

	public static FunctionOrComponent createHomeSensorFunction() {
		FunctionTemplate homeSensorFunction = new FunctionTemplate();
		homeSensorFunction.setName("Home");
		homeSensorFunction.setAbbreviation("Home");
		List<FunctionOrComponent> children = homeSensorFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateExampleFactory.createInductiveProximitySensor());
		return homeSensorFunction;
	}


	public static FunctionTemplate createArrivalTurnTableConveyFunction() {
		FunctionTemplate turnFunction = new FunctionTemplate();
		turnFunction.setName("Convey");
		turnFunction.setAbbreviation("Cnv");
		List<FunctionOrComponent> children = turnFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateExampleFactory.createElectricMotorGroupWithFrequencyController());//TODO variant
		children.add(createBeginSensorFunction());
		children.add(createEndSensorFunction());
		return turnFunction;
	}
	
	private static FunctionOrComponent createBeginSensorFunction() {
		FunctionTemplate beginSensorFunction = new FunctionTemplate();
		beginSensorFunction.setName("Begin");
		beginSensorFunction.setAbbreviation("Bgn");
		List<FunctionOrComponent> children = beginSensorFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateExampleFactory.createInductiveProximitySensor());//TODO variant
		return beginSensorFunction;
	}

	private static FunctionOrComponent createEndSensorFunction() {
		FunctionTemplate endSensorFunction = new FunctionTemplate();
		endSensorFunction.setName("End");
		endSensorFunction.setAbbreviation("End");
		List<FunctionOrComponent> children = endSensorFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateExampleFactory.createInductiveProximitySensor());//TODO variant
		return endSensorFunction;
	}

	public static FunctionTemplate createArrivalTurnTableTurnFunction() {
		FunctionTemplate turnFunction = new FunctionTemplate();
		turnFunction.setName("Turn");
		turnFunction.setAbbreviation("Trn");
		List<FunctionOrComponent> children = turnFunction.getFunctionAndComponentTemplates();
		children.add(ComponentTemplateExampleFactory.createElectricMotorGroupWithFrequencyController());
		children.add(ComponentTemplateExampleFactory.createRelativeEncoderSensor());
		children.add(createHomeSensorFunction());
		return turnFunction;
	}

}

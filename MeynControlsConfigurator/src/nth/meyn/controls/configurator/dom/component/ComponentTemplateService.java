package nth.meyn.controls.configurator.dom.component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class ComponentTemplateService {
	public static final String O2_CONCENTRATION_SENSOR = "O2 Concentration Sensor";
	public static final String CYLINDER_WITH_3_4_VALVE_WITH_2_SPEEDS = "Cylinder with 3/4 Valve with 2 speeds";
	public static final String CYLINDER_WITH_3_4_VALVE = "Cylinder with 3/4 Valve";
	public static final String CYLINDER_WITH_2_3_VALVE = "Cylinder with 2/3 Valve";
	public static final String ELECTRIC_MOTOR_GROUP_WITH_MOTOR_PROTECTIVE_CIRQUIT_BRAKER_PKZM_AND_2_DIRECTIONS = "Electric Motor Group with Motor Protective Cirquit Braker (PKZM) and 2 Directions";
	public static final String ELECTRIC_MOTOR_GROUP_WITH_MOTOR_PROTECTIVE_CIRQUIT_BRAKER_PKZM = "Electric Motor Group with Motor Protective Cirquit Braker (PKZM)";
	public static final String ELECTRIC_MOTOR_GROUP_WITH_SOFT_STARTER = "Electric Motor Group with Soft Starter";
	public static final String ELECTRIC_MOTOR_GROUP_WITH_FREQUENCY_CONTROLLER = "Electric Motor Group with Frequency Controller";
	public static final String INDUCTIVE_PROXIMITY_SENSOR = "Inductive Proximity Sensor";
	public static final String RELATIVE_ENCODER = "Relative Encode";

	public List<ComponentTemplate> allComponentTemplates() {
		return createExampleComponentTemplates();
	}
	
	@ParameterFactory
	public List<ComponentTemplate> findComponentTemplate(SearchText searchText) {
		String textToFind = searchText.getTextToFind();
		List<ComponentTemplate> allComponentTemplates = allComponentTemplates();
		List<ComponentTemplate> found = allComponentTemplates.stream().filter(createFilter(textToFind) ).collect(Collectors.toList());
		return found;
	}
	
	
	private Predicate<ComponentTemplate> createFilter(String textToFind) {
		String lowerTextToFind = textToFind.toLowerCase();
		return new Predicate<ComponentTemplate>() {
			@Override
			public boolean test(ComponentTemplate componentTemplate) {
				if (componentTemplate==null || componentTemplate.getName()==null) {
					return false;
				}
				if (componentTemplate.getName().toLowerCase().contains(lowerTextToFind)) {
				return false;
				} else {
					for ( ComponentTemplateTag tag : componentTemplate.getTags()) {
						if (tag.getName()!=null && tag.getName().toLowerCase().contains(lowerTextToFind)) {
							return true;
						}
					}
					return false;
				}
			}
		};
	}

	private static List<ComponentTemplate> createExampleComponentTemplates() {
		List<ComponentTemplate> componentTemplates=new ArrayList<>();
		ComponentTemplateTag actuatorTag=new ComponentTemplateTag();
		actuatorTag.setName("Actuator");
		ComponentTemplateTag inputTag=new ComponentTemplateTag();
		inputTag.setName("Input");
		
		ComponentTemplate motorGroup1 = new ComponentTemplate();
		motorGroup1.setName(ELECTRIC_MOTOR_GROUP_WITH_FREQUENCY_CONTROLLER);
		motorGroup1.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup1.getTags().add(actuatorTag);
		componentTemplates.add(motorGroup1);

		ComponentTemplate motorGroup2 = new ComponentTemplate();
		motorGroup2.setName(ELECTRIC_MOTOR_GROUP_WITH_SOFT_STARTER);
		motorGroup2.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup2.getTags().add(actuatorTag);
		componentTemplates.add(motorGroup2);

		ComponentTemplate motorGroup3 = new ComponentTemplate();
		motorGroup3.setName(ELECTRIC_MOTOR_GROUP_WITH_MOTOR_PROTECTIVE_CIRQUIT_BRAKER_PKZM);
		motorGroup3.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup3.getTags().add(actuatorTag);
		componentTemplates.add(motorGroup3);

		ComponentTemplate motorGroup4 = new ComponentTemplate();
		motorGroup4.setName(ELECTRIC_MOTOR_GROUP_WITH_MOTOR_PROTECTIVE_CIRQUIT_BRAKER_PKZM_AND_2_DIRECTIONS);
		motorGroup4.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup4.getTags().add(actuatorTag);
		componentTemplates.add(motorGroup4);

		
		ComponentTemplate cylinder1 = new ComponentTemplate();
		cylinder1.setName(CYLINDER_WITH_2_3_VALVE);
		cylinder1.setVersion(LocalDateTime.of(2015, 11, 11, 17, 12));
		cylinder1.getTags().add(actuatorTag);
		componentTemplates.add(cylinder1);
	
		ComponentTemplate cylinder2 = new ComponentTemplate();
		cylinder2.setName(CYLINDER_WITH_3_4_VALVE);
		cylinder2.setVersion(LocalDateTime.of(2015, 11, 11, 17, 12));
		cylinder2.getTags().add(actuatorTag);
		componentTemplates.add(cylinder2);
		
		ComponentTemplate cylinder3 = new ComponentTemplate();
		cylinder3.setName(CYLINDER_WITH_3_4_VALVE_WITH_2_SPEEDS);
		cylinder3.setVersion(LocalDateTime.of(2015, 11, 11, 17, 12));
		cylinder3.getTags().add(actuatorTag);
		componentTemplates.add(cylinder3);

		ComponentTemplate inductiveProximitySensor = new ComponentTemplate();
		inductiveProximitySensor.setName(INDUCTIVE_PROXIMITY_SENSOR);
		inductiveProximitySensor.setVersion(LocalDateTime.of(2013, 1, 4, 12, 11));
		inductiveProximitySensor.getTags().add(inputTag);
		componentTemplates.add(inductiveProximitySensor);

		ComponentTemplate relativeEncoderSensor = new ComponentTemplate();
		relativeEncoderSensor.setName(RELATIVE_ENCODER);
		relativeEncoderSensor.setVersion(LocalDateTime.of(2013, 1, 9, 12, 11));
		relativeEncoderSensor.getTags().add(inputTag);
		componentTemplates.add(relativeEncoderSensor);
		
		ComponentTemplate o2Sensor = new ComponentTemplate();
		o2Sensor.setName(O2_CONCENTRATION_SENSOR);
		o2Sensor.setVersion(LocalDateTime.of(2014, 1, 4, 12, 11));
		o2Sensor.getTags().add(inputTag);
		componentTemplates.add(o2Sensor);
		
		return componentTemplates;
	}
	
	public List<ComponentTemplateTag> allComponentTemplateTags() {
		return createExampleComponentTemplateTags();
	}
	
	public static ComponentTemplate get(String componentTemplateNameToFind) {
		List<ComponentTemplate> componentTemplates = createExampleComponentTemplates();
		for (ComponentTemplate componentTemplate : componentTemplates) {
			if (componentTemplate.getName().equals(componentTemplateNameToFind)) {
				return componentTemplate;
			}
		}
		return null;
	}

	private List<ComponentTemplateTag> createExampleComponentTemplateTags() {
		List<ComponentTemplateTag> componentTemplateTags=new ArrayList<>();
		ComponentTemplateTag actuatorTag=new ComponentTemplateTag();
		actuatorTag.setName("Actuator");
		componentTemplateTags.add(actuatorTag);
		ComponentTemplateTag inputTag=new ComponentTemplateTag();
		inputTag.setName("Input");
		componentTemplateTags.add(inputTag);
		return componentTemplateTags;
	}

}

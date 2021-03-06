package nth.meyn.controls.configurator.dom.component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.meyn.controls.configurator.dom.named.Tag;

public class ComponentTemplateExampleFactory {
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
	private static Tag actuatorTag;
	private static Tag sensorTag;

	static {
		actuatorTag = createActuatorTag();
		sensorTag = createSensorTag();
	}

	public static List<ComponentTemplate> createComponents() {
		List<ComponentTemplate> componentTemplates = new ArrayList<>();
		componentTemplates.add(createElectricMotorGroupWithFrequencyController());
		componentTemplates.add(createElectricMotorGroupWithSoftStarter());
		componentTemplates.add(createElectricMotorGroupWithProtectiveCirquitBraker());
		componentTemplates.add(createElectricMotorGroupWithProtectiveCirquitBreaker2Directions());
		componentTemplates.add(createCylinderWith23Valve());
		componentTemplates.add(createCylinderWith34Valve());
		componentTemplates.add(createCylinderWith34ValveWith2Speeds());
		componentTemplates.add(createInductiveProximitySensor());
		componentTemplates.add(createRelativeEncoderSensor());
		componentTemplates.add(createO2ConcentrationSensor());
		return componentTemplates;
	}

	public static ComponentTemplate createO2ConcentrationSensor() {
		ComponentTemplate o2Sensor = new ComponentTemplate();
		o2Sensor.setName(O2_CONCENTRATION_SENSOR);
		o2Sensor.setVersion(LocalDateTime.of(2014, 1, 4, 12, 11));
		o2Sensor.getTags().add(sensorTag);
		return o2Sensor;
	}

	public static ComponentTemplate createRelativeEncoderSensor() {
		ComponentTemplate relativeEncoderSensor = new ComponentTemplate();
		relativeEncoderSensor.setName(RELATIVE_ENCODER);
		relativeEncoderSensor.setVersion(LocalDateTime.of(2013, 1, 9, 12, 11));
		relativeEncoderSensor.getTags().add(sensorTag);
		return relativeEncoderSensor;
	}

	public static ComponentTemplate createInductiveProximitySensor() {
		ComponentTemplate inductiveProximitySensor = new ComponentTemplate();
		inductiveProximitySensor.setName(INDUCTIVE_PROXIMITY_SENSOR);
		inductiveProximitySensor.setVersion(LocalDateTime.of(2013, 1, 4, 12, 11));
		inductiveProximitySensor.getTags().add(sensorTag);
		return inductiveProximitySensor;
	}

	public static ComponentTemplate createCylinderWith34ValveWith2Speeds() {
		ComponentTemplate cylinder3 = new ComponentTemplate();
		cylinder3.setName(CYLINDER_WITH_3_4_VALVE_WITH_2_SPEEDS);
		cylinder3.setVersion(LocalDateTime.of(2015, 11, 11, 17, 12));
		cylinder3.getTags().add(actuatorTag);
		return cylinder3;
	}

	public static ComponentTemplate createCylinderWith34Valve() {
		ComponentTemplate cylinder2 = new ComponentTemplate();
		cylinder2.setName(CYLINDER_WITH_3_4_VALVE);
		cylinder2.setVersion(LocalDateTime.of(2015, 11, 11, 17, 12));
		cylinder2.getTags().add(actuatorTag);
		return cylinder2;
	}

	public static ComponentTemplate createCylinderWith23Valve() {
		ComponentTemplate cylinder1 = new ComponentTemplate();
		cylinder1.setName(CYLINDER_WITH_2_3_VALVE);
		cylinder1.setVersion(LocalDateTime.of(2015, 11, 11, 17, 12));
		cylinder1.getTags().add(actuatorTag);
		return cylinder1;
	}

	public static ComponentTemplate createElectricMotorGroupWithProtectiveCirquitBreaker2Directions() {
		ComponentTemplate motorGroup4 = new ComponentTemplate();
		motorGroup4.setName(
				ELECTRIC_MOTOR_GROUP_WITH_MOTOR_PROTECTIVE_CIRQUIT_BRAKER_PKZM_AND_2_DIRECTIONS);
		motorGroup4.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup4.getTags().add(actuatorTag);
		return motorGroup4;
	}

	public static ComponentTemplate createElectricMotorGroupWithProtectiveCirquitBraker() {
		ComponentTemplate motorGroup3 = new ComponentTemplate();
		motorGroup3.setName(ELECTRIC_MOTOR_GROUP_WITH_MOTOR_PROTECTIVE_CIRQUIT_BRAKER_PKZM);
		motorGroup3.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup3.getTags().add(actuatorTag);
		return motorGroup3;
	}

	public static ComponentTemplate createElectricMotorGroupWithSoftStarter() {
		ComponentTemplate motorGroup2 = new ComponentTemplate();
		motorGroup2.setName(ELECTRIC_MOTOR_GROUP_WITH_SOFT_STARTER);
		motorGroup2.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup2.getTags().add(actuatorTag);
		return motorGroup2;
	}

	public static ComponentTemplate createElectricMotorGroupWithFrequencyController() {
		ComponentTemplate motorGroup1 = new ComponentTemplate();
		motorGroup1.setName(ELECTRIC_MOTOR_GROUP_WITH_FREQUENCY_CONTROLLER);
		motorGroup1.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup1.getTags().add(actuatorTag);
		return motorGroup1;
	}

	public static Tag createActuatorTag() {
		Tag actuatorTag = new Tag();
		actuatorTag.setName("Actuator");
		return actuatorTag;
	}

	public static Tag createSensorTag() {
		Tag actuatorTag = new Tag();
		actuatorTag.setName("Sensor");
		return actuatorTag;
	}

	public static List<Tag> getTags() {
		List<Tag> tags = new ArrayList<>();
		tags.add(actuatorTag);
		tags.add(sensorTag);
		return tags;
	}

}

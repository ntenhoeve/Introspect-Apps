package nth.meyn.controls.configurator.dom.component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class ComponentTemplateService {
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

	private List<ComponentTemplate> createExampleComponentTemplates() {
		List<ComponentTemplate> componentTemplates=new ArrayList<>();
		ComponentTemplateTag actuatorTag=new ComponentTemplateTag();
		actuatorTag.setName("Actuator");
		ComponentTemplateTag inputTag=new ComponentTemplateTag();
		inputTag.setName("Input");
		
		ComponentTemplate motorGroup = new ComponentTemplate();
		motorGroup.setName("Electric Motor Group");
		motorGroup.setVersion(LocalDateTime.of(2016, 12, 5, 12, 13));
		motorGroup.getTags().add(actuatorTag);
		componentTemplates.add(motorGroup);
		
		
		ComponentTemplate lift = new ComponentTemplate();
		lift.setName("Lift");
		lift.setVersion(LocalDateTime.of(2015, 11, 11, 17, 12));
		lift.getTags().add(actuatorTag);
		componentTemplates.add(lift);
	
		ComponentTemplate o2Sensor = new ComponentTemplate();
		o2Sensor.setName("O2 conentration sensor");
		o2Sensor.setVersion(LocalDateTime.of(2014, 1, 4, 12, 11));
		o2Sensor.getTags().add(inputTag);
		componentTemplates.add(o2Sensor);
		
		return componentTemplates;
	}
	
	public List<ComponentTemplateTag> allComponentTemplateTags() {
		return createExampleComponentTemplateTags();
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

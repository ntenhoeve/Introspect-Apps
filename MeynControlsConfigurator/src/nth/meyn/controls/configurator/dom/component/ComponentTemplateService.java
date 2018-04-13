package nth.meyn.controls.configurator.dom.component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.meyn.controls.configurator.dom.named.Tag;

public class ComponentTemplateService {


	public List<ComponentTemplate> allComponentTemplates() {
		return ComponentTemplateExampleFactory.createComponents();
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
					for ( Tag tag : componentTemplate.getTags()) {
						if (tag.getName()!=null && tag.getName().toLowerCase().contains(lowerTextToFind)) {
							return true;
						}
					}
					return false;
				}
			}
		};
	}

	
	
	public List<Tag> allComponentTemplateTags() {
		return ComponentTemplateExampleFactory.getTags();
	}
	
//	public static ComponentTemplate get(String componentTemplateNameToFind) {
//		List<ComponentTemplate> componentTemplates = createExampleComponentTemplates();
//		for (ComponentTemplate componentTemplate : componentTemplates) {
//			if (componentTemplate.getName().equals(componentTemplateNameToFind)) {
//				return componentTemplate;
//			}
//		}
//		return null;
//	}


}

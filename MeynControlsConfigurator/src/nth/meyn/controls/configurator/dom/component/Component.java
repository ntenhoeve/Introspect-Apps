package nth.meyn.controls.configurator.dom.component;

import nth.introspect.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription = "A specific instance of an electric component (ComponentTemplate) like relay or motor, or a group of components such as a display panel, motor group, etc.")
public class Component {

	private String name;
	private String abbreviation;
	private ComponentTemplate componentTemplate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public ComponentTemplate getComponentTemplate() {
		return componentTemplate;
	}
	public void setComponentTemplate(ComponentTemplate componentTemplate) {
		this.componentTemplate = componentTemplate;
	}
	
	
}

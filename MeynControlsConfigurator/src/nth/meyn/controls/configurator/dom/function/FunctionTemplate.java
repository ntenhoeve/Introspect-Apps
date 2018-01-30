package nth.meyn.controls.configurator.dom.function;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.description.Description;
import nth.meyn.controls.configurator.dom.component.Component;

@Description(englishDescription = "A part of equipment that is responsible for one function (e.g. transport, turning, plucking, lifting, temperature control) made out of none or more components")
public class FunctionTemplate {
	private String name;
	private String abbreviation;
	private LocalDateTime version;
	private int order;
	private List<Component> components;
	
	public FunctionTemplate() {
		components=new ArrayList<>();
	}
	
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
	public LocalDateTime getVersion() {
		return version;
	}
	public void setVersion(LocalDateTime version) {
		this.version = version;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}
	
	

		
	// TODO add attributes
	// TODO add electric schematic template (EPlan macro's)
	// TODO add plc program template
	// TODO add display program template
	// TODO add FAT template doc
	// TODO add IO

	
}

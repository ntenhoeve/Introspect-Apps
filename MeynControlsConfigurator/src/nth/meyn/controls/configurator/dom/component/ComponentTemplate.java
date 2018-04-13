package nth.meyn.controls.configurator.dom.component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.description.Description;
import nth.meyn.controls.configurator.dom.function.FunctionOrComponent;
import nth.meyn.controls.configurator.dom.named.Tag;

@Description(englishDescription = "A electric component like a sensor oractuator such as a valve, relays or motor. A component can have multiple variants, e.g. a proximity sensor can have multiple types or brands. A ComponentTemplate can be referenced by many FunctionTemplates)")
public class ComponentTemplate implements FunctionOrComponent {
	private String name;
	private String abbreviation;
	private LocalDateTime version;
	private List<Tag> tags;
	// TODO add attributes
	// TODO add electric schematic template (EPlan macro's)
	// TODO add plc program template
	// TODO add display program template
	// TODO add FAT template doc
	// TODO add IO

	public ComponentTemplate() {
		tags=new ArrayList<>();
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	

}

package nth.meyn.controls.configurator.dom.component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription = "A electric component like relay or motor, or a group of components such as a display panel, motor group, etc.")
public class ComponentTemplate  {
	private String name;
	private LocalDateTime version;
	private List<ComponentTemplateTag> tags;
	// TODO add attributes
	// TODO add electric schematic template (EPlan macro's)
	// TODO add plc program template
	// TODO add display program template
	// TODO add FAT template doc
	// TODO add IO

	public ComponentTemplate() {
		tags=new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getVersion() {
		return version;
	}

	public void setVersion(LocalDateTime version) {
		this.version = version;
	}

	public List<ComponentTemplateTag> getTags() {
		return tags;
	}

	public void setTags(List<ComponentTemplateTag> tags) {
		this.tags = tags;
	}
	
	

}

package nth.meyn.controls.configurator.dom.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.description.Description;
import nth.meyn.controls.configurator.dom.equipment.EquipmentTemplateRule;

@Description(englishDescription = "An generic project template to generate e.g. a evisceration line panel or whole leg deboner")
public class ProjectTemplate {

	private String name;
	private LocalDateTime version;
	private List<EquipmentTemplateRule> equipmentTemplateRule;

	public ProjectTemplate() {
		equipmentTemplateRule=new ArrayList<>();
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

	public List<EquipmentTemplateRule> getEquipmentTemplateRules() {
		return equipmentTemplateRule;
	}

	public void setEquipmentTemplateRules(List<EquipmentTemplateRule> equipmentTemplates) {
		this.equipmentTemplateRule = equipmentTemplates;
	}


	
	
}

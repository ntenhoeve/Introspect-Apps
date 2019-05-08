package nth.meyn.controls.configurator.dom.equipment;

import java.util.List;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription = "A machine or software product (e.g. a plucker 3 of EquipmentTempate Plucker-JM64 with a bottom beam and water valve options)")
public class Equipment {
	private EquipmentTemplate equipmentTemplate;
	private List<EquipmentOption> options;
	private int instanceNumber;

	public Equipment() {
		instanceNumber = 1;
	}

	public EquipmentTemplate getEquipmentTemplate() {
		return equipmentTemplate;
	}

	public void setEquipmentTemplate(EquipmentTemplate equipmentTemplate) {
		this.equipmentTemplate = equipmentTemplate;
		// todo update options;
	}

	public List<EquipmentOption> getOptions() {
		return options;
	}

	public void setOptions(List<EquipmentOption> options) {
		this.options = options;
	}

	public int getInstanceNumber() {
		return instanceNumber;
	}

	public void setInstanceNumber(int instanceNumber) {
		this.instanceNumber = instanceNumber;
	}

	public String getName() {
		if (equipmentTemplate == null) {
			return "";
		} else {
			return new TitleBuilder().append(equipmentTemplate.getName()).append(instanceNumber).toString();
		}
	}

}

package nth.meyn.controls.configurator.dom.equipment;

import nth.meyn.controls.configurator.dom.named.Named;

public class EquipmentTemplateTag implements Named {

	
	private String name;
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

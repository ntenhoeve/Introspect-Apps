package nth.meyn.controls.configurator.dom.component;

import nth.meyn.controls.configurator.dom.named.Named;

public class ComponentTemplateTag implements Named {
	private String name;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

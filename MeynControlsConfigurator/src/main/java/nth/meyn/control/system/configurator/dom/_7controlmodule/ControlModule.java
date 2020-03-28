package nth.meyn.control.system.configurator.dom._7controlmodule;

import nth.meyn.control.system.configurator.dom.Multiplicity;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(defaultEnglish = ControlModule.DESCRIPTION)
public class ControlModule {
	static final String DESCRIPTION = "A Control Module typically a sensor (e.g. a emergency stop) or actuator (e.g. a pneumatic valve) or a combination of sensors and actuators (e.g. a motor group or frequency controlled drive)";
	private String name;
	private ControlModuleTemplate template;
	private Multiplicity multiplicity;

	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.INFO_CIRCLE)
	public String info() {
		return getClass().getSimpleName() + "= " + DESCRIPTION;
	}

	@Order(value = 1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(value = 10)
	public ControlModuleTemplate getTemplate() {
		return template;
	}

	public void setTemplate(ControlModuleTemplate template) {
		this.template = template;
	}

	@Order(value = 20)
	public Multiplicity getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

}

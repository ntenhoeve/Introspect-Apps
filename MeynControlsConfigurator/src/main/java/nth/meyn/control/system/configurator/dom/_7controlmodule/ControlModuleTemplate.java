package nth.meyn.control.system.configurator.dom._7controlmodule;

import java.util.List;

import nth.meyn.control.system.configurator.dom.alarm.Alarm;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(englishDescription = ControlModuleTemplate.DESCRIPTION)
public class ControlModuleTemplate {
	static final String DESCRIPTION = "A template for a Controll Module (sometimes also called a typical), e.g.: a sensor, push button, pneumatic valve, motor group, or frequency controller";
	private String name;
	private String description;
	private List<Io> io;
	private List<Alarm> alarms;
	// TODO add PLC software template
	// TODO add HMI template
	// TODO add electrical template??

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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Order(value = 20)
	public List<Io> getIo() {
		return io;
	}

	public void setIo(List<Io> io) {
		this.io = io;
	}

	@Order(value = 30)
	public List<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}

}

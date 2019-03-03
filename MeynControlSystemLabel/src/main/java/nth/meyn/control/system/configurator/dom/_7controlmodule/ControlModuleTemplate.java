package nth.meyn.control.system.configurator.dom._7controlmodule;

import java.util.List;

import nth.meyn.control.system.configurator.dom.alarm.Alarm;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Description(englishDescription = "A template for a Controll Module (sometimes also called a typical), e.g.: a sensor, push button, pneumatic valve, motor group, or frequency controller")
public class ControlModuleTemplate {
	private String name;
	private String description;
	private List<Io> io;
	private List<Alarm> alarms;
	// TODO add PLC software template
	// TODO add HMI template
	// TODO add electrical template??

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

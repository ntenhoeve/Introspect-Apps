package nth.meyn.control.system.configurator.dom._6equipmentmodule;

import java.util.List;

import nth.meyn.control.system.configurator.dom._7controlmodule.ControlModuleTemplate;
import nth.meyn.control.system.configurator.dom.alarm.Alarm;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Description(englishDescription = "A template for a functional group of control modules to achieve a specific process activity, e.g. a temperature controller, Conveyor, Deskinner, etc")
public class EquipmentModuleTemplate {

	private String name;
	private List<ControlModuleTemplate> controlModules;
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
	public List<ControlModuleTemplate> getControlModules() {
		return controlModules;
	}

	public void setControlModules(List<ControlModuleTemplate> controlModules) {
		this.controlModules = controlModules;
	}

	@Order(value = 20)
	public List<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}

}

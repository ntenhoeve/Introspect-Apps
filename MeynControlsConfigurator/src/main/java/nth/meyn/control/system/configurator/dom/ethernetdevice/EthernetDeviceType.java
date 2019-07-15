package nth.meyn.control.system.configurator.dom.ethernetdevice;

import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(englishDescription = EthernetDeviceType.DESCRIPTION)
public class EthernetDeviceType {

	static final String DESCRIPTION = "A device connected to the local Meyn Internet, e.g.: A PLC, HMI, Camera Grading System, Distribution Manager, Server Computer, Desktop Computer (not remote IO)";

	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.INFO_CIRCLE)
	public String info() {
		return getClass().getSimpleName() + "= " + DESCRIPTION;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}

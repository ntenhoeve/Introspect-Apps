package nth.meyn.control.system.configurator.dom.internetdevice;

import javax.validation.constraints.Pattern;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class InternetDevice {
	private static final String IPV4_ADDRESS_REGEXP = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private InternetDeviceType internetDeviceType;
	private String ipAddress;

	@Order(value = 1)
	public InternetDeviceType getInternetDeviceType() {
		return internetDeviceType;
	}

	public void setInternetDeviceType(InternetDeviceType internetDeviceType) {
		this.internetDeviceType = internetDeviceType;
	}

	@Order(value = 10)
	@Pattern(regexp = IPV4_ADDRESS_REGEXP)
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	// TODO IP Address:
	// depends on line nr, e.g.: slaughter line 1, 2 ,3
	// depends on internetDeviceType (PLC, HMI1, HMI2, etc)
	// depends on work unit, e.g. sl-ev rehanger or ev-ch rehanger
}

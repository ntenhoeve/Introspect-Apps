package nth.meyn.control.system.configurator.dom.ethernetdevice;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class InstanceNumberAndIpAddress {
	private static final String IPV4_ADDRESS_REGEXP = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	private static final int INSTANCE_NUMBER_DEFATAULT = 1;

	private int instanceNumber = INSTANCE_NUMBER_DEFATAULT;
	private String ipAddress;

	@Order(value = 1)
	@NotNull
	@Min(1)
	@Max(200)
	public int getInstanceNumber() {
		return instanceNumber;
	}

	public void setInstanceNumber(int instanceNumber) {
		this.instanceNumber = instanceNumber;
	}

	@Order(value = 20)
	@NotEmpty
	@Pattern(regexp = IPV4_ADDRESS_REGEXP)
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(instanceNumber).append(ipAddress).toString();
	}

}

package nth.meyn.control.system.configurator.dom.ethernetdevice;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class EthernetDeviceTemplate {

	private EthernetDeviceType internetDeviceType;
	private boolean required;
	private List<InstanceNumberAndIpAddress> instanceNumberAndIpAddresses;

	@Order(value = 10)
	@NotNull
	public EthernetDeviceType getInternetDeviceType() {
		return internetDeviceType;
	}

	public void setInternetDeviceType(EthernetDeviceType internetDeviceType) {
		this.internetDeviceType = internetDeviceType;
	}

	@Order(value = 20)
	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	@Order(value = 30)
	@NotEmpty
	public List<InstanceNumberAndIpAddress> getInstanceNumberAndIpAddresses() {
		return instanceNumberAndIpAddresses;
	}

	public void setInstanceNumberAndIpAddresses(List<InstanceNumberAndIpAddress> instanceNumberAndIpAddresses) {
		this.instanceNumberAndIpAddresses = instanceNumberAndIpAddresses;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(internetDeviceType).append(required ? "required" : "").toString();
	}

}

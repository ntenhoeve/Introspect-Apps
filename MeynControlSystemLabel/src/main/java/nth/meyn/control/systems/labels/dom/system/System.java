package nth.meyn.control.systems.labels.dom.system;

import javax.validation.constraints.Pattern;

import org.apache.bval.constraints.NotEmpty;

import nth.meyn.control.systems.labels.dom.productgroup.ProductGroup;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class System {
	private static final String IPV4_ADDRESS_REGEXP = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	private ProductGroup productGroup;
	private String name;
	private String conventionalIpAddress;
	private String meynConnectIpAddress;

	// @Order(sequenceNumber = 0)
	// @NotNull
	// public ProductGroup getProductGroup() {
	// return productGroup;
	// }
	//
	// public void setProductGroup(ProductGroup productGroup) {
	// this.productGroup = productGroup;
	// }

	@Order(sequenceNumber = 10)
	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(sequenceNumber = 20)
	@Pattern(regexp = IPV4_ADDRESS_REGEXP)
	public String getConventionalIpAddress() {
		return conventionalIpAddress;
	}

	public void setConventionalIpAddress(String conventionalIpAddress) {
		this.conventionalIpAddress = conventionalIpAddress;
	}

	@Order(sequenceNumber = 30)
	@Pattern(regexp = IPV4_ADDRESS_REGEXP)
	public String getMeynConnectIpAddress() {
		return meynConnectIpAddress;
	}

	public void setMeynConnectIpAddress(String meynConnectIpAddress) {
		this.meynConnectIpAddress = meynConnectIpAddress;
	}

	@Override
	public String toString() {
		StringBuilder id = new StringBuilder();
		if (productGroup != null) {
			id.append(productGroup);
			id.append("-");
		}
		id.append(name);
		return id.toString();
	}
}

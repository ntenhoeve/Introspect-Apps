package nth.meyn.control.systems.labels.dom.productgroup;

import org.apache.bval.constraints.NotEmpty;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class ProductGroup {

	private ProductGroup parent;
	private String name;

	@Order(sequenceNumber = 0)
	public ProductGroup getParent() {
		return parent;
	}

	public void setParent(ProductGroup parent) {
		this.parent = parent;
	}

	@Order(sequenceNumber = 10)
	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		if (parent != null) {
			StringBuilder path = new StringBuilder();
			path.append(parent.toString());
			path.append("-");
			path.append(name);
			return path.toString();
		} else {
			return name;
		}
	}

}

package nth.meyn.innovation.intake.dom.product;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private String name;
	private List<String> productCodes;

	public Product() {
		productCodes = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getProductCodes() {
		return productCodes;
	}

	public void setProductCodes(List<String> productCodes) {
		this.productCodes = productCodes;
	}

	@Override
	public String toString() {
		return name;
	}

}

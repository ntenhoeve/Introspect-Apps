package nth.meyn.connect.dom.module.maintenance;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.order.Order;

public class Asset {

	private String name;
	private List<Asset> assets;

	public Asset() {
		assets = new ArrayList<>();
	}

	@Order(sequenceNumber=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(sequenceNumber=20)
	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	@Override
	public String toString() {
		return name;
	}

}

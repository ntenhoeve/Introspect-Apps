package nth.meyn.connect.dom.module.maintenance;

import java.util.ArrayList;
import java.util.List;

public class Asset {

	private List<Asset> assets;

	public Asset() {
		assets = new ArrayList<>();
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
}

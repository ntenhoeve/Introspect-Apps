package nth.meyn.connect.dom.asset;

import java.util.List;
import java.util.stream.Collectors;

import nth.meyn.connect.dom.ipadress.IPAddress;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription="A Meyn line such as a container system, De-feathering line, evisceration line, spin chiller, deboning machines, etc...")
public class MeynLine extends Asset {
	private IPAddress linePLC;

	public MeynLine(String name) {
		super(name);
	}

	public IPAddress getLinePLC() {
		return linePLC;
	}

	public void setLinePLC(IPAddress linePLC) {
		this.linePLC = linePLC;
	}
	
	public List<Asset> getMeynLines() {
		return getAssets().stream().filter(asset->asset instanceof MeynEquipment).collect(Collectors.toList());
	}
}

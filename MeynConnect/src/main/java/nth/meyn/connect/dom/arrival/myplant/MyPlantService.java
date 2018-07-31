package nth.meyn.connect.dom.arrival.myplant;

import nth.meyn.connect.dom.arrival.flock.FlockService;
import nth.meyn.connect.dom.arrival.grower.GrowerService;
import nth.meyn.connect.dom.arrival.lot.LotService;
import nth.meyn.connect.dom.arrival.transport.TransportService;
import nth.meyn.connect.dom.asset.Asset;
import nth.meyn.connect.dom.asset.AssetExampleFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.serviceobjectchildren.ServiceObjectChildren;

@ServiceObjectChildren(serviceClasses = { GrowerService.class, FlockService.class,
		TransportService.class, LotService.class })
public class MyPlantService {
	public Asset myPlant() {
		return AssetExampleFactory.create();
	}
}

package nth.meyn.connect.dom.module.maintenance;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class MaintenanceOrderService {
	public List<MaintenanceOrder> activeMaintenanceOrders() {
		return new ArrayList<>();
	}

	public List<MaintenanceOrder> maintenanceOrderHistory() {
		return new ArrayList<>();
	}

	@ParameterFactory
	public void createMaintenanceOrder(MaintenanceOrder maintenanceOrder) {

	}

}

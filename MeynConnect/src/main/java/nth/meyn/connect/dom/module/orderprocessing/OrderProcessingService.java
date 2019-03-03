package nth.meyn.connect.dom.module.orderprocessing;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@FontIcon(fontIconUrl=FontAwesomeUrl.BALANCE_SCALE)
@DisplayName(englishName="ProductionOrder Processing")
public class OrderProcessingService {

	@Order(value=1)
	public List<ProductionOrder> feet() {
		return new ArrayList<>();
	}
	
	@Order(value=2)
	public List<ProductionOrder> wholeBirds() {
		return new ArrayList<>();
	}
	
	@Order(value=3)
	public List<ProductionOrder> wings() {
		return new ArrayList<>();
	}
	
	@Order(value=4)
	public List<ProductionOrder> breastCaps() {
		return new ArrayList<>();
	}
	
	@Order(value=5)
	public List<ProductionOrder> legs() {
		return new ArrayList<>();
	}
}

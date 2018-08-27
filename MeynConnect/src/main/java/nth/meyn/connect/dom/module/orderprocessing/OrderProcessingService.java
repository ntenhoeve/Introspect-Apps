package nth.meyn.connect.dom.module.orderprocessing;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

@FontIcon(fontIconUrl=FontAwesomeUrl.BALANCE_SCALE)
@DisplayName(englishName="ProductionOrder Processing")
public class OrderProcessingService {

	@Order(sequenceNumber=1)
	public List<ProductionOrder> feet() {
		return new ArrayList<>();
	}
	
	@Order(sequenceNumber=2)
	public List<ProductionOrder> wholeBirds() {
		return new ArrayList<>();
	}
	
	@Order(sequenceNumber=3)
	public List<ProductionOrder> wings() {
		return new ArrayList<>();
	}
	
	@Order(sequenceNumber=4)
	public List<ProductionOrder> breastCaps() {
		return new ArrayList<>();
	}
	
	@Order(sequenceNumber=5)
	public List<ProductionOrder> legs() {
		return new ArrayList<>();
	}
}

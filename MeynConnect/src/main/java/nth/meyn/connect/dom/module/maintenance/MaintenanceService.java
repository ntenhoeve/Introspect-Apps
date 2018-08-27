package nth.meyn.connect.dom.module.maintenance;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.connect.dom.asset.Asset;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.serviceobjectchildren.ServiceObjectChildren;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

@FontIcon(fontIconUrl=FontAwesomeUrl.WRENCH)
@DisplayName(englishName="Maintenance")
@ServiceObjectChildren(serviceClasses={MaintenanceOrderService.class})
public class MaintenanceService {

	public List<Asset> assets() {
		return new ArrayList();
	}
	
	
}

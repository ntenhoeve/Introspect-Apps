package nth.meyn.connect.dom.module.maintenance;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.introspect.layer5provider.reflection.behavior.icon.Icon;
import nth.introspect.layer5provider.reflection.behavior.serviceobjectchildren.ServiceObjectChildren;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;

@Icon(iconURL=FontAwesomeUrl.WRENCH)
@DisplayName(englishName="Maintenance")
@ServiceObjectChildren(serviceClasses={MaintenanceOrderService.class})
public class MaintenanceService {

	public List<Asset> assets() {
		return new ArrayList();
	}
	
	
}

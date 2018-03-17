package nth.meyn.connect.dom.module.maintenance;

import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.introspect.layer5provider.reflection.behavior.icon.Icon;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;

@Icon(iconURL=FontAwesomeUrl.WRENCH)
@DisplayName(englishName="Maintenance")
public class MaintenanceService {

	public List<Asset> assets() {
		return null;
	}
}

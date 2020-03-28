package nth.meyn.connect.dom.module.maintenance;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.connect.dom.asset.Asset;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;

@FontIcon(fontIconUrl=FontAwesomeUrl.WRENCH)
@DisplayName(defaultEnglish="Maintenance")
public class MaintenanceService {

	public List<Asset> assets() {
		return new ArrayList();
	}
	
	
}

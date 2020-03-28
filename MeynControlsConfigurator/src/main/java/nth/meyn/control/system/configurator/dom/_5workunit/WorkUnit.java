package nth.meyn.control.system.configurator.dom._5workunit;

import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(defaultEnglish = WorkUnit.DESCRIPTION)
public class WorkUnit {

	static final String DESCRIPTION = "A logical group of equipment that can be switched on or off together. This is typically a system or machine, e.g.: a scalder, plucker, rehanger. A workunits contains one or more Equipment Modules, e.g. a temperature conroller, line speed controller.";

	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.INFO_CIRCLE)
	public String info() {
		return getClass().getSimpleName() + "= " + DESCRIPTION;
	}
}

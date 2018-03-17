package nth.meyn.connect.dom.module.efficiency;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.introspect.layer5provider.reflection.behavior.icon.Icon;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;

@Icon(iconURL=FontAwesomeUrl.LINE_CHART)
@DisplayName(englishName = "Efficiency")
public class EfficiencyService {

	public Image overview() {
		return null;
	}

	@ParameterFactory
	public List<EfficiencyIssue> efficiencyIssues(EfficiencyIssueFilter efficiencyIssueFilter) {
		return new ArrayList<>();
	}
}

package nth.meyn.connect.dom.module.foodsafetyquality;

import java.util.List;

import javafx.scene.image.Image;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.introspect.layer5provider.reflection.behavior.icon.Icon;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;

@Icon(iconURL=FontAwesomeUrl.SHIELD)
@DisplayName(englishName="Food safety & Quality")
public class FoodSafetyAndQualityService {

	public Image condemned() {
		return null;//Eg small birds and DOAS
	}

	public Image deceases() {
		return null;//e.g. Veterinarian stations, QA stations or vision grading systems
	}
	
	public Image downgrades() {
		return null;//e.g. Veterinarian stations, QA stations or vision grading systems
	}
	
	public List<GradingSystem> visionGrading() {
		return null;//vision grading systems
	}
}

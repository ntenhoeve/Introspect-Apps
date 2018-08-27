package nth.meyn.connect.dom.module.foodsafetyquality;

import java.util.List;

import javafx.scene.image.Image;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

@FontIcon(fontIconUrl=FontAwesomeUrl.SHIELD)
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

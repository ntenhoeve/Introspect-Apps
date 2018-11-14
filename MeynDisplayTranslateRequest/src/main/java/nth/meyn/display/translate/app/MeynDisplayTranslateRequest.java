package nth.meyn.display.translate.app;

import java.util.Arrays;
import java.util.List;

import nth.meyn.display.translate.dom.translate.TranslateService;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.layer5provider.reflection.behavior.applicationicon.ApplicationIcon;
import nth.reflect.fw.ui.style.MaterialColorPalette;
import nth.reflect.fw.ui.style.ReflectColors;

@ApplicationIcon(iconURL = "reflect-class-resource://nth.meyn.display.translate.app.MeynDisplayTranslateRequest/meyn32x32.png")
public class MeynDisplayTranslateRequest extends ReflectApplicationForJavaFX {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(TranslateService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return null;
	}

	@Override
	public ReflectColors getColors() {
		return new ReflectColors(MaterialColorPalette.green700(), MaterialColorPalette.orange500(),
				MaterialColorPalette.white());
	}

}

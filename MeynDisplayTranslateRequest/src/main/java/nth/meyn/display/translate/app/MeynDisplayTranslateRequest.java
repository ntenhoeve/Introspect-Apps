package nth.meyn.display.translate.app;

import java.util.Arrays;
import java.util.List;

import nth.introspect.ui.style.ContentColor;
import nth.introspect.ui.style.MaterialColorPalette;
import nth.introspect.ui.style.basic.Color;
import nth.meyn.display.translate.dom.translate.TranslateService;
import nth.reflect.javafx.ReflectApplicationForJavaFX;

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
	public Color getPrimaryColor() {
		return MaterialColorPalette.TEAL;
	}

	@Override
	public Color getAccentColor() {
		return MaterialColorPalette.ORANGE;
	}

	@Override
	public ContentColor getContentColor() {
		return ContentColor.WHITE;
	}



}

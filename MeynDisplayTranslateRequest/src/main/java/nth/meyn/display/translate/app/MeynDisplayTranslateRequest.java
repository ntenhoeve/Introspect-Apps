package nth.meyn.display.translate.app;

import java.util.Arrays;
import java.util.List;

import nth.introspect.ui.swing.IntrospectApplicationForSwing;
import nth.meyn.display.translate.dom.translate.TranslateService;

public class MeynDisplayTranslateRequest extends IntrospectApplicationForSwing {

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

}

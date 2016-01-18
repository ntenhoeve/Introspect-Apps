package nth.meyn.display.translate.app;
import java.util.List;

import nth.introspect.generic.util.ClassList;
import nth.introspect.ui.swing.IntrospectApplicationForSwing;
import nth.meyn.display.translate.dom.translate.TranslateService;


public class MeynDisplayTranslateRequest extends IntrospectApplicationForSwing {

	public MeynDisplayTranslateRequest(String[] commandLineArguments) {
		super(commandLineArguments);
	}
	
	public static void main(String[] commandLineArguments) {
		new MeynDisplayTranslateRequest(commandLineArguments);
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return new ClassList(TranslateService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return null;
	}

}

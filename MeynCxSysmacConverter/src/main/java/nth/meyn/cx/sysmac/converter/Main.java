package nth.meyn.cx.sysmac.converter;

import java.util.Arrays;
import java.util.List;

import nth.reflect.fw.ui.swing.ReflecttApplicationForSwing;

public class Main extends ReflecttApplicationForSwing {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(CxOneSysmacService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return null;
	}

}
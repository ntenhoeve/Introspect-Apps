package nth.meyn.jetstreamscalder.app;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.jetstreamscalder.dom.scalder.JetStreamScalderService;
import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.ui.swing.ReflectApplicationForSwing;
import nth.reflect.infra.generic.xml.XmlConverter;

public class MeynJetStreamScalder extends ReflectApplicationForSwing {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		List<Class<?>> serviceClasses = new ArrayList<>();
		serviceClasses.add(JetStreamScalderService.class);
		return serviceClasses;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		List<Class<?>> infrastructureClasses = new ArrayList<>();
		infrastructureClasses.add(XmlConverter.class);
		return infrastructureClasses;
	}

	@Override
	public ColorProvider getColorProvider() {
		return new ColorProvider(new Color(0, 120, 91), MaterialColorPalette.orange500(), MaterialColorPalette.white());
	}

}

package nth.meyn.jetstreamscalder.app;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.jetstreamscalder.dom.scalder.JetStreamScalderService;
import nth.reflect.fw.ui.style.MaterialColorPalette;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.swing.ReflecttApplicationForSwing;
import nth.reflect.infra.generic.xml.XmlConverter;

public class MeynJetStreamScalder extends ReflecttApplicationForSwing {

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
	public ReflectColors getColors() {
		return new ReflectColors(new Color(0, 120, 91), MaterialColorPalette.orange500(), MaterialColorPalette.white());
	}

}

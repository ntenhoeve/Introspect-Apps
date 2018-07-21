package nth.meyn.jetstreamscalder.app;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.generic.xml.XmlConverter;
import nth.introspect.ui.swing.IntrospectApplicationForSwing;
import nth.meyn.jetstreamscalder.dom.scalder.JetStreamScalderService;

public class MeynJetStreamScalder extends IntrospectApplicationForSwing {

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

}

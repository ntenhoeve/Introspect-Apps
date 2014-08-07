package nth.meyn.innovation.intake.app;

import nth.introsepect.ui.swing.IntrospectInitializerForSwing;
import nth.introspect.Introspect;
import nth.meyn.innovation.intake.dom.capitalsalesintake.CapitalSalesIntakeService;
import nth.meyn.innovation.intake.dom.projectlaunch.ProjectLaunchService;

public class MeynInnovationIntake {

	public static void main(String[] args) {
		IntrospectInitializerForSwing initializer = new IntrospectInitializerForSwing(
				new MeynInnovationIntake());
		initializer.registerFrontEndServiceClass(CapitalSalesIntakeService.class);
		initializer.registerFrontEndServiceClass(ProjectLaunchService.class);
		Introspect.init(initializer);
	}

}

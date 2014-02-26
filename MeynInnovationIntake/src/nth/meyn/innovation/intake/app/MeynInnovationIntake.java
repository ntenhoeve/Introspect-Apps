package nth.meyn.innovation.intake.app;

import nth.introsepect.ui.swing.SwingIntrospectInitializer;
import nth.introspect.Introspect;
import nth.meyn.innovation.intake.dom.capitalsalesintake.CapitalSalesIntakeService;
import nth.meyn.innovation.intake.dom.projectlaunch.ProjectLaunchService;

public class MeynInnovationIntake {

	public static void main(String[] args) {
		SwingIntrospectInitializer initializer = new SwingIntrospectInitializer(
				new MeynInnovationIntake());
		initializer.addServiceClass(CapitalSalesIntakeService.class);
		initializer.addServiceClass(ProjectLaunchService.class);
		Introspect.init(initializer);
	}

}

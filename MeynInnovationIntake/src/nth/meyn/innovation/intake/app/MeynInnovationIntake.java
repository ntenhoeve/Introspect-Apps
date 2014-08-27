package nth.meyn.innovation.intake.app;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.report.msexcel.item.ExcelReportFactory;
import nth.introspect.ui.swing.IntrospectApplicationForSwing;
import nth.meyn.innovation.intake.dom.capitalsalesintake.CapitalSalesIntakeService;
import nth.meyn.innovation.intake.dom.projectlaunch.ProjectLaunchService;

public class MeynInnovationIntake extends IntrospectApplicationForSwing {

	public MeynInnovationIntake(String[] args) {
		super(args);
	}

	public static void main(String[] args) {
		new MeynInnovationIntake(args);
	}

	@Override
	public List<Class<?>> getFrontEndServiceClasses() {
		List<Class<?>> frontEndServiceClasses=new ArrayList<>();
		frontEndServiceClasses.add(CapitalSalesIntakeService.class);
		frontEndServiceClasses.add(ProjectLaunchService.class);
		return frontEndServiceClasses;
	}

	@Override
	public List<Class<?>> getBackEndServiceClasses() {
		List<Class<?>> backEndServiceClasses=new ArrayList<>();
		backEndServiceClasses.add(ExcelReportFactory.class);
		return backEndServiceClasses;
	}

}

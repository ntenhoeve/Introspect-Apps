package nth.meyn.control.systems;

import java.util.Arrays;
import java.util.List;

import nth.meyn.control.systems.dom.customer.CustomerRepository;
import nth.meyn.control.systems.dom.customer.CustomerService;
import nth.meyn.control.systems.dom.customerorder.CustomerOrderRepository;
import nth.meyn.control.systems.dom.db.MeynControlDepartmentDbConfigutation;
import nth.meyn.control.systems.dom.employee.EmployeeRepository;
import nth.meyn.control.systems.dom.employee.EmployeeService;
import nth.meyn.control.systems.dom.project.ProjectService;
import nth.meyn.control.systems.dom.timeline.TimeLineExcelReport;
import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.gui.style.ReflectColors;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.ui.swing.ReflecttApplicationForSwing;

public class MeynControlSystems extends ReflecttApplicationForSwing {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(ProjectService.class, CustomerService.class, EmployeeService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(MeynControlDepartmentDbConfigutation.class, CustomerRepository.class,
				EmployeeRepository.class, CustomerOrderRepository.class, TimeLineExcelReport.class);
	}

	@Override
	public ReflectColors getColors() {
		return new ReflectColors(new Color(0, 120, 91), MaterialColorPalette.orange500(), MaterialColorPalette.white());
	}

}

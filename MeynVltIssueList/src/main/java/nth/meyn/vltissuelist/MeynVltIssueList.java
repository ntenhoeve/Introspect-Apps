package nth.meyn.vltissuelist;

import java.util.Arrays;
import java.util.List;

import nth.meyn.vltissuelist.dom.customer.CustomerRepository;
import nth.meyn.vltissuelist.dom.issue.IssueExcelRepository2;
import nth.meyn.vltissuelist.dom.issue.IssueService;
import nth.reflect.fw.ui.swing.ReflecttApplicationForSwing;

public class MeynVltIssueList extends ReflecttApplicationForSwing {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(IssueService.class);
	}

	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(IssueExcelRepository2.class, CustomerRepository.class,
				nth.meyn.vltissuelist.dom.customer.MeynControlDepartmentDbConfigutation.class);
	}
}

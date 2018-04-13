package nth.meyn.vltissuelist;

import java.util.Arrays;
import java.util.List;

import nth.introspect.ui.swing.IntrospectApplicationForSwing;
import nth.meyn.vltissuelist.dom.customer.CustomerRepository;
import nth.meyn.vltissuelist.dom.issue.IssueExcelRepository2;
import nth.meyn.vltissuelist.dom.issue.IssueService;

public class MeynVltIssueList extends IntrospectApplicationForSwing {

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

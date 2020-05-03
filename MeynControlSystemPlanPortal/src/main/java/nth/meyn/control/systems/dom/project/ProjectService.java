package nth.meyn.control.systems.dom.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nth.meyn.control.systems.dom.customerorder.CustomerOrder;
import nth.meyn.control.systems.dom.customerorder.CustomerOrderRepository;
import nth.meyn.control.systems.dom.factoryorder.FactoryOrder;
import nth.meyn.control.systems.dom.timeline.TimeLineExcelReport;
import nth.reflect.fw.stream.DownloadStream;

public class ProjectService {

	private final CustomerOrderRepository customerOrderRepository;
	private final TimeLineExcelReport timeLineExcelReport;

	public ProjectService(CustomerOrderRepository customerOrderRepository, TimeLineExcelReport timeLineExcelReport) {
		this.customerOrderRepository = customerOrderRepository;
		this.timeLineExcelReport = timeLineExcelReport;
	}

	public List<CustomerOrder> allCustomerOrders() {
		List<CustomerOrder> customerOrders = customerOrderRepository
				.allCustomerOrders();
		return customerOrders;
	}

	public List<Project> allProjects() {
		List<CustomerOrder> customerOrders = customerOrderRepository
				.allCustomerOrders();
		List<Project> projects = getProjects(customerOrders);
		return projects;
	}
	
	
	public DownloadStream createTimeLineOverview() throws IOException {
		return timeLineExcelReport.createTimeLineOverview();
	}

	private List<Project> getProjects(List<CustomerOrder> customerOrders) {
		List<Project> projects = new ArrayList<>();
		for (CustomerOrder customerOrder : customerOrders) {
			List<FactoryOrder> factoryOrders = customerOrder.getFactoryOrders();
			for (FactoryOrder factoryOrder : factoryOrders) {
				Project project = new Project(customerOrder, factoryOrder);
				projects.add(project);
			}
		}
		return projects;
	}

}

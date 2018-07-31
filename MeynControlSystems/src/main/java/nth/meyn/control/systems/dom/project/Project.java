package nth.meyn.control.systems.dom.project;

import java.util.Date;

import nth.meyn.control.systems.dom.customer.Customer;
import nth.meyn.control.systems.dom.customerorder.CustomerOrder;
import nth.meyn.control.systems.dom.factoryorder.FactoryOrder;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

/**
 * Wraper class for an {@link CustomerOrder} and {@link FactoryOrder}
 * @author nilsth
 *
 */
public class Project {

	private final Date exWorksDate;
	private final Customer customer;
	private final Integer customerOrderNumber;
	private final Integer projectRelatedCosts;
	private final String factoryOrderName;

	public Project(CustomerOrder customerOrder, FactoryOrder factoryOrder) {
		exWorksDate=customerOrder.getExWorksDate();
		customer=customerOrder.getCustomer();
		customerOrderNumber=customerOrder.getCustomerOrderNumber();
		projectRelatedCosts=customerOrder.getProjectRelatedCosts();
		factoryOrderName=factoryOrder.getName();
	}

	@Format(pattern="Y - ww")
	@Order(sequenceNumber=1)
	public Date getExWorksDate() {
		return exWorksDate;
	}

	@Order(sequenceNumber=2)
	public Customer getCustomer() {
		return customer;
	}

	@Order(sequenceNumber=3)
	@Format(pattern="######")
	public Integer getCustomerOrderNumber() {
		return customerOrderNumber;
	}

	@Order(sequenceNumber=4)
	@Format(pattern="######")
	public Integer getProjectRelatedCosts() {
		return projectRelatedCosts;
	}

	@Order(sequenceNumber=5)
	public String getFactoryOrderName() {
		return factoryOrderName;
	}
	
	
}

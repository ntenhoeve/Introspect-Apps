package nth.meyn.control.systems.dom.customerorder;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import nth.meyn.control.systems.dom.customer.Customer;
import nth.meyn.control.systems.dom.factoryorder.FactoryOrder;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.infra.hibernate.entity.EntityId;

/**
 * @author nilsth
 *
 */
@Entity
@Table(name = "projectdata")
public class CustomerOrder extends EntityId {
	private boolean deleted;
	private Integer customerOrderNumber;
	private Integer projectRelatedCosts;
	private String Info;
	private Date exWorksDate;
	private Customer customer;
	private List<FactoryOrder> factoryOrders;


	@Hidden(propertyHiddenFor=HiddenFor.TABLES_AND_FORMS)
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	@Order(sequenceNumber=1)
	@Format(pattern = "Y - ww")
	public Date getExWorksDate() {
		return exWorksDate;
	}

	public void setExWorksDate(Date exWorksDate) {
		this.exWorksDate = exWorksDate;
	}

	
	
	@Order(sequenceNumber=2)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="Location", referencedColumnName="Location", insertable=false, updatable=false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Order(sequenceNumber=3)
	@Format(pattern = "######")
	@Column(name = "Proj_Order")
	public Integer getCustomerOrderNumber() {
		return customerOrderNumber;
	}

	public void setCustomerOrderNumber(Integer customerOrderNumber) {
		this.customerOrderNumber = customerOrderNumber;
	}

	
	@Order(sequenceNumber=4)
	@Format(pattern = "######")
	@Column(name = "PRC")
	public Integer getProjectRelatedCosts() {
		return projectRelatedCosts;
	}

	public void setProjectRelatedCosts(Integer projectRelatedCosts) {
		this.projectRelatedCosts = projectRelatedCosts;
	}

	@Order(sequenceNumber=5)
	public String getInfo() {
		return Info;
	}

	public void setInfo(String info) {
		Info = info;
	}

	@OneToMany()
	@JoinColumn(name="ProjectID")
	public List<FactoryOrder> getFactoryOrders() {
		return factoryOrders;
	}

	public void setFactoryOrders(List<FactoryOrder> factoryOrders) {
		this.factoryOrders = factoryOrders;
	}

	@Transient
	public boolean isCompleted() {
		for (FactoryOrder factoryOrder: getFactoryOrders()) {
			if (!factoryOrder.isCompleted()) {
				return false;
			}
		}
		return true;
		
	}
	
	@Override
	public String toString() {
		return new TitleBuilder().append(customerOrderNumber).append(projectRelatedCosts).append(customer).toString();
	}

}

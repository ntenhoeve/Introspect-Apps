package nth.meyn.control.systems.dom.customerorder;

import java.util.List;

import nth.meyn.control.systems.dom.db.MeynControlDepartmentDbConfigutation;
import nth.reflect.infra.hibernate.HibernateRepository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CustomerOrderRepository extends HibernateRepository<CustomerOrder> {

	public CustomerOrderRepository(
			MeynControlDepartmentDbConfigutation configutation) {
		super(configutation);
	}

	@SuppressWarnings("unchecked")
	public List<CustomerOrder> allCustomerOrders() {
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(CustomerOrder.class)
				.add(Restrictions.eq("deleted", false))
				.addOrder(Order.asc("exWorksDate"));
		List<CustomerOrder> customerOrders = criteria.list();
		return customerOrders;
	}

}

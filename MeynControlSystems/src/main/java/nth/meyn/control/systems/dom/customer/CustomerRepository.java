package nth.meyn.control.systems.dom.customer;

import java.util.List;

import nth.introspect.infrastructure.hibernate.HibernateRepository;
import nth.meyn.control.systems.dom.db.MeynControlDepartmentDbConfigutation;

public class CustomerRepository extends HibernateRepository<Customer> {

	public CustomerRepository(MeynControlDepartmentDbConfigutation hibernateConfiguration) {
		super(hibernateConfiguration);
	}

	public static void main(String[] args) {//TODO move to JUNIT test and move MySqlConnect to Maven Test scope
		CustomerRepository customerRepository=new CustomerRepository(new MeynControlDepartmentDbConfigutation());
		List<Customer> customers = customerRepository.getAll();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}
}

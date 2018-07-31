package nth.meyn.vltissuelist.dom.customer;

import nth.reflect.infra.hibernate.HibernateRepository;

public class CustomerRepository extends HibernateRepository<Customer> {

	public CustomerRepository(MeynControlDepartmentDbConfigutation hibernateConfiguration) {
		super(hibernateConfiguration);
	}

}

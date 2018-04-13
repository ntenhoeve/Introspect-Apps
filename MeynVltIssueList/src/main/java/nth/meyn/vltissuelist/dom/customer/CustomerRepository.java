package nth.meyn.vltissuelist.dom.customer;

import nth.introspect.infrastructure.hibernate.HibernateRepository;

public class CustomerRepository extends HibernateRepository<Customer> {

	public CustomerRepository(MeynControlDepartmentDbConfigutation hibernateConfiguration) {
		super(hibernateConfiguration);
	}

}

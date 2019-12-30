package nth.meyn.control.systems.dom.customer;

import java.util.List;

public class CustomerService {

	private final CustomerRepository customerRepository; 
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> allCustomers() {
		return customerRepository.getAll();
	}
}

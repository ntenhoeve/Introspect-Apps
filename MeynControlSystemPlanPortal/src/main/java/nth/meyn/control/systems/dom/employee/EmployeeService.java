package nth.meyn.control.systems.dom.employee;

import java.util.List;

public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> allEmployees() {
		return employeeRepository.getAll();
	}
}

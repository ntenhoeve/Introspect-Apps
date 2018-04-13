package nth.meyn.control.systems.dom.employee;

import nth.introspect.infrastructure.hibernate.HibernateRepository;
import nth.meyn.control.systems.dom.db.MeynControlDepartmentDbConfigutation;

public class EmployeeRepository extends HibernateRepository<Employee> {

	public EmployeeRepository(MeynControlDepartmentDbConfigutation configutation) {
		super(configutation);
	}

}

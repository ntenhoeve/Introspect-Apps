package nth.meyn.control.systems.dom.employee;

import nth.meyn.control.systems.dom.db.MeynControlDepartmentDbConfigutation;
import nth.reflect.infra.hibernate.HibernateRepository;

public class EmployeeRepository extends HibernateRepository<Employee> {

	public EmployeeRepository(MeynControlDepartmentDbConfigutation configutation) {
		super(configutation);
	}

}

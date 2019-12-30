package nth.meyn.control.systems.dom.employee;

import java.util.List;

import nth.meyn.control.systems.dom.db.MeynControlDepartmentDbConfigutation;
import nth.reflect.infra.hibernate.HibernateRepository;

public class EmployeeRepository extends HibernateRepository<Employee> {

	public EmployeeRepository(MeynControlDepartmentDbConfigutation configutation) {
		super(configutation);
	}

	@Override
	public List<Employee> getAll() {
		StringBuffer query = new StringBuffer("select e from ");
		query.append(Employee.class.getCanonicalName());
		query.append(" e where e.active=true and function!='Do Not Use' order by e.name");// where e.person=true
		List<Employee> results = getAll(query.toString());
		return results;
	}
}

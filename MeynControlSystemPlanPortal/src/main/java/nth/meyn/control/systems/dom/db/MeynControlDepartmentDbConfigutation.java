package nth.meyn.control.systems.dom.db;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;

import nth.meyn.control.systems.dom.activity.Activity;
import nth.meyn.control.systems.dom.customer.Customer;
import nth.meyn.control.systems.dom.customerorder.CustomerOrder;
import nth.meyn.control.systems.dom.employee.Employee;
import nth.meyn.control.systems.dom.factoryorder.FactoryOrder;
import nth.reflect.infra.hibernate.HibernateConfiguration;

public class MeynControlDepartmentDbConfigutation extends HibernateConfiguration {

	private boolean LOCAL_TEST_DB = true;

	public MeynControlDepartmentDbConfigutation() {
		super();
		// addDomainPackage("nth.meyn.control.systems.dom");
		addDomainClass(Customer.class);
		addDomainClass(Employee.class);
		addDomainClass(CustomerOrder.class);
		addDomainClass(FactoryOrder.class);
		addDomainClass(Activity.class);
	}

	@Override
	public String getConnectionUrl() {
		if (LOCAL_TEST_DB) {
			return "jdbc:mysql://localhost:3306/meyn_control_dep_test";
		} else {
			return "jdbc:mysql://controlsystems:3306/meyn_control_dep";
		}
	}

	@Override
	public String getUserName() {
		if (LOCAL_TEST_DB) {
			return "root";
		} else {
			return "meyndev";
		}
	}

	@Override
	public String getPassword() {
		if (LOCAL_TEST_DB) {
			return "MysqL66^";
		} else {
			return "11meyndev";
		}
	}

	@Override
	public Class<? extends Dialect> getDialect() {
		return MySQLDialect.class;
	}

}

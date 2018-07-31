package nth.meyn.vltissuelist.dom.customer;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;

import nth.reflect.infra.hibernate.HibernateConfiguration;

public class MeynControlDepartmentDbConfigutation extends
		HibernateConfiguration {

	private boolean LOCAL_TEST_DB = false; // true for production db

	public MeynControlDepartmentDbConfigutation() {
		super();
		addDomainClass(Customer.class);
	}

	@Override
	public String getConnectionUrl() {
		if (LOCAL_TEST_DB) {
			return "jdbc:mysql://localhost:3306/meyn_control_dep";
		} else {
			return "jdbc:mysql://ozdt025:3306/meyn_control_dep";
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

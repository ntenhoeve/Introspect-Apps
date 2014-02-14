package nth.innoforce.dataaccess;

import nth.introspect.dataaccess.hibernate.HibernateDataAccess;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnitConfiguration;

public class InnoforceDataAccess<T> extends HibernateDataAccess<T> {

	@Override
	public PersistenceUnitConfiguration getPersistenceUnitConfiguration() {
		// No need to set the parameters. This is done in the META-INF/persistence.xml file
		// Map<String, String> jpaProperties = new HashMap<String, String>();
		// jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		// jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		// jpaProperties.put("hibernate.connection.driver_class", SQLServerDriver.class.getCanonicalName());
		// jpaProperties.put("hibernate.connection.url", "jdbc:sqlserver://LTM082:1501;database=InnoForce2;user=sa;password=supernils;");
		return new PersistenceUnitConfiguration("InnoForce");
	}

}

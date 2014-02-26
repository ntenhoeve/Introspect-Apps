package nth.meyn.innovation.intake.database;

import nth.introspect.dataaccess.sql.SqlDataAccess;
import nth.introspect.dataaccess.sql.SqlDatabaseConfig;
import oracle.jdbc.driver.OracleDriver;

public class BaanDatabase extends SqlDataAccess {
	
	@Override
	public SqlDatabaseConfig getSqlDatabaseConfig() {
		return new SqlDatabaseConfig(OracleDriver.class, "jdbc:oracle:thin:@//orasrv01.meyn.nl:1521/baan01", "readit", "readit");
	}

}

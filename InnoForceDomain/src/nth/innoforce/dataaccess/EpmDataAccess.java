package nth.innoforce.dataaccess;

import nth.introspect.dataaccess.sql.SqlDataAccess;
import nth.introspect.dataaccess.sql.SqlDatabaseConfig;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class EpmDataAccess extends SqlDataAccess {

	@Override
	public SqlDatabaseConfig getSqlDatabaseConfig() {
		return new SqlDatabaseConfig(SQLServerDriver.class, "jdbc:sqlserver://EPM", "epmbaan", "baanepm"); 
	}

}

package ziemba.ian.test2.tomcat.helpers;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Helper class used to connection to a database that is a defined as a Tomcat server
 * resource.
 * @author icziemba
 */
public class DBResource {

	/**
	 * Connect to a specific Tomcat database resource.
	 * @param dbResource Name of the resource.
	 * @return Connection object for that database resource.
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static Connection connect(String dbResource) throws NamingException, SQLException {
		// Obtain our environment naming context
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");

		// Look up our data source
		DataSource ds = (DataSource) envCtx.lookup(dbResource);
		return ds.getConnection();
	}
}

package ziemba.ian.test2.tomcat.helpers;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBResource {
	
	private String dbResource;
	
	public DBResource(String dbResource) {
		this.dbResource = dbResource;
	}

	public Connection connect() throws NamingException, SQLException {
		// Obtain our environment naming context
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");

		// Look up our data source
		DataSource ds = (DataSource) envCtx.lookup(this.dbResource);
		return ds.getConnection();
	}
}

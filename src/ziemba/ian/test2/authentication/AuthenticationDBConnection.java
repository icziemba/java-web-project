package ziemba.ian.test2.authentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;
import ziemba.ian.test2.tomcat.helpers.DBResource;

/**
 * Class used to interface with an Authentication database. This class is responsible for all the
 * SQL commands for the database.
 * @author icziemba
 * @see AuthenticationSystem
 * @see AuthenticationUser
 */
public class AuthenticationDBConnection {
	
	private final static String DB_RESOURCE = "jdbc/Authentication";

	/**
	 * Query the Authentication database for a specific user and get that user's information.
	 * @param userName The user name to be queried.
	 * @return AuthenticationUser representing the queried user.
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static AuthenticationUser getAuthenticationUser(String userName) throws NamingException, SQLException {
		Connection connection = DBResource.connect(DB_RESOURCE);
		AuthenticationUser user = null;
		
		String command = "SELECT * FROM Users";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(command);
		
		while(resultSet.next() && user == null) {
			int authenticatedUserID = resultSet.getInt(1);
			String authenticatedUserName = resultSet.getString(2);
			String authenticatedUserPassword = resultSet.getString(3);
			
			if(userName.equals(authenticatedUserName)) {
				user = new AuthenticationUser(authenticatedUserID, authenticatedUserName, authenticatedUserPassword);
			}
		}
		
		connection.close();
		return user;
	}
	
	/**
	 * Insert a user's information into the Authentication database.
	 * @param user AuthenticationUer data structure containing the information to be entered.
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static void insertAutenticationUser(AuthenticationUser user) throws NamingException, SQLException {
		Connection connection = DBResource.connect(DB_RESOURCE);;
		
		String command = String.format("INSERT INTO Users (userName, password) VALUES (%s, %s)", user.getUserName(), user.getPassword());
		Statement statement = connection.createStatement();
		statement.executeUpdate(command);
		
		connection.close();
	}
}

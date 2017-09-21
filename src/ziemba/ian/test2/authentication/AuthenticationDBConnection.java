package ziemba.ian.test2.authentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import ziemba.ian.test2.tomcat.helpers.DBResource;

public class AuthenticationDBConnection {
	
	private final String dbResource = "jdbc/Authentication";
	private Connection connection = null;

	public AuthenticationUser getAuthenticationUser(String userName) throws NamingException, SQLException {
		this.connect();
		AuthenticationUser user = null;
		ResultSet resultSet = this.getUsers();
		while(resultSet.next() && user == null) {
			int authenticatedUserID = resultSet.getInt(1);
			String authenticatedUserName = resultSet.getString(2);
			String authenticatedUserPassword = resultSet.getString(3);
			
			if(userName.equals(authenticatedUserName)) {
				user = new AuthenticationUser(authenticatedUserID, authenticatedUserName, authenticatedUserPassword);
			}
		}
		this.close();
		return user;
	}
	
	public void insertAutenticationUser(AuthenticationUser user) throws NamingException, SQLException {
		this.connect();
		String command = String.format("INSERT INTO Users (userName, password) VALUES (%s, %s)", user.getUserName(), user.getPassword());
		Statement statement = connection.createStatement();
		statement.executeUpdate(command);
		this.close();
	}
	
	private ResultSet getUsers() throws SQLException, NamingException {
		String command = "SELECT * FROM Users";
		return this.queryDB(command);
	}
	
	private ResultSet queryDB(String command) throws SQLException, NamingException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(command);
		return resultSet;
	}
	
	private void connect() throws NamingException, SQLException {
		connection = new DBResource(dbResource).connect();
	}
	
	private void close() throws SQLException {
		connection.close();
		connection = null;
	}
}

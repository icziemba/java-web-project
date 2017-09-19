package ziemba.ian.test2.authentication;

import java.sql.SQLException;

import javax.naming.NamingException;

public class AuthenticationSystem {
	
	private AuthenticationDBConnection dbConnection;
	
	public AuthenticationSystem() throws SQLException, NamingException {
		dbConnection = new AuthenticationDBConnection();
	}
	
	public boolean authenticateUser(String userName, String password) throws SQLException, NamingException {
		
		// Get the user's information from the system
		AuthenticationUser user = dbConnection.getAuthenticationUser(userName);
		if(user != null) {
			if(user.getUserName().contentEquals(userName) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public void shutdown() {
		return;
	}

}

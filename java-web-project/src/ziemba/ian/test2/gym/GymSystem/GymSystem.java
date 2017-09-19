package ziemba.ian.test2.gym.GymSystem;

import java.sql.SQLException;

import javax.naming.NamingException;

import ziemba.ian.test2.authentication.AuthenticationSystem;

public class GymSystem {
	
	private AuthenticationSystem authenticationSystem;
	
	public GymSystem() throws SQLException, NamingException {
		authenticationSystem = new AuthenticationSystem();
	}
	
	public boolean login(String userName, String password) throws NullPointerException, NamingException {
		if(userName == null || password == null) {
			throw new NullPointerException("Null argument found for login credentials");
		}
		
		// Forward the login request to the authentication system
		try {
			return authenticationSystem.authenticateUser(userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

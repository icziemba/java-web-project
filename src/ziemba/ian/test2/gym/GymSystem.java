package ziemba.ian.test2.gym;

import java.sql.SQLException;
import javax.naming.NamingException;
import ziemba.ian.test2.authentication.AuthenticationSystem;

public class GymSystem {
	
	private AuthenticationSystem authenticationSystem = new AuthenticationSystem();
	
	public boolean login(String userName, String password) throws NullPointerException {
		if(userName == null || password == null) {
			throw new NullPointerException("Null argument found for login credentials");
		}
		
		return authenticationSystem.authenticateUser(userName, password);
	}
}

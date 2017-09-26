package ziemba.ian.test2.gym.authentication.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ziemba.ian.test2.gym.authentication.AuthenticationSystem;
import ziemba.ian.test2.gym.authentication.User;
import ziemba.ian.test2.gym.authentication.UserLevel;
import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;

public class AuthenticationSystemTest {

	private EntityManagerFactoryListener emfl;
	
	@Before
	public void setUp() throws Exception {
		emfl = new EntityManagerFactoryListener();
		emfl.contextInitialized(null);
	}
	
	@After
	public void tearDown() throws Exception {
		emfl.contextDestroyed(null);
		emfl = null;
	}
	
	@Test
	public void insertUser() {
		User user  = new User("icziemba", "password", UserLevel.ADMIN);
		AuthenticationSystem.registerUser(user);
	}
	
	@Test
	public void authenticateUser() {
		AuthenticationSystem.authenticateUser("icziemba", "password");
	}

}

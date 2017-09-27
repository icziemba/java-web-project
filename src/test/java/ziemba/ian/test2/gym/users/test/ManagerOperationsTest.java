package ziemba.ian.test2.gym.users.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ziemba.ian.test2.gym.authentication.AuthenticationSystem;
import ziemba.ian.test2.gym.authentication.User;
import ziemba.ian.test2.gym.authentication.UserLevel;
import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;
import ziemba.ian.test2.gym.users.Manager;
import ziemba.ian.test2.gym.users.ManagerOperations;

public class ManagerOperationsTest {

	private EntityManagerFactoryListener emfl;;
	
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
	public void createDeleteManager() {
		User user  = new User("icziemba", "password", UserLevel.ADMIN);
		AuthenticationSystem.registerUser(user);
		Manager manager = new Manager("Ian", "Ziemba", user);
		ManagerOperations.registerManager(manager);
		assertEquals("Manager not successfully entered into database", manager, ManagerOperations.getManager(user));
		ManagerOperations.removeManager(manager);
		assertEquals("Manager not successfully removed into database", null, ManagerOperations.getManager(user));
	}

}

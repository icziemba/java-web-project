package ziemba.ian.test2.authentication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The AuthenticationSystem class is intended to provide authentication services for another
 * system. The AuthenticationSystem works be using the AuthenticationDBConnection class to connect
 * to the underlying database.
 * 
 * @author Ian Ziemba
 * @see AuthenticationDBConnection
 * @see AuthenticationUser
 */
public class AuthenticationSystem {
	
	/**
	 * Authenticates user name and password with information in the Authentication database.
	 * @param userName User name to be checked with database.
	 * @param password Password to be checked with database.
	 * @return Status of whether the authentication was successfully or not.
	 * @throws RuntimeException Unable to authenticate the user name and password for some reason.
	 * @see AuthenticationDBConnection
	 */
	public static boolean authenticateUser(String userName, String password) {
		
		// Use the java persistence API (JPA) to create a object-relation mapping (ORM)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AuthenticationUser");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		AuthenticationUser user = em.find(AuthenticationUser.class, userName);
		
		// Verify user name and password matches entry in database
		boolean isAuthenticated = user.getUserName().contentEquals(userName) && user.getPassword().equals(password);

		// Shutdown the JPA connection
		em.close();
		emf.close();
		
		return isAuthenticated;
	}
	
	/**
	 * Register a new user with the Authentication database. The attributes of the
	 * AuthenticationUser class represent the User table which the information will be entered
	 * into.
	 * @param user Information to be entered into the database.
	 * @throws RuntimeException Unable to register the user from some reason.
	 */
	public static void registerUser(AuthenticationUser user) throws RuntimeException {
		
		// Use the java persistence API (JPA) to create a object-relation mapping (ORM)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AuthenticationUser");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// Commit the user to the Authentication database
		em.persist(user);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}

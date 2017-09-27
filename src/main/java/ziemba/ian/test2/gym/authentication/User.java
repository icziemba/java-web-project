package ziemba.ian.test2.gym.authentication;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;
import javax.persistence.Table;

import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;

/**
 * Class representation of the User table in the Authentication database. Each attribute of the
 * class should represent a row in the User table.
 * 
 * @author Ian Ziemba
 * @see AuthenticationSystem
 */

@Entity
@Table(name = "User")
@NamedNativeQuery(query = "SELECT * FROM User WHERE userName = ?", name = "find user by user name", resultClass = User.class)
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	private String userName;
	private String password;
	private String level;
	
	public User() {}

	public User(String userName, String password, UserLevel level) {
		this.userName = userName;
		this.password = password;
		this.level = level.getUserLevel();
	}
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long id) {
		this.idUser = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public UserLevel getLevelEnum() {
		return UserLevel.convertUserLevel(this.level);
	}
	public void setLevel(UserLevel level) {
		this.level = level.getUserLevel();
	}
	
	@Override
	public String toString() {
		return "User [id=" + idUser + ", userName=" + userName + ", password=" + password + ", level=" + level
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User user = (User) obj;
			return getIdUser() == user.getIdUser() && getUserName().equals(user.getUserName()) && getPassword().equals(user.getPassword())
					&& getLevel().equals(user.getLevel());
		}
		return false;
	}
	
	/**
	 * Given a user name and password, check if the pair match some entry in the database.
	 * @param userName User name to be checked with database.
	 * @param password Password to be checked with database.
	 * @return Status of whether the authentication was successfully or not.
	 */
	public static boolean authenticateUser(String userName, String password) {
		User user = getUser(userName);
		return user.getUserName().contentEquals(userName) && user.getPassword().equals(password);
	}
	
	/**
	 * Register a new user. User name of the User object must be unique.
	 * @param user Information to be entered into the database.
	 * @throws RuntimeException Unable to register the user from some reason.
	 */
	public static void registerUser(User user) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Remove a user from the the database.
	 * @param user User object to be removed.
	 */
	public static void removeUser(User user) {
		
		// JPA requires a find operation before a remove operation. So, use the primary key of the
		// user to issue the find.
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		User removedUser = em.find(User.class, user.getIdUser());
		em.getTransaction().begin();
		em.remove(removedUser);
		em.getTransaction().commit();
		em.close();
	}
	
	
	/**
	 * Check is a user name already exists.
	 * @param userName String to be checked.
	 * @return True if userName exists. Else false.
	 */
	public static boolean userNameExists(String userName) {
		return getUser(userName) != null;
	}
	
	/**
	 * Get a User object based on a specific user name. The row must exist in the database.
	 * @param userName User name to be checked.
	 * @return On success, valid user object. Else null.
	 */
	public static User getUser(String userName) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		
		// "find the user by username" is a NamedNativeQuery defined in User.java
		Query query = em.createNamedQuery("find user by user name");
		query.setParameter(1, userName);
		
		try {
			return (User) query.getSingleResult();
		} catch(javax.persistence.NoResultException e) {
			return null;
		}
	}
}

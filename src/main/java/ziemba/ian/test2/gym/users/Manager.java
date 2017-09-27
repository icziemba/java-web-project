package ziemba.ian.test2.gym.users;

import java.io.Serializable;
import javax.persistence.*;

import ziemba.ian.test2.gym.authentication.User;
import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;

/**
 * Entity implementation class for Entity: Manager
 *
 */

@Entity
@Table(name = "Manager")
@NamedNativeQuery(query = "SELECT * FROM Manager WHERE User_idUser = ?", name = "find manager by user", resultClass = Manager.class)
public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idManager;
	private String firstName;
	private String lastName;

	@OneToOne(cascade=CascadeType.REMOVE)
	private User user;

	public Manager() {
		super();
	}

	public Manager(String firstName, String lastName, User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.user = user;
	}

	public Long getIdManager() {
		return idManager;
	}

	public void setIdManager(Long idManager) {
		this.idManager = idManager;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Manager [idManager=" + idManager + ", firstName=" + firstName + ", lastName=" + lastName + ", user="
				+ user + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
	
	public static void registerManager(Manager manager) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		em.getTransaction().begin();
		em.persist(manager);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void removeManager(Manager manager) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		Manager removedUser = em.find(Manager.class, manager.getIdManager());
		em.getTransaction().begin();
		em.remove(removedUser);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Manager getManager(User user) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		Query query = em.createNamedQuery("find manager by user");
		query.setParameter(1, user.getIdUser());
		
		try {
			return (Manager) query.getSingleResult();
		} catch(javax.persistence.NoResultException e) {
			return null;
		}
	}
}

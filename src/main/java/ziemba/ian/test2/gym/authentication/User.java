package ziemba.ian.test2.gym.authentication;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

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
}

package ziemba.ian.test2.authentication;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class representation of the User table in the Authentication database. Each attribute of the
 * class should represent a row in the User table.
 * 
 * @author Ian Ziemba
 * @see AuthenticationSystem
 */

@Entity
@Table(name = "Users")
public class AuthenticationUser {
	
	@Id
	private String userName;
	private String password;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "AuthenticationUser [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
}

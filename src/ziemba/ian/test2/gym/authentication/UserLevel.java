package ziemba.ian.test2.gym.authentication;

public enum UserLevel {
	CUSTOMER("CUSTOMER"), TRAINER("TRAINER"), MANAGER("MANAGER"), ADMIN("ADMIN");
	
	private String userLevel;
	
	private UserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	
	public String getUserLevel() {
		return this.userLevel;
	}
	
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	
	public static UserLevel convertUserLevel(String userLevel) throws IllegalArgumentException {
		switch(userLevel) {
		case "CUSTOMER":
			return UserLevel.CUSTOMER;
		case "TRAINER":
			return UserLevel.TRAINER;
		case "MANAGER":
			return UserLevel.MANAGER;
		case "ADMIN":
			return UserLevel.ADMIN;
		default:
			throw new IllegalArgumentException("User level [" + userLevel + "] not supported");
		}
	}
}

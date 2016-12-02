package bean;

public class User {
	private int userId;
	private String userName;
	private int userRole; // 0代表普通用户，1代表管理员

	public User(int userId, String userName, int userRole) {
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

}

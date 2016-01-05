package Object;

public class User {
	private String userId;
	private String userAddress;
	public User(String userId, String userAddress)
	{
		this.userId = userId;
		this.userAddress = userAddress;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userid) {
		userId = userid;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String useraddress) {
		userAddress = useraddress;
	}
	
}

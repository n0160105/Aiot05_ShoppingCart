package shopping;

public class UserModel {
	
	protected String user;
	protected String password;
	protected String email;
	protected String token;

	public UserModel(String user, String password, String email, String token) {
		super();
		this.user = user;
		this.password = password;
		this.email = email;
		this.token = token;
	}
	
	public UserModel(String user) {
		super();
		this.user = user;
	}
	
	public UserModel(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	public UserModel(String user, String email, String password) {
		super();
		this.user = user;
		this.email = email;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

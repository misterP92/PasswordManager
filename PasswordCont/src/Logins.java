
public abstract class Logins {

	private String username;
	private String password;
	
	public Logins() {
		// TODO Auto-generated constructor stub
	}

	public Logins(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "UserName: " + this.username + "\nPassword: " + this.password;
	}
	
	public abstract String toStringSpec();
	
	public abstract int type();

}


public class AppLogins extends Logins {

	private String name;
	public AppLogins() {
		// TODO Auto-generated constructor stub
	}
	
	public AppLogins(String username, String password, String tname) {
		super(username, password);
		// TODO Auto-generated constructor stub
		this.name = tname;
		//this.icon = ticon;
	}

	@Override
	public String toStringSpec() {
		// TODO Auto-generated method stub
		String ret = this.name + ": " + super.toString() + "\n";
		return ret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	

}

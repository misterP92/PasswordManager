
public class WebLogins extends Logins {

	private String webName;
	private String web;
	public WebLogins() {
		// TODO Auto-generated constructor stub
	}
	public WebLogins(String username, String password, String webName) {
		super(username, password);
		this.webName = webName;
		this.web ="";
		// TODO Auto-generated constructor stub
	}
	public WebLogins(String username, String password, String webName, String web) {
		super(username, password);
		this.webName = webName;
		this.web = web;
		// TODO Auto-generated constructor stub
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}
	
	public String getUser() {
		return super.getUsername();
	}
	
	public String getPass() {
		return super.getPassword();
	}

	@Override
	public String toStringSpec() {
		// TODO Auto-generated method stub
		
		String ret = this.webName  +":  "+ super.toString() + " ;visit at: " + this.web +"\n";
		return ret;
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return 1;
	}

}

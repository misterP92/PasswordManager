import java.util.ArrayList;


public class PersonalLogins {

	private ArrayList<Logins> accounts;
	private SystemLogins log;
	private String name;
	private Boolean blocked;
	public int websize;
	public int appsize;
	
	public PersonalLogins(String tname, String username, String password, String question, String res) {
		super();
		this.accounts = new ArrayList<Logins>();
		this.log = new SystemLogins(username, password, res, question);
		this.name = tname;
		this.blocked = false;
	}
	public void addWebb(String username, String password, String webName, String web) {
		WebLogins item = new WebLogins(username, password, webName, web);
		this.accounts.add(item);
		this.websize++;
	}
	public void addApp(String username, String password, String tname) {
		AppLogins item = new AppLogins(username, password, tname);
		this.accounts.add(item);
		this.appsize++;
	}
	
	public Logins searchByName(String name) {
		Logins ret = null;
		Boolean ok = false;
		for(int i=0; i < this.accounts.size() && !ok;i++){
			if(this.accounts.get(i).type() == 1) {
				if(((WebLogins) this.accounts.get(i)).getWebName() == name) {
					ret = this.accounts.get(i);
					ok = true;
				}
			}
			else if(this.accounts.get(i).type() == 2) {
				if(((AppLogins) this.accounts.get(i)).getName() == name) {
					ret = this.accounts.get(i);
					ok = true;
				}
			}
		}
		return ret;
	}
	
	public String getInfo(int i) {
		return this.accounts.get(i).toStringSpec();
	}
	
	public String webbLogsSearch(int i) {
		String retString = "";
		try {
			if(this.accounts.get(i).type() == 1)
				retString = ((WebLogins) this.accounts.get(i)).getWebName();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return retString;
	}
	
	public String appLogsSearch(int i) {
		String retString = "";
		try {
			if(this.accounts.get(i).type() == 2)
				retString = ((AppLogins) this.accounts.get(i)).getName();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return retString; 
	}
	public int getType(int i) {
		return this.accounts.get(i).type();
	}
	
	public Boolean remove(String name) {
		Boolean removed = false;
		for(int i=0; i < accounts.size() && !removed;i++){
			if(this.accounts.get(i).type() == 1) {
				if( name.compareTo(((WebLogins) this.accounts.get(i)).getWebName()) == 0) {
					this.accounts.remove(i);
					removed = true;
					this.websize--;
				}
			}
			else if(this.accounts.get(i).type() == 2) {
				if(name.compareTo(((AppLogins) this.accounts.get(i)).getName()) == 0) {
					this.accounts.remove(i);
					removed = true;
					this.appsize--;
				}
			}
		}
		return removed;
	}
	public String getUsrName(int i) {
		return this.accounts.get(i).getUsername();
	}
	public String getUsrPass(int i) {
		return this.accounts.get(i).getPassword();
	}
	
	public String getWebName(int i) {
		return ((WebLogins) this.accounts.get(i)).getWebName();
	}
	public String getWeb(int i) {
		return ((WebLogins) this.accounts.get(i)).getWeb();
	}
	public String getAppName(int i) {
		return ((AppLogins) this.accounts.get(i)).getName();
	}
	
	public String getName() {
		return this.name;
	}
	public Boolean ifBolcked() {
		return this.blocked;
	}
	public String getQuestion() {
		return ((SystemLogins) this.log).getQuestion();
	}
	public String getSystemUsr() {
		return this.log.getUsername();
	}
	public String getStystemPass() {
		return this.log.getPassword();
	}
	public boolean reset(String resString) {
		boolean ok = false;
		if(  resString.compareTo(((SystemLogins) this.log).getRes()) == 0) {
			((SystemLogins) this.log).setTrys(0);
			this.blocked = false;
			ok = true;
		}
		return ok;
	}
	public String getAnsw() {
		return this.log.getRes();
	}
	public int getSize() {
		return this.accounts.size();
	}
	
	public int valid(String usr, String pass) {
		int ok = -2;
		int value = ((SystemLogins) this.log).validation(usr, pass);
		switch (value) {
		case 0:
			ok = 0;
			break;
		case 1:
			ok = 1;
			break;
		case -1:
			this.blocked = true;
			ok = -1;
			break;
		default:
			break;
		}
		return ok;
	}
	
}

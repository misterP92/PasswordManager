

public class SystemLogins extends Logins {
	private static final int OK = 0;
	private static final int NOTOK = 1;
	private static final int OUT = -1;
	private int trys;
	private String res;
	private String question;
	
	public SystemLogins() {
		super();
		// TODO Auto-generated constructor stub
		trys = 0;
	}

	public SystemLogins(String username, String password, String res, String tquestion) {
		super(username, password);
		this.res = res;
		this.question = tquestion;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toStringSpec() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int validation(String username, String password) {
		if(trys == 2) {
			return OUT;
		}
		else if ((username.compareTo(super.getUsername()) == 0) && (password.compareTo(super.getPassword()) == 0)) {
			return OK;
		} else {
			trys++;
			return NOTOK;
		}
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getTrys() {
		return trys;
	}

	public void setTrys(int trys) {
		this.trys = trys;
	}
	
}

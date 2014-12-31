package net.plannet.model;

public class User {
	private int uid;
	private String pw;
	private String email;
	private String name;
	private String uuid;
	
	public User() {
	}
	
	public User(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}
	
	public User(String name, String email, String pw) {
		this(email, pw);
		this.name = name;
	}
	
	public User(int uid, String name, String email, String pw) {
		this(name, email, pw);
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public boolean isValid() {
		if(email == null || email.equals(""))
			return false;
		if(pw == null || pw.equals(""))
			return false;
		
		return true;
	}
	
}

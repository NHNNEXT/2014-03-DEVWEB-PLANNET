package net.plannet.model;

public class User {
	private int uid;
	private String name;
	private String email;
	private String pw;
	
	public User(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}
	
	public User(String name, String email, String pw) {
		this(name, pw);
		this.name = name;
	}
	
	public User(int uid, String name, String email, String pw) {
		this(name, email, pw);
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPw() {
		return pw;
	}
	
}

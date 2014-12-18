package net.plannet.model;

public class Verify {
	private String name;
	private String email;
	private String pw;
	
	public Verify(String name, String email, String pw) {
		this.name = name;
		this.email = email;
		this.pw = pw;
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

package com.plannet.model;

public class User {
	private int uid;
	private String email;
	private String pw;
	private String name;

	public User(int uid, String email, String pw, String name) {
		this.uid = uid;
		this.email = email;
		this.pw = pw;
		this.name = name;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
package net.plannet.model;

public class Plan {
	private int pid;
	private String title;

	public Plan(String title) {
		this.title = title;
	}

	public Plan(int pid, String title) {
		this.pid = pid;
		this.title = title;
	}

	public int getPid() {
		return pid;
	}

	public String getTitle() {
		return title;
	}
}
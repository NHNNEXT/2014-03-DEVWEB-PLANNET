package com.plannet.model;

public class Plan {
	private int pid;
	private int uid;
	private int cid;
	private String title;
	private String summary;
	private boolean iscomplete; // in actual db, "complete"
	private boolean isprivate; // in actual db, "private"

	public Plan(int cid, String title, String summary) {
		this.cid = cid;
		this.title = title;
		this.summary = summary;
	}

	public Plan(int pid, int uid, int cid, String title, String summary, boolean isComplete, boolean isPrivate) {
		this.pid = pid;
		this.uid = uid;
		this.cid = cid;
		this.title = title;
		this.summary = summary;
		this.iscomplete = isComplete;
		this.isprivate = isPrivate;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isComplete() {
		return iscomplete;
	}

	public void setComplete(boolean isComplete) {
		this.iscomplete = isComplete;
	}

	public boolean isPrivate() {
		return isprivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isprivate = isPrivate;
	}
}

package com.plannet.model;

public class Subplan {
	private int subpid;
	private int pid;
	private String title;
	private String summary;
	private int percent;
	private boolean iscomplete; // in actual db, "complete"

	public Subplan(int pid, String title, String summary, int percent) {
		this.pid = pid;
		this.title = title;
		this.summary = summary;
		this.percent = percent;
	}

	public Subplan(int subpid, int pid, String title, String summary, int percent, boolean iscomplete) {
		this.subpid = subpid;
		this.pid = pid;
		this.title = title;
		this.summary = summary;
		this.percent = percent;
		this.iscomplete = iscomplete;
	}

	public int getSubpid() {
		return subpid;
	}

	public void setSubpid(int subpid) {
		this.subpid = subpid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public boolean isComplete() {
		return iscomplete;
	}

	public void setComplete(boolean iscomplete) {
		this.iscomplete = iscomplete;
	}
}

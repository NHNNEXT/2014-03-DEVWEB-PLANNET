package net.plannet.model;

public class Plan {
	private int pid;
	private int uid;
	private int cid;
	private String title;
	private String summary;
	private boolean iscomplete;
	private boolean isprivate;
	
	public Plan(int uid, String title) {
		this.uid = uid;
		this.title = title;
	}
	
	public Plan(int pid, int uid,int cid, String title, String summary, boolean isprivate, boolean iscomplete) {
		this.pid = pid;
		this.uid = uid;
		this.cid = cid;
		this.title = title;
		this.summary = summary;
		this.isprivate = isprivate;
		this.iscomplete = iscomplete;
	}

	public int getPid() {
		return pid;
	}

	public int getUid() {
		return uid;
	}

	public int getCid() {
		return cid;
	}

	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}

	public boolean isIscomplete() {
		return iscomplete;
	}

	public boolean isIsprivate() {
		return isprivate;
	}
	
}
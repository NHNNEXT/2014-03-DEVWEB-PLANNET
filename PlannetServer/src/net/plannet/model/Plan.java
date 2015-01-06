package net.plannet.model;

public class Plan {
	private int pid;
	private int uid;
	private int cid;
	private String title;
	private String summary;
	private boolean iscomplete;
	private boolean isprivate;
	
	public Plan() {
	}
	
	public Plan(int uid, String title) {
		this.uid = uid;
		this.title = title;
	}
	
	public Plan(int uid,int cid, String title, String summary, boolean isprivate, boolean iscomplete) {
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

	public boolean getIscomplete() {
		return iscomplete;
	}

	public boolean getIsprivate() {
		return isprivate;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setIscomplete(boolean iscomplete) {
		this.iscomplete = iscomplete;
	}

	public void setIsprivate(boolean isprivate) {
		this.isprivate = isprivate;
	}
}
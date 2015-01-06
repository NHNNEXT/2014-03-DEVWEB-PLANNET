package net.plannet.model;

public class SubPlan {

	private int subpid;
	private int pid;
	private String title;
	private String summary;
	private int percent;
	private boolean iscomplete;
	
	//Constructor
	
	public SubPlan() {
	}
	
	public SubPlan(int pid, String title, String summary) {
		this.pid = pid;
		this.title = title;
		this.summary = summary;
	}
	
	public SubPlan(int pid, String title, String summary, int percent, boolean iscomplete) {
		this(pid,title,summary);
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
	public boolean getIscomplete() {
		return iscomplete;
	}
	public void setIscomplete(boolean iscomplete) {
		this.iscomplete = iscomplete;
	}
	
	
}

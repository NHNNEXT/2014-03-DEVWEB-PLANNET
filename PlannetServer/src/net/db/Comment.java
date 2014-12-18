package net.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	private int cid;
	private int nid;
	private String writer;
	private String content;
	private String creation;

	public Comment(int nid, String writer, String content) {
		this.nid = nid;
		this.writer = writer;
		this.content = content;

		Date currentTime = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.creation = dateFormatter.format(currentTime);
	}

	public Comment(int cid, int nid, String writer, String content, String creation) {
		this(nid, writer, content);
		this.cid = cid;
		this.creation = creation;
	}

	public int getCid() {
		return cid;
	}

	public int getNid() {
		return nid;
	}

	public String getWriter() {
		return writer;
	}

	public String getContent() {
		return content;
	}

	public String getCreation() {
		return creation;
	}
}
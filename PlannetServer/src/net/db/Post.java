package net.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	private int nid;
	private String title;
	private String writer;
	private String content;
	private String creation;

	public Post(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;

		Date currentTime = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.creation = dateFormatter.format(currentTime);
	}

	public Post(int nid, String title, String writer, String content, String creation) {
		this(title, writer, content);
		this.nid = nid;
		this.creation = creation;
	}

	public int getNid() {
		return nid;
	}

	public String getTitle() {
		return title;
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
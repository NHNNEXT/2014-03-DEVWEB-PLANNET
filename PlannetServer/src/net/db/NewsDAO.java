package net.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// 서블릿은 생성자로 생성해서 사용해야 한다
public class NewsDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public NewsDAO() {
		conn = getConnection();
	}

	private Connection getConnection() {
		String url = "jdbc:mysql://192.168.56.101/NewsSite";// "jdbc:mysql://10.73.45.137/NewsSite";
		String id = "popi";// "root";
		String pw = "popi1004";// "plan1004";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			System.out.println("[DB Connection Failed] : " + e.getMessage());
			return null;
		}
	}

	public void insertPost(Post post) throws SQLException {
		String sql = "insert into news(title,writer,content,creation) values(?,?,?,?)";
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, post.getTitle());
		pstmt.setString(2, post.getWriter());
		pstmt.setString(3, post.getContent());
		pstmt.setString(4, post.getCreation());
		pstmt.executeUpdate();
		closeRS_PS_CONN();
	}

	public void insertComment(Comment comment) throws SQLException {
		String sql = "insert into comment(nid,writer,content,creation) values(?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getNid());
		pstmt.setString(2, comment.getWriter());
		pstmt.setString(3, comment.getContent());
		pstmt.setString(4, comment.getCreation());
		pstmt.executeUpdate();
		closeRS_PS_CONN();
	}

	public Post extractPost(int nid) throws SQLException {
		String sql = "select * from news where nid=" + nid;
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		Post result = null;
		while (rs.next()) {
			result = new Post(rs.getInt("nid"), rs.getString("title"), rs.getString("writer"), rs.getString("content"), rs.getString("creation"));
		}
		closeRS_PS_CONN();
		return result;
	}

	public ArrayList<Post> extractAllPosts() throws SQLException {
		String sql = "select * from news";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		ArrayList<Post> result = new ArrayList<Post>();
		while (rs.next()) {
			Post tempPost = new Post(rs.getInt("nid"), rs.getString("title"), rs.getString("writer"), rs.getString("content"), rs.getString("creation"));
			result.add(tempPost);
		}
		closeRS_PS_CONN();
		return result;
	}

	public ArrayList<Comment> extractAllComments(int nid) throws SQLException {
		String sql = "select * from comment where nid=" + nid;
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		ArrayList<Comment> result = new ArrayList<Comment>();
		while (rs.next()) {
			Comment tempComment = new Comment(rs.getInt("cid"), rs.getInt("nid"), rs.getString("writer"), rs.getString("content"), rs.getString("creation"));
			result.add(tempComment);
		}
		closeRS_PS_CONN();
		return result;
	}

	private void closeRS_PS_CONN() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}

package net.plannet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.plannet.model.Plan;

public class PlanDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet resultSet = null;

	public PlanDAO() {
		conn = getConnection();
	}

	private Connection getConnection() {
		String url = "jdbc:mysql://10.73.45.137:3306/plannet";
		String id = "root";
		String pw = "plan1004";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			System.out.println("[DB Connection Failed] : " + e.getMessage());
			return null;
		}
	}

	public void pushPlan(Plan plan) throws SQLException {
		String sql = "insert into Plan(title) values(?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, plan.getTitle());
		pstmt.executeUpdate();
		closeRS_PS_CONN();
	}
	
	public boolean signIn(String email, String pw) throws SQLException {
		String sql = "select * from user where email = ? and pw = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, pw);
		
		resultSet = pstmt.executeQuery();
		resultSet.last();
		int countRow = resultSet.getRow();
		
		
		closeRS_PS_CONN();
		System.out.println(countRow);

		if (countRow == 1)
			return true;
		return false;
	}

	public void signUp(String email, String pw, String name)
			throws SQLException {
		String sql = "insert into user(email, pw, name) values(?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, pw);
		pstmt.setString(3, name);
		pstmt.executeUpdate();
		closeRS_PS_CONN();
	}

	public boolean isExistEmail(String email) throws SQLException {
		String sql = "select email from user where email like ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);

		resultSet = pstmt.executeQuery();
		resultSet.last();
		int countRow = resultSet.getRow();
		closeRS_PS_CONN();
		if(countRow == 1)
			return true;
		return false;
	}
	
	public int getUID(String email) throws SQLException{
		String sql = "select uid from user where email = ?;";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		
		resultSet = pstmt.executeQuery();
		resultSet.last();
		System.out.println(resultSet.getInt("uid"));
		return resultSet.getInt("uid");
	}
	
	public void saveUUID(int uid, String uuid)
			throws SQLException {
		String sql = "insert into signUpManage values (?, ?, now());";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setString(2, uuid);
		pstmt.executeUpdate();
		closeRS_PS_CONN();
	}

	public ArrayList<Plan> pullAllPlans() throws SQLException {
		String sql = "select * from Plan";
		pstmt = conn.prepareStatement(sql);
		resultSet = pstmt.executeQuery();

		ArrayList<Plan> result = new ArrayList<Plan>();
		while (resultSet.next()) {
			Plan tempPlan = new Plan(resultSet.getInt("pid"), resultSet.getString("title"));
			result.add(tempPlan);
		}
		closeRS_PS_CONN();
		return result;
	}

	private void closeRS_PS_CONN() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
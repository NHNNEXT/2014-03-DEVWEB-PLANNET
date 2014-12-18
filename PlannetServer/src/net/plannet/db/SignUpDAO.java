package net.plannet.db;

import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;
import net.plannet.model.Plan;
import net.plannet.model.User;
import net.plannet.model.Verify;

public class SignUpDAO extends DAO{
	
	public ArrayList<User> selectEmail(User user) {
		String sql = "SELECT * FROM user WHERE email = ? ";
		ArrayList<Object> queryParams = new ArrayList<Object>();
		queryParams.add(user.getEmail());
		ArrayList<User> userRecord = selectQueryExecute(new QuerySet(sql, queryParams), User.class);
		closeResource();
		return userRecord;
	}
	
	public void addUser(User user) {
		String sql = "INSERT INTO user (name, email, pw) VALUES ( ?, ?, ? )";
		ArrayList<Object> queryParams = new ArrayList<Object>();
		queryParams.add(user.getName());
		queryParams.add(user.getEmail());
		queryParams.add(user.getPw());
		nonSelectQueryExecute(new QuerySet(sql, queryParams));
		closeResource();
	}
	
	public void addVerify(User user, String uuid) {
		String sql = "INSERT INTO verify (name, email, pw, uuid, expiredate) VALUES ( ?, ?, ?, ?, now())";
		ArrayList<Object> queryParams = new ArrayList<Object>();
		queryParams.add(user.getName());
		queryParams.add(user.getEmail());
		queryParams.add(user.getPw());
		queryParams.add(uuid);
		nonSelectQueryExecute(new QuerySet(sql, queryParams));
		closeResource();
	}
	
	public Verify selectVerify(String uuid) {
		String sql = "SELECT name, email, pw FROM verify WHERE uuid = ? ";
		ArrayList<Object> queryParams = new ArrayList<Object>();
		queryParams.add(uuid);
		ArrayList<Verify> verifyList = selectQueryExecute(new QuerySet(sql, queryParams), Verify.class);
		Verify verifyInfo = verifyList.get(0);
		closeResource();
		return verifyInfo;
	}
	
	public void deleteVerify(String uuid) {
		String sql = "DELETE FROM verify WHERE uuid = ?";
		ArrayList<Object> queryParams = new ArrayList<Object>();
		queryParams.add(uuid);
		nonSelectQueryExecute(new QuerySet(sql, queryParams));
		closeResource();
	}
}

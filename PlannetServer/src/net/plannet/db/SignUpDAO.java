package net.plannet.db;

import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;
import net.plannet.model.Plan;
import net.plannet.model.User;

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
		nonSelectQueryExecute(new QuerySet(sql, user.getName(), user.getEmail(), user.getPw(), user.getUuid()));
		closeResource();
	}
	
	public User selectVerify(String uuid) {
		String sql = "SELECT name, email, pw FROM verify WHERE uuid = ? ";
		ArrayList<User> userList = selectQueryExecute(new QuerySet(sql, uuid), User.class);
		User user = userList.get(0);
		closeResource();
		return user;
	}
	
	public void deleteVerify(String uuid) {
		String sql = "DELETE FROM verify WHERE uuid = ?";
		nonSelectQueryExecute(new QuerySet(sql, uuid));
		closeResource();
	}
}

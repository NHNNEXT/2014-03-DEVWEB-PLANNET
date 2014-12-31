package net.plannet.db;

import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;
import net.plannet.model.User;

public class SignUpDAO extends DAO{
	
	public ArrayList<User> selectEmail(User user) {
		String sql = "SELECT * FROM user WHERE email = ? ";
		ArrayList<User> userRecord = selectQueryExecute(new QuerySet(sql, user.getEmail()), User.class);
		closeResource();
		return userRecord;
	}
	
	public void addUser(User user) {
		String sql = "INSERT INTO user (name, email, pw) VALUES ( ?, ?, ? )";
		nonSelectQueryExecute(new QuerySet(sql, user.getName(), user.getEmail(), user.getPw()));
		closeResource();
	}
	
	public void addVerify(User user, String uuid) {
		String sql = "INSERT INTO verify (name, email, pw, uuid, expiredate) VALUES ( ?, ?, ?, ?, now())";
		nonSelectQueryExecute(new QuerySet(sql, user.getName(), user.getEmail(), user.getPw(), uuid));
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

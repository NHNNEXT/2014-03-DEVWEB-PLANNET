package net.plannet.db;

import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;
import net.plannet.model.User;

public class SignInDAO extends DAO{
	
	public User selectUser(User user) {
		String sql = "SELECT * FROM plan WHERE email = ? and password = ?";
		ArrayList<Object> queryParams = new ArrayList<Object>();
		queryParams.add(user.getEmail());
		queryParams.add(user.getPw());
		ArrayList<User> userRecord = selectQueryExecute(new QuerySet(sql, queryParams), User.class);
		closeResource();
		return userRecord.get(0);
	}
	
	public ArrayList<User> selectSignIn(User user) {
		String sql = "SELECT * FROM user WHERE email = ? and pw = ?";
		ArrayList<User> userList = selectQueryExecute(new QuerySet(sql, user.getEmail(), user.getPw()), User.class);
		closeResource();
		return userList;
	}
	
	public ArrayList<User> selectExistUUID(String uuid) {
		String sql = "SELECT * FROM user WHERE uuid = ?";
		ArrayList<User> userList = selectQueryExecute(new QuerySet(sql, uuid), User.class);
		closeResource();
		return userList;
	}
	
	public void updateUUID(int uid, String uuid) {
		String sql = "UPDATE user SET uuid = ? WHERE uid = ?";
		nonSelectQueryExecute(new QuerySet(sql, uuid, uid));
		closeResource();
	}
	
}

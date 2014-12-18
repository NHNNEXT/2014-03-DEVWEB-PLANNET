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
		String sql = "INSERT INTO user (name, email, password) VALUES ( ?, ?, ? )";
		ArrayList<Object> queryParams = new ArrayList<Object>();
		queryParams.add(user.getName());
		queryParams.add(user.getEmail());
		queryParams.add(user.getPw());
		nonSelectQueryExecute(new QuerySet(sql, queryParams));
	}
}

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
}

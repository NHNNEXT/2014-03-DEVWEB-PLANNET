package net.plannet.db;

import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;
import net.plannet.model.Plan;
import net.plannet.model.User;

public class PlanDAO extends DAO {

	public void pushPlan(Plan plan) {
		String sql = "insert into plan(uid, cid, title, summary) values(?, ?, ?, ?)";
		nonSelectQueryExecute(new QuerySet(sql, plan.getUid(), plan.getCid(), plan.getTitle(), plan.getSummary()));
		closeResource();
	}

	public ArrayList<Plan> pullAllPlans(User user) {
		String sql = "select * from plan where uid = ?";
		ArrayList<Plan> planList = selectQueryExecute(new QuerySet(sql, user.getUid()), Plan.class);
		closeResource();
		return planList;
	}
}

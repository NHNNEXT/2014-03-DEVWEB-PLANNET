package net.plannet.db;

import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;
import net.plannet.model.Plan;

public class PlanDAO extends DAO {

	public void pushPlan(Plan plan) {
		String sql = "insert into plan(uid, title) values(?, ?)";
		ArrayList<Object> queryParams = new ArrayList<Object>();
		queryParams.add(plan.getUid());
		queryParams.add(plan.getTitle());
		nonSelectQueryExecute(new QuerySet(sql, queryParams));
		closeResource();
	}

	public ArrayList<Plan> pullAllPlans() {
		String sql = "select * from plan";
		ArrayList<Plan> planList = selectQueryExecute(new QuerySet(sql, null), Plan.class);
		closeResource();
		return planList;
	}
}

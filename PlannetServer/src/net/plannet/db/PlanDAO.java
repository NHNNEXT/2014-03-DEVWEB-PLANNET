package net.plannet.db;

import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;
import net.plannet.model.Plan;

public class PlanDAO extends DAO {

	public int pushPlan(Plan plan) {
		String sql = "insert into plan(uid, cid, title, summary, isprivate, iscomplete) values(?, ?, ?, ?, ?, ?)";
		String getPidSql = "select pid from plan where uid = ? and title = ?";
		nonSelectQueryExecute(new QuerySet(sql, plan.getUid(), plan.getCid(), plan.getTitle(), plan.getSummary(), plan.getIsprivate(), plan.getIscomplete()));
		ArrayList<Plan> planList = selectQueryExecute(new QuerySet(getPidSql, plan.getUid(), plan.getTitle()), Plan.class);
		int pid = planList.get(0).getPid();
		closeResource();
		return pid;
	}

	public ArrayList<Plan> pullPlan(int uid) {
		String sql = "select * from plan where uid = ?";
		ArrayList<Plan> planList = selectQueryExecute(new QuerySet(sql, uid), Plan.class);
		closeResource();
		return planList;
	}
	
	public void modifyPlan(Plan plan) {
		String titleSql = "update plan set title = ? where pid = ?";
		nonSelectQueryExecute(new QuerySet(titleSql, plan.getTitle(), plan.getPid()));
		String summarySql = "update plan set summary = ? where pid = ?";
		nonSelectQueryExecute(new QuerySet(summarySql, plan.getSummary(), plan.getPid()));
	}
	
	public void deletePlan(Plan plan) {
		String deleteSubplanSql = "delete from subplan where pid = ?";
		nonSelectQueryExecute(new QuerySet(deleteSubplanSql, plan.getPid()));
		String deletePlanSql = "delete from plan where uid = ? and pid = ?";
		nonSelectQueryExecute(new QuerySet(deletePlanSql, plan.getUid(), plan.getPid()));
	}
}

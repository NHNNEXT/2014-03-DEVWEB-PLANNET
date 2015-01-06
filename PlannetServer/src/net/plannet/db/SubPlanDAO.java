package net.plannet.db;

import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;
import net.plannet.model.SubPlan;

public class SubPlanDAO extends DAO {

	public int pushSubPlan(SubPlan subPlan) {
		String sql = "insert into subplan(pid, title, summary, percent, iscomplete) values(?, ?, ?, ?, ?)";
		String getSubPidSQL = "select subpid from subplan where pid = ? and title = ? and summary = ?";
		nonSelectQueryExecute(new QuerySet(sql, subPlan.getPid(),
				subPlan.getTitle(), subPlan.getSummary(), subPlan.getPercent(),
				subPlan.getIscomplete()));
		ArrayList<SubPlan> subPlanList = selectQueryExecute(
				new QuerySet(getSubPidSQL, subPlan.getPid(),
						subPlan.getTitle(), subPlan.getSummary()),
				SubPlan.class);
		int subpid = subPlanList.get(0).getSubpid();
		closeResource();
		return subpid;
	}

	public ArrayList<SubPlan> pullSubPlan(int pid) {
		String sql = "select * from subplan where pid = ?";
		ArrayList<SubPlan> subPlanList = selectQueryExecute(new QuerySet(sql,
				pid), SubPlan.class);
		closeResource();
		return subPlanList;
	}
	
	public void deleteSubPlan(int pid) {
		
	}
	
}

package Test.dao;

import java.util.ArrayList;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;

import org.junit.Test;

public class PlanDaoTest {

	@Test
	public void TestPushPlan() {
		Plan a = new Plan(22, 5, "push plan test1", "success", false, false);
		int pid = new PlanDAO().pushPlan(a);
		System.out.println(pid);
	}
	
	@Test
	public void TestPullPlan() {
		ArrayList<Plan> result= new PlanDAO().pullPlan(22);
	}
	
	@Test
	public void TestDeletion() {
		Plan a = new Plan();
		a.setPid(14);
		a.setUid(22);
		new PlanDAO().deletePlan(a);
	}
	
	@Test
	public void TestModify() {
		Plan a = new Plan();
		a.setPid(18);
		a.setTitle("Modified title2!");
		a.setSummary("Modified Summary!@#!@@@@!");
		new PlanDAO().modifyPlan(a);
		
	}
	
}

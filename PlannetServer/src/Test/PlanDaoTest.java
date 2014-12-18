package Test;

import java.sql.SQLException;
import java.util.ArrayList;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;

import org.junit.Test;

public class PlanDaoTest {

	@Test
	public void test() throws SQLException {
		ArrayList<Plan> planlist = new PlanDAO().pullAllPlans();
		for(int i = 0; i< planlist.size(); i++)
			System.out.println(planlist.get(i).getTitle());
	}
	
	@Test
	public void test2() throws SQLException{
		Plan plan = new Plan(1, "텝스 시험 단기간에 준비하기");
		new PlanDAO().pushPlan(plan);
	}
	
	
}

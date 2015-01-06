package Test.dao;

import net.plannet.db.SubPlanDAO;
import net.plannet.model.SubPlan;

import org.junit.Test;

public class SubplanDaoTest {

	@Test
	public void pushSubplanTest() {
		int subpid = new SubPlanDAO().pushSubPlan(new SubPlan(14, "Wow!! Super holiday22", "Playing game!!"));
		System.out.println(subpid);
	}

}

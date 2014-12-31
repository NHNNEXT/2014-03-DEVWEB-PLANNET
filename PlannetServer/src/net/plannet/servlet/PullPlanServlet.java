package net.plannet.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.model.User;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;

@WebServlet("/PullPlan")
public class PullPlanServlet extends HttpServlet {
	private static final long serialVersionUID = -5404894584828069465L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			GsonUtil.getObjectFromRequest(req, User.class);
			ArrayList<Plan> planList = new PlanDAO().pullAllPlans();
			GsonUtil.writeObjectOnResponse(resp, planList);
		} catch (Exception e) {
			ErrorUtil.printError("PullPlanServlet Failed", e);
		}
	}
}
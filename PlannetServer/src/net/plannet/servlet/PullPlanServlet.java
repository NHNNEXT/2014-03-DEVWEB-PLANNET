package net.plannet.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.model.User;
import net.plannet.util.GsonUtil;

@WebServlet("/PullPlan")
public class PullPlanServlet extends HttpServlet {
	private static final long serialVersionUID = -5404894584828069465L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String jsonRequest;
		try {
			jsonRequest = GsonUtil.getJsonFromRequest(req);
			User user = GsonUtil.getGsonConverter().fromJson(jsonRequest, User.class);
			ArrayList<Plan> planList = new PlanDAO().pullAllPlans();
			String json = GsonUtil.getGsonConverter().toJson(planList);
			
			resp.getWriter().write(json);
		} catch (Exception e) {
			System.out.println("[PullPlanServlet Failed] : " + e.getMessage());
		}
	}
}
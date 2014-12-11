package net.plannet.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.others.Utilities;

@WebServlet("/PullPlan.do")
public class PullPlanServlet extends HttpServlet {
	private static final long serialVersionUID = -5404894584828069465L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ArrayList<Plan> planLists = new PlanDAO().pullAllPlans();
			String jsonResponse = Utilities.getGsonConverter().toJson(planLists);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jsonResponse);
		} catch (Exception e) {
			System.out.println("[PullPlanServlet Failed] : " + e.getMessage());
		}
	}
}
package net.plannet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.others.Utilities;

@WebServlet("/PushPlan.do")
public class PushPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 674145725707580679L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String jsonRequest = Utilities.getJsonFromPostRequest(req);
			Plan plan = Utilities.getGsonConverter().fromJson(jsonRequest, Plan.class);
			new PlanDAO().pushPlan(plan);
		} catch (Exception e) {
			System.out.println("[PushPlanServlet Failed] : " + e.getMessage());
		}
	}
}
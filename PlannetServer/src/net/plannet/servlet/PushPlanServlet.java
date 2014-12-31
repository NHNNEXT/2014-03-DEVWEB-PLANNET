package net.plannet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;

@WebServlet("/PushPlan")
public class PushPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 674145725707580679L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Plan plan = GsonUtil.getObjectFromRequest(req, Plan.class);
			new PlanDAO().pushPlan(plan);
		} catch (Exception e) {
			ErrorUtil.printError("PushPlanServlet Failed", e);
		}
	}
}
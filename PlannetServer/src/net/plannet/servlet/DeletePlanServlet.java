package net.plannet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.util.GsonUtil;

@WebServlet("/DeletePlan")
public class DeletePlanServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int uid = (int)req.getSession().getAttribute("uid");
		Plan plan = GsonUtil.getObjectFromRequest(req, Plan.class);
		plan.setUid(uid);
		new PlanDAO().deletePlan(plan);
	}
}

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

@WebServlet("/ModifyPlan")
public class ModifyPlanServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Plan plan = GsonUtil.getObjectFromRequest(req, Plan.class);
		new PlanDAO().modifyPlan(plan);
	}

}

package net.plannet.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;

@WebServlet("/PushPlan")
public class PushPlanServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(VerifyServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ArrayList<Plan> planRecord = new PlanDAO().pullAllPlans();
			GsonUtil.writeObjectOnResponse(resp, planRecord);
		} catch (Exception e) {
			ErrorUtil.printError("PushPlanServlet Failed", e);
		}
	}
}
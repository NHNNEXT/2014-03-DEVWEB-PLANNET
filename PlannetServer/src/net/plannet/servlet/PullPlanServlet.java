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
import net.plannet.model.User;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;
import net.plannet.util.RequestResult;

@WebServlet("/PullPlan")
public class PullPlanServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(VerifyServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//Server --> Client
			User user = GsonUtil.getObjectFromRequest(req, User.class);
			ArrayList<Plan> planList = new PlanDAO().pullAllPlans(user);
			GsonUtil.writeObjectOnResponse(resp, planList);
			logger.info("전체플랜 전송완료. User:{} 전송된 플랜:{}개",user.getEmail(), planList.size());
			
		} catch (Exception e) {
			ErrorUtil.printError("PullPlanServlet Failed", e);
		}
	}
}
package net.plannet.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.model.User;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;
import net.plannet.util.RequestResult;

@WebServlet("/PushPlan")
public class PushPlanServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(VerifyServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//Client --> Server
			Plan plan =  GsonUtil.getObjectFromRequest(req, Plan.class);
			
			//ArrayList<Plan> planRecord = new PlanDAO().pullAllPlans();
			//GsonUtil.writeObjectOnResponse(resp, planRecord);
			
			//insert 문 필요
			resp.setHeader("result", RequestResult.Success);
			HttpSession session = req.getSession();
			String uid = (String) session.getAttribute(RequestResult.SESSION_USER_ID);
			logger.info("{}의 플랜이 정상적으로 저장되었습니다.", uid);
			
		} catch (Exception e) {
			ErrorUtil.printError("PushPlanServlet Failed", e);
		}
	}
}
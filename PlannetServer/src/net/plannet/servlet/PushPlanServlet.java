package net.plannet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;
import net.plannet.util.RequestResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/PushPlan")
public class PushPlanServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(VerifyServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Plan plan =  GsonUtil.getObjectFromRequest(req, Plan.class);
			HttpSession session = req.getSession();
			int uid = (int)session.getAttribute(RequestResult.SESSION_USER_ID);
			plan.setUid(uid);
			int pid = new PlanDAO().pushPlan(plan);
			resp.setHeader("result", RequestResult.Success);
			resp.setHeader("pid", String.valueOf(pid));
			logger.info("{}의 플랜이 정상적으로 저장되었습니다.", uid);
		} catch (Exception e) {
			ErrorUtil.printError("PushPlanServlet Failed", e);
		}
	}
}
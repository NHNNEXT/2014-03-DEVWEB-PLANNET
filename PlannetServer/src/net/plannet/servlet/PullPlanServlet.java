package net.plannet.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;
import net.plannet.model.Plan;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;
import net.plannet.util.RequestResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;

@WebServlet("/PullPlan")
public class PullPlanServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(VerifyServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//Server --> Client
			int uid = (int)req.getSession().getAttribute("uid");
			ArrayList<Plan> planList = new PlanDAO().pullPlan(uid);
			GsonUtil.writeObjectOnResponse(resp, planList.toArray());
			logger.info("전체플랜 전송완료. 전송된 플랜:{}개", planList.size());
		} catch (Exception e) {
			ErrorUtil.printError("PullPlanServlet Failed", e);
		}
	}
}
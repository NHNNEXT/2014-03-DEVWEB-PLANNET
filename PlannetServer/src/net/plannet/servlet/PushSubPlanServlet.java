package net.plannet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.SubPlanDAO;
import net.plannet.model.SubPlan;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;
import net.plannet.util.RequestResult;

@WebServlet("/PushSubplan")
public class PushSubPlanServlet extends HttpServlet {
	//원철이형이 처리해줄 부분
	//private static final Logger logger = LoggerFactory.getLogger(VerifyServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			SubPlan subPlan =  GsonUtil.getObjectFromRequest(req, SubPlan.class);
			int subpid = new SubPlanDAO().pushSubPlan(subPlan);
			resp.setHeader("result", RequestResult.Success);
			resp.setHeader("subpid", String.valueOf(subpid));
			
			//HttpSession session = req.getSession();
			//String uid = (String) session.getAttribute(RequestResult.SESSION_USER_ID);
			//logger.info("{}의 플랜이 정상적으로 저장되었습니다.", uid);
			
		} catch (Exception e) {
			ErrorUtil.printError("PushPlanServlet Failed", e);
		}
	}
}
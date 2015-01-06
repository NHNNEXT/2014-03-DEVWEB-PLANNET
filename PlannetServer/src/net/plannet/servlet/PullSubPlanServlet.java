package net.plannet.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.SubPlanDAO;
import net.plannet.model.SubPlan;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;

@WebServlet("/PullSubPlan")
public class PullSubPlanServlet extends HttpServlet {
	//원철이형이 처리해줄 부분
	//private static final Logger logger = LoggerFactory.getLogger(VerifyServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int pid = Integer.valueOf(req.getHeader("pid"));
			ArrayList<SubPlan> subPlanList = new SubPlanDAO().pullSubPlan(pid);
			GsonUtil.writeObjectOnResponse(resp, subPlanList.toArray());
		} catch (Exception e) {
			ErrorUtil.printError("PullPlanServlet Failed", e);
		}
	}
}
package net.plannet.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.SignUpDAO;
import net.plannet.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/Verify")
public class VerifyServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(VerifyServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// uuid를 verify table의 uuid와 비교 [select]
		String uuid = req.getParameter("requestuuid");
		logger.info("인증 시스템에 진입하였습니다. UUID:{}", uuid);
		
		// 존재하지 않을 경우
		ArrayList<User> userList = new SignUpDAO().selectVerify(uuid);
		if (userList.size() != 1){
			logger.debug("이메일 인증을 실패하였습니다.");
			resp.sendRedirect("/verifyFail.jsp");
			return;
		}
		
		// 존재할 경우
		// user테이블에 verify의 사용자 정보를 [insert]
		new SignUpDAO().addUser(userList.get(0));
		// verify table의 해당 uuid를 [delete]
		new SignUpDAO().deleteVerify(uuid);
		resp.sendRedirect("/verifySuccess.jsp");
		logger.info("{}가 인증되어 정상적으로 SignUp이 완료되었습니다.", uuid);
	}
}

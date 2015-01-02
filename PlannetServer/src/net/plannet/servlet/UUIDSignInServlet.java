package net.plannet.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.plannet.db.SignInDAO;
import net.plannet.model.User;
import net.plannet.util.RequestResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/UUIDSignIn")
public class UUIDSignInServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UUIDSignInServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 클라로부터 uuid를 받는다.
		String uuid = req.getHeader("uuid");
		logger.info("UUIDSignIn에 진입하였습니다. UUID:{}", uuid);

		if (uuid != null) {
			// 디비에서 uuid를 찾는다.
			ArrayList<User> userList = new SignInDAO().selectExistUUID(uuid);
			if (userList.size() == 1) {
				// uuid가 있을 경우 uuid에 해당하는 id/pw를 꺼내 로그인한다.
				resp.setHeader("SigninResult", RequestResult.Success);
				HttpSession session = req.getSession();
				session.setAttribute(RequestResult.SESSION_USER_ID, userList.get(0).getUid());
				logger.info("UUIDSignIn이 성공하였습니다.");
				// uuid expireDate update한다. (옵션)
			} else {
				// uuid가 없을 경우
				resp.setHeader("SigninResult", RequestResult.UUIDNotFound);
				logger.info("UUIDSignIn이 실패하였습니다.");
				// 클라에 로그인 화면으로 갈 것을 요청한다.
			}
		}
	}
}

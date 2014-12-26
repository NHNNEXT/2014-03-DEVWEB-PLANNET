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

@WebServlet("/UUIDSignIn")
public class UUIDSignInServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 클라로부터 uuid를 받는다.
		String uuid = req.getHeader("uuid");
		System.out.println("signin uuid : " + uuid);

		if (uuid != null) {
			// 디비에서 uuid를 찾는다.
			ArrayList<User> userList = new SignInDAO().selectExistUUID(uuid);
			if (userList.size() == 1) {
				// uuid가 있을 경우
				// uuid에 해당하는 id/pw를 꺼내 로그인한다.
				resp.setHeader("SigninResult", RequestResult.Success);
				HttpSession session = req.getSession();
				session.setAttribute(RequestResult.SESSION_USER_ID, userList.get(0).getUid());
				// uuid expireDate update한다. (옵션)
			} else {
				// uuid가 없을 경우
				resp.setHeader("SigninResult", RequestResult.UUIDNotFound);
				// 클라에 로그인 화면으로 갈 것을 요청한다.
			}
		}
	}
}

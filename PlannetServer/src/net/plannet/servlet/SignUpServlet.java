package net.plannet.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.SignUpDAO;
import net.plannet.model.User;
import net.plannet.util.GsonUtil;
import net.plannet.util.Mail;
import net.plannet.util.RequestResult;

@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 클라로부터 email, password를 받아 DB에 저장.
			String name = req.getHeader("name");
			String email = req.getHeader("email");
			String password = req.getHeader("password");
			System.out.println(email);
			System.out.println(password);
			System.out.println(name);
			User user = new User(name, email, password);

			// 이메일 isExist?
			ArrayList<User> userRecord = new SignUpDAO().selectEmail(user);

			// 입력이 들어오지 않았을 경우 에러처리
			if (userRecord.size() == 0) {
				// 정상 && uuid 발급
				String uuid = createUUID();
				// verify table에 정보 저장
				new SignUpDAO().addVerify(user, uuid);
				resp.getWriter().print(RequestResult.Success);

				// 이메일 발송
				Mail.sendMail(email, uuid);

			} else {
				// 이미 존재하는 이메일일 경우 에러처리
				resp.getWriter().print(RequestResult.EmailOverlap);
			}
		} catch (Exception e) {
			System.out.println("[SignUpServlet Failed] : " + e.getMessage());
		}
	}

	public String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
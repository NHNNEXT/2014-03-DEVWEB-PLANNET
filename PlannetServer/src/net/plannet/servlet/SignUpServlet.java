package net.plannet.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
import net.plannet.util.UUIDControl;

import com.google.gson.reflect.TypeToken;

@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 클라로부터 email, password를 받아 DB에 저장.

			String json = GsonUtil.getJsonFromRequest(req);
			Type t = new TypeToken<ArrayList<User>>() {
			}.getType();
			ArrayList<User> temp = GsonUtil.getGsonConverter()
					.fromJson(json, t);
			User user = temp.get(0);

			// 이메일 isExist?
			ArrayList<User> userRecord = new SignUpDAO().selectEmail(user);

			// 입력이 들어오지 않았을 경우 에러처리
			if (userRecord.size() == 0) {
				// 정상 && uuid 발급
				String uuid = new UUIDControl().createUUID();
				// verify table에 정보 저장
				new SignUpDAO().addVerify(user, uuid);
				resp.getWriter().print(RequestResult.Success);

				// 이메일 발송
				Mail.sendMail(user.getEmail(), uuid);

			} else {
				// 이미 존재하는 이메일일 경우 에러처리
				resp.getWriter().print(RequestResult.EmailOverlap);

			}
		} catch (Exception e) {
			System.out.println("[SignUpServlet Failed] : " + e.getMessage());
		}
	}
}
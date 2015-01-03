package net.plannet.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.plannet.db.SignUpDAO;
import net.plannet.model.User;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;
import net.plannet.util.Mail;
import net.plannet.util.RequestResult;
import net.plannet.util.UUIDControl;

@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(SignUpServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			User user = GsonUtil.getObjectFromRequest(req, User.class);
			logger.info("SignUp을 시작합니다. user:{}", user.getEmail());
			
			ArrayList<User> userRecord = new SignUpDAO().selectEmailUserTable(user);
			ArrayList<User> verifyRecord = new SignUpDAO().selectEmailVerifyTable(user);
			logger.info("userRecord:{} verifyRecord:{}", userRecord.size(), verifyRecord.size());

			// 입력이 들어오지 않았을 경우 에러처리
			if (userRecord.size() == 0 && verifyRecord.size() == 0) {
				// 정상 && uuid 발급
				String uuid = new UUIDControl().createUUID();
				// verify table에 정보 저장
				new SignUpDAO().addVerify(user, uuid);
				resp.setHeader("result", RequestResult.Success);
				logger.info("정상적으로 SignUp대기상태에 진입하였습니다.");

				// 이메일 발송
				Mail.sendMail(user.getEmail(), uuid);

			} else {
				// 이미 존재하는 이메일일 경우 에러처리
				resp.setHeader("result", RequestResult.EmailOverlap);
				logger.info("SignUp에 실패하였습니다.");
			}
		} catch (Exception e) {
			ErrorUtil.printError("SignUpError", e);
		}
	}
}
package net.plannet.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.plannet.db.SignInDAO;
import net.plannet.db.SignUpDAO;
import net.plannet.model.User;
import net.plannet.util.ErrorUtil;
import net.plannet.util.GsonUtil;
import net.plannet.util.RequestResult;
import net.plannet.util.UUIDControl;

@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(SignInServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 클라로부터 id/pw를 받는다.
			User userFromReq = GsonUtil.getObjectFromRequest(req, User.class);
			logger.info("SingIn System에 진입하였습니다. email:{}", userFromReq.getEmail());

			if (userFromReq.isValid()) {
				ArrayList<User> userRecords = new SignInDAO().selectSignIn(userFromReq);
				ArrayList<User> verifyRecords = new SignInDAO().selectVerify(userFromReq);

				if (userRecords.size() == 1) {
					User userRecord = userRecords.get(0);
					String uuid = new UUIDControl().createUUID();
					new SignInDAO().updateUUID(userRecord.getUid(), uuid);

					resp.setHeader("uuid", uuid);
					resp.setHeader("result", RequestResult.Success);
					logger.info("SignIn이 성공적으로 완료되었습니다. 발급UUID:{}", uuid);
					HttpSession session = req.getSession();
					session.setAttribute(RequestResult.SESSION_USER_ID, userRecord.getUid());

				} else if (verifyRecords.size() == 1) {
					// 가가입은 되어 있으나 인증이 되지 않은 경우에 대한 처리
					logger.info("메일 인증이 완료되지 않았습니다.");
					resp.setHeader("result", RequestResult.EmailNotVerified);
				} else {
					// 실패했을 경우
					resp.setHeader("result", RequestResult.Fail);
					logger.info("SignIn에 실패하였습니다.");
					
					ArrayList<User> userRecordExistEmail = new SignInDAO().selectExistEmail(userFromReq);
					if(userRecordExistEmail.size() == 1){
						// id가 없을 경우에 대한 예외처리
						resp.setHeader("result", RequestResult.PasswordDismatch);
						logger.info("이메일은 존재하나 패스워드가 틀렸습니다.");
					}else{
						// pw가 없을 경우에 대한 예외처리
						resp.setHeader("result", RequestResult.EmailNotFound);
						logger.info("이메일이 존재하지 않습니다.");
					}
				}
			}
		} catch (Exception e) {
			ErrorUtil.printError("HTTP error", e);
		}
	}
}

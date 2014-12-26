package net.plannet.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.plannet.db.SignInDAO;
import net.plannet.model.User;
import net.plannet.util.GsonUtil;
import net.plannet.util.RequestResult;
import net.plannet.util.UUIDControl;

import com.google.gson.reflect.TypeToken;

@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {
	public static final String SESSION_USER_ID = "uuid";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			OutputStream out = resp.getOutputStream();
			OutputStreamWriter outWriter = new OutputStreamWriter(out);
			// 클라로부터 id/pw를 받는다.
			String json = GsonUtil.getJsonFromRequest(req);
			Type t = new TypeToken<ArrayList<User>>(){}.getType();
			ArrayList<User> temp = GsonUtil.getGsonConverter().fromJson(json,
					t);
			User userFromClient = temp.get(0);
			System.out.println("getemail : " + userFromClient.getEmail());
			System.out.println("pw : " + userFromClient.getPw());
			// id/pw가 null이니다.
			if (userFromClient.getEmail() != null && userFromClient.getPw() != null) {
				// 로그인을 한다.(select쿼리를 던진다.)
				ArrayList<User> userListFromDB = new SignInDAO().selectSignIn(userFromClient);
				
				System.out.println("userList.size : " + userListFromDB.size());
				if (userListFromDB.size() == 1) {
					User userFromDB = userListFromDB.get(0);
					// 성공했을 경우
					// 클라에게 uuid를 던져준다.
					String uuid = new UUIDControl().createUUID();
					System.out.println("uuid : " + uuid);
					
					//DB에 집어넣는다.
					new SignInDAO().updateUUID(userFromDB.getUid(), uuid);
					
					resp.setHeader("uuid", uuid);
					resp.setHeader("SigninResult", RequestResult.Success);
					HttpSession session = req.getSession();
					session.setAttribute(SESSION_USER_ID, uuid);
				} else {
					// 실패했을 경우
					resp.setHeader("SigninResult", RequestResult.Fail);
					// id가 없을 경우에 대한 예외처리 (옵션)
					// pw가 없을 경우에 대한 예외처리 (옵션)
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

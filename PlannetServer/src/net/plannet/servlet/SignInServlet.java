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
import net.plannet.util.UUIDControl;

import com.google.gson.reflect.TypeToken;

@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {
	public static final String SESSION_USER_ID = "uuid";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("signIn 진입");
		try {
			OutputStream out = resp.getOutputStream();
			OutputStreamWriter outWriter = new OutputStreamWriter(out);
			HttpSession session = req.getSession();
			// 클라로부터 id/pw를 받는다.
			String json = GsonUtil.getJSONJSON(req);
			
			System.out.println(json);
			
			Type t = new TypeToken<ArrayList<User>>(){}.getType();
			
			ArrayList<User> temp = GsonUtil.getGsonConverter().fromJson(json,
					t);
			User user = temp.get(0);
			System.out.println("getemail : " + user.getEmail());
			System.out.println("pw : " + user.getPw());
			// id/pw가 null이니다.
			if (user.getEmail() != null && user.getPw() != null) {
				// 로그인을 한다.(select쿼리를 던진다.)
				
				ArrayList<User> userList = new SignInDAO().selectSignIn(user);
				System.out.println("userList.size" + userList.size());
				if (userList.size() == 1) {
					// 성공했을 경우
					// 클라에게 uuid를 던져준다.
					String uuid = new UUIDControl().createUUID();
					outWriter.write(uuid);
					// 로그인 성공을 알려준다. (이건 보류)
					session.setAttribute(SESSION_USER_ID, uuid);
					outWriter.write("낼름낼름");
				} else {
					// 실패했을 경우
					outWriter.write("낼룸낼룸");
					// id가 없을 경우에 대한 예외처리
					// pw가 없을 경우에 대한 예외처리
				}
			} else {
				// 클라로부터 uuid를 받는다.
				String uuid = user.getUuid();
				// 디비에서 uuid를 찾는다.
				ArrayList<User> userList = new SignInDAO()
						.selectExistUUID(uuid);
				if (userList.size() == 1) {
					// uuid가 있을 경우
					// uuid에 해당하는 id/pw를 꺼내 로그인한다.
					session.setAttribute(SESSION_USER_ID, uuid);
					// uuid expireDate update한다. (나중에)
				} else {
					// uuid가 없을 경우
					// 클라에 로그인 화면으로 갈 것을 요청한다.
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

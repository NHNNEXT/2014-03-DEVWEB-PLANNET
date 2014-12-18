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
import net.plannet.util.GsonUtil;
import net.plannet.util.RequestResult;

@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String json = GsonUtil.getJsonFromRequest(req);
			User[] userArray = GsonUtil.getGsonConverter().fromJson(json, User[].class);
			User user = userArray[0];
			ArrayList<User> userRecord = new SignUpDAO().selectEmail(user);
			if (userRecord.size() == 0) {
				new SignUpDAO().addUser(user);
				resp.getWriter().print(RequestResult.Success);
			} else {
				resp.getWriter().print(RequestResult.EmailOverlap);
			}
		} catch (Exception e) {
			System.out.println("[SignUpServlet Failed] : " + e.getMessage());
		}
	}
}
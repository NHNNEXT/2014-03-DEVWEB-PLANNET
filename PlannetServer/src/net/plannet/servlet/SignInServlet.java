package net.plannet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.plannet.db.SignInDAO;
import net.plannet.model.User;
import net.plannet.util.RequestResult;

public class SignInServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SignInDAO signInDAO = new SignInDAO();
		String email = req.getHeader("email");
		String password = req.getHeader("password");
		User user = new User(email, password);
		user = signInDAO.selectUser(user);
		
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("uid", user.getUid());
			resp.setHeader("result", RequestResult.Success);
		}
		else {
			resp.setHeader("result", RequestResult.EmailNotFound);
		}
	}
}

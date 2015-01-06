package net.plannet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.plannet.db.SignInDAO;


@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info("진입하였습니다.");
		HttpSession session = req.getSession();
		int uid = (int)req.getSession().getAttribute("uid");
		new SignInDAO().deleteUUID(uid);
		session.invalidate();
	}
}

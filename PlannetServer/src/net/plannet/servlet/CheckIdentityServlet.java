package net.plannet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckIdentityServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Cookie[] cookies = req.getCookies();
		String jSessionId;
		for (Cookie cookie : cookies) {
			if (cookie.getName() == "JSESSIONID")
				jSessionId = cookie.getValue();

			HttpSession session = req.getSession();
			resp.getHeader("Set-Cookie");
			req.getSession().setAttribute("uid", 1);
			String service = req.getHeader("Service");

		}

	}
}

package net.helper;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.db.NewsDAO;

@WebServlet("/ViewNews.do")
public class ViewNewsServlet extends HttpServlet {
	private static final long serialVersionUID = -1958227288542862663L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
			req.setAttribute("AllPosts", new NewsDAO().extractAllPosts());
			RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			System.out.println("[ViewNewsServlet Failed] : " + e.getMessage());
		}
	}
}
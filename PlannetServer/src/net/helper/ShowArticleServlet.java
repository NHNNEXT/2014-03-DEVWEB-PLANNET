package net.helper;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.db.Comment;
import net.db.NewsDAO;
import net.db.Post;

@WebServlet("/ShowArticle.do")
public class ShowArticleServlet extends HttpServlet {
	private static final long serialVersionUID = -5596918181505614142L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int targetNid = Integer.parseInt(req.getParameter("nid"));
			Post post = new NewsDAO().extractPost(targetNid);
			ArrayList<Comment> comments = new NewsDAO().extractAllComments(targetNid);

			req.setCharacterEncoding("utf-8");
			req.setAttribute("article", post);
			req.setAttribute("comments", comments);
			RequestDispatcher dispatcher = req.getRequestDispatcher("article.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			System.out.println("[ShowArticleServlet Failed] : " + e.getMessage());
		}
	}
}
package net.helper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.db.NewsDAO;
import net.db.Post;
import net.others.Utilities;

@WebServlet("/PostNews.do")
public class PostNewsServlet extends HttpServlet {
	private static final long serialVersionUID = -2390230697380008447L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
			String title = req.getParameter("TITLE");
			String writer = req.getParameter("WRITER");
			String content = req.getParameter("CONTENT");
			Post post = new Post(title, writer, content);
			new NewsDAO().insertPost(post);
			Utilities.invokeBrowserPopup(resp, "뉴스가 성공적으로 업로드되었습니다!", "/");
		} catch (Exception e) {
			System.out.println("[PostNewsServlet Failed] : " + e.getMessage());
		}
	}
}
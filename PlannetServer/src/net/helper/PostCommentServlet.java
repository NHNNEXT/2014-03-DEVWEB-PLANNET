package net.helper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.db.Comment;
import net.db.NewsDAO;
import net.others.Utilities;

@WebServlet("/PostComment.do")
public class PostCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1190869923230073767L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
			int nid = Integer.parseInt(req.getParameter("nid"));
			String writer = req.getParameter("WRITER");
			String content = req.getParameter("CONTENT");
			Comment comment = new Comment(nid, writer, content);
			new NewsDAO().insertComment(comment);
			Utilities.invokeBrowserPopup(resp, "댓글이 성공적으로 업로드되었습니다!", "ShowArticle.do?nid="+nid);
		} catch (Exception e) {
			System.out.println("[PostNewsServlet Failed] : " + e.getMessage());
		}
	}
}
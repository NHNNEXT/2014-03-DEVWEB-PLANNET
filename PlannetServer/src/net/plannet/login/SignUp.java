package net.plannet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;

@WebServlet("/login/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 59931909252471368L;

	// Post로 받아서 내용이 들어오면
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");

		try {
			PlanDAO pd = new PlanDAO();
			// DB에 Email일치여부 확인
			if (new PlanDAO().isExistEmail(email)) {
				// 일치하는 Email이 있을 경우 Response로 사용자에게 전달
				resp.setCharacterEncoding("UTF-8");
			    resp.setContentType("text/xml");
			    PrintWriter out = resp.getWriter();
			    out.write("그 이메일 이미 있음. 딴 거 써.");
				// 다시 회원가입 페이지로
			}else{
				System.out.println("DB에 저장!");
				new PlanDAO().signUp(email, password, name);
				// 그렇지 않으면
				// DB에 저장
				
				
				// 로그인을 해줌.
				// 로그인 후에 메인 엑티비티로 전환
			}
		} catch (SQLException e) {
			System.out.println("[signUp Failed] : " + e.getMessage());
		}

	}


}

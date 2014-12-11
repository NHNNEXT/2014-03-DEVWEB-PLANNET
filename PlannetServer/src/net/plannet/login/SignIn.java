package net.plannet.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.PlanDAO;

@WebServlet("/login/signin")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = -3909608933641459945L;

	//post 로 요청이 들어오면
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//email, password 받아서
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println(email);
		System.out.println(password);
		
		//DB랑 일치 여부 확인
		PlanDAO pd = new PlanDAO();
		try {
			if(pd.signIn(email, password)){
				//HttpSession session = req.getSession();
				//session.setAttribute(arg0, arg1);
				System.out.println("로그인 성공!");
			}else{
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	//로그인 성공시
		//세션을 부여한다.
		//메인 화면으로 이동
	//로그인 실패시
		//없는 이메일일 경우
			//없는 이메일이라고 알려준다.
			//로그인 화면으로 이동.
		//비밀번호가 틀린경우
			//비밀번호가 틀렸다고 알려준다.
			//로그인 화면으로 이동.

}

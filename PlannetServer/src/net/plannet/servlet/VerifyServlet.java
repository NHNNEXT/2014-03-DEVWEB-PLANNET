package net.plannet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.plannet.db.SignUpDAO;
import net.plannet.model.User;

@WebServlet("/Verify")
public class VerifyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//uuid를 verify table의 uuid와 비교 [select]
		String uuid = req.getParameter("requestuuid");
		User verifiedInfo = new SignUpDAO().selectVerify(uuid);
		//user가 존재하지 않는 경우 에러처리
		
		//존재하지 않을 경우	
		if(verifiedInfo == null){return;}
		//존재할 경우
		//user테이블에 verify의 사용자 정보를 [insert]
		new SignUpDAO().addUser(verifiedInfo);
		//verify table의 해당 uuid를 [delete]
		new SignUpDAO().deleteVerify(uuid);
		
		//메일 인증하고 또 다시 그 버튼을 누르면 서버 500에러 
	}
}

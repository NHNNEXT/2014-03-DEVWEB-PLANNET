package net.plannet.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RespectBinding;

import net.plannet.db.PlanDAO;

@WebServlet("/login/mail")
public class Mail extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		String email = "kuku";	//일단 이메일은 받아온다고 가정
		String uuid = createUUID();
		sendMail("sturdyegg@gmail.com", uuid);
		sendMail("deplax@naver.com", uuid);
		try {
			int uid = new PlanDAO().getUID(email);
			//7c0555d9a0b448ab8da6c411f353be4d
			new PlanDAO().saveUUID(uid, uuid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String createUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public void SaveDatabaseUUID(String email, String UUID){
		
	}
	
	public void sendMail(String email, String uuid){
		String host = "smtp.gmail.com";
		String from = "plannetnoreply";
		String pass = "plannet1004";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = { email }; // added this line
		
		String text =""
				+ "<div style='background-color: #444444; height: 40px; width: 100%; padding: 2px 2px 2px 6px;' class='bar'>"
				+ "<h1 style='color: #FFFFFF;'>Plannet</h1></div>"
				+ "<div style='padding: 10px 10px 10px 10px;' class='contents'>"
				+ "<p style=''>Plannet에 서비스를 이용하기 위해서는 아직 가입 절차가 남았습니다. 서비스를 이용하기 위해서는 아래의 링크를 눌러 가입을 완료 하여야 합니다.</p>"
				+ "<p style='font-size: 12px; color: #445544;' class='warning'>*인증 유효시간은 메일 도착으로부터 1시간이며, 1시간이 초과했을 경우 다시 가입절차를 밟아 서비스를 이용할 수 있습니다.</p>"
				+ "<form style='' action='http://localhost:8080/login/verify' method='post' id='verify'>"
				+ "<input type='hidden' name='requestemail' value='" + email + "'>"
				+ "<input type='hidden' name='requestuuid' value='" + uuid + "'>"
				+ "<button style='' type='submit' form='verify' value='Verify'>"
				+ uuid
				+ "</button></form></div>"
				+ "<footer style='background-color: #888888; height: 15px; width: 100%; padding: 0px 2px 2px 2px; font-size: 10px; color: #ffffff;' >Welcome to plannet application</footer>";

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));

			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) { // changed from a while loop
				toAddress[i] = new InternetAddress(to[i]);
			}
			System.out.println(Message.RecipientType.TO);

			for (int i = 0; i < toAddress.length; i++) { // changed from a while
															// loop
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject("[Plannet]이메일을 인증하여 가입을 완료해 주세요!");
			message.setText(text, "utf-8", "html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
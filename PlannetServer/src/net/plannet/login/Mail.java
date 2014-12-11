package net.plannet.login;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/login/mail")
class Mail extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Mail.sendMail();
	}
	public static void sendMail(){
		Properties props = new Properties();
		props.put("mail.smtp.com", "smtp.gmail.com");
		Session session = Session.getDefaultInstance(props, null);
		String to = "sturdyegg@gmail.com";
		String from = "from@gmail.com";
		String subject = "Testing...";
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText("Working fine..!");
		} catch (Exception exc) {
		}
	}
}
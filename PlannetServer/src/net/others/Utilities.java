package net.others;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Utilities {

	public static void invokeBrowserPopup(HttpServletResponse resp, String message, String redirectLocation) throws IOException {
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/></head><body>");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('" + message + "')");
		out.println("window.location.href='" + redirectLocation + "';");
		out.println("</script>");
		out.println("</body></html>");
		out.close();
	}
}
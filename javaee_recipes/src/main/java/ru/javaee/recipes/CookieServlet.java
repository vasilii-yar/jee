package ru.javaee.recipes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Cookies", urlPatterns="/cookie")
public class CookieServlet extends HttpServlet {
	
	protected void reqProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cookie cookies = new Cookie("testK", "valueofcookie");
		cookies.setHttpOnly(true);
		cookies.setMaxAge(-30);
		res.addCookie(cookies);
		PrintWriter prw = res.getWriter();
		try {
			prw.println("<html>");
			prw.println("<head>");
			prw.println("</head>");
			prw.println("<body>");
			prw.print("<h1>Test cookies page");
			prw.println("</h1>");
			prw.println("</body>");
			prw.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			prw.close();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		reqProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		reqProcess(req, resp);
	}

}

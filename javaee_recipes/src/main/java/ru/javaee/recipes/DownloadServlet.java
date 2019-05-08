package ru.javaee.recipes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="DownloadServlet", urlPatterns="/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private String filePath;
	
	public DownloadServlet(String p) {
		filePath = p;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("filename");
		download(req, resp, fileName);
	}
	
	public void download(HttpServletRequest req, HttpServletResponse resp, String filename) throws IOException {
		final int BYTES = 1024;
		int length = 0;
		
		
		ServletOutputStream stream = resp.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		resp.setContentType((context.getMimeType(filename) == null) ? "text/plain" : context.getMimeType(filename));
		
		InputStream fileStream = context.getResourceAsStream(filename);
		Enumeration<URL> en = Thread.currentThread().getContextClassLoader().getResources(filename);
		System.out.println(en.nextElement());
		byte[] b = new byte[BYTES];
		if(fileStream == null) System.out.println("File not found");
		while((fileStream != null)  && ((length = fileStream.read(b)) != -1 )) {
			stream.write(b, 0, length);
		}
		stream.flush();
		stream.close();
	}
}

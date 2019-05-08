package ru.javaee.recipes;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class WebApplication {

	public static void main(String[] args) throws LifecycleException {
		Tomcat tmc = new Tomcat();
		tmc.setBaseDir("temp");
		tmc.setPort(8088);
		
		String contextPath = "";
		String docBase = new File("files").getAbsolutePath();
		System.out.println("resource docbase -- " + docBase);
		
		Context context = tmc.addContext(contextPath, docBase);
		
		String servletDownload = "DownloadServlet";
		String ServletCookie = "CookiesServlet";
		
		String servletUrlDownload = "/DownloadServlet";
		String servletUrlCookie = "/cookie";
 		
		DownloadServlet servletD = new DownloadServlet(docBase);
		CookieServlet servletC = new CookieServlet();
		
		tmc.addServlet(contextPath, servletDownload, servletD);
		tmc.addServlet(contextPath, ServletCookie, servletC);
		
		context.addServletMappingDecoded(servletUrlDownload, servletDownload);
		context.addServletMappingDecoded(servletUrlCookie, ServletCookie);
		
		tmc.start();
		tmc.getServer().await();
		

	}

}

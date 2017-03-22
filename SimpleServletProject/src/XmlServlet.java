import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XmlServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("userName");
		out.println("hello from the get method "+userName+"!!!");
		
		//response.getWriter().append("Xml Servlet called ");
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("userName");
		String fullName=request.getParameter("fullName");
		String prof=request.getParameter("prof");
		out.println("hello from the post method "+userName+" \nand fullname="+fullName+"!!!");
		
		//String location=request.getParameter("location");
		
		String[] location=request.getParameterValues("location");
		
		out.println("you are a "+prof+" and your location is ");
		
		for(int i=0; i<location.length; i++){
			out.println(location[i]+" ");
		}
		//response.getWriter().append("Xml Servlet called ");
		
		
		
	}
}

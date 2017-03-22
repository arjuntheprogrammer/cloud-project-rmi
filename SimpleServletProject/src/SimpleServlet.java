

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet(description = "a simple servlet", urlPatterns = { "/SimpleServletMap" }, 
initParams={@WebInitParam(name="defaultUser", value="albert austin")}
)
	
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		System.out.println("hello from get method.");
//		PrintWriter writer=response.getWriter();
//		writer.println("<h1>hello in html</h1>");
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		String userName=request.getParameter("name");
		HttpSession session=request.getSession();
		
		ServletContext context=request.getServletContext(); 
		if(userName!="" && userName!=null){
			session.setAttribute("savedUserName", userName);
			context.setAttribute("savedUserName", userName);
			
		}
		writer.println("Request parameter has username="+userName);
		writer.println("Session parameter has username="+(String)session.getAttribute("savedUserName"));
		writer.println("Context parameter has username="+(String)context.getAttribute("savedUserName"));
		writer.println("Init parameter has default username as="+this.getServletConfig().getInitParameter("defaultUser"));
		
	}

}

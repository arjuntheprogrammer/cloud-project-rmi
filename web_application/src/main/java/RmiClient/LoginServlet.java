package RmiClient;

import java.io.IOException;
import java.rmi.Naming;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import RmiClient.ServerInterface;
import login.LoginService;
import todo.TodoService;


@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LoginService service = new LoginService();
	private TodoService todoService = new TodoService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		
		//String name = "dir";
		RmiClient.ServerInterface serverInt;
		//String url="rmi://127.0.0.1:1099/dir";
		try {
			
			
			//System.setProperty("java.security.policy","Server.policy");
			//System.setProperty("java.rmi.server.hostname","127.0.0.1");
			 if (System.getSecurityManager() == null) {
		            System.setSecurityManager(new SecurityManager());
			 }
			 
			serverInt = (RmiClient.ServerInterface) Naming.lookup("dir");

			System.out.println("here");
			
			
			JSONObject obj = (JSONObject) serverInt.printFnames("/home/arjun/Documents/eclipse/workspace/");
			System.out.println("json object=" + obj);
			request.setAttribute("json", obj);
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
		}
		
		catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		boolean isValidUser = service.validateUser(name, password);

		if (isValidUser) {
			request.setAttribute("name", name);
			request.setAttribute("todos", todoService.retrieveTodos());
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials!!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

}
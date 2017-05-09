import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.Naming;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;



@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//private LoginService service = new LoginService();
	//private TodoService todoService = new TodoService();

	public void download(ServerInterface serverInt, String path_where_client_saves, String filePathOfServer) throws IOException{
		path_where_client_saves="/home/arjun/a.txt";
		filePathOfServer = "/media/arjun/46A6A679A6A668DF/Users/arjun gupta/Downloads/a.txt";
		
		File f= new File(path_where_client_saves);
		f.createNewFile();
		FileOutputStream out=new FileOutputStream(f, true);


		serverInt.setFileForDownload(filePathOfServer);
		byte[] mydata1 = null;

		while((mydata1=serverInt.fileDownload())!=null ){
			//c+=1024;
			//mydata=serverInt.fileDownload();
			out.write(mydata1, 0, mydata1.length);
		}


		serverInt.closeFileForDownload();;

		out.flush();
		out.close();


		System.out.println("file recieved");
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {



		//String name = "dir";
		ServerInterface serverInt;
		String url="rmi://127.0.0.1:1099/dir";
		try {


			//System.setProperty("java.security.policy","Server.policy");
			//System.setProperty("java.rmi.server.hostname","127.0.0.1");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			serverInt = (ServerInterface) Naming.lookup(url);

			//System.out.println("here");
			String path="/media/arjun/46A6A679A6A668DF/Users/arjun gupta/Downloads/";
			JSONObject obj = (JSONObject) serverInt.printFnames(path);
			
			System.out.println("json="+ obj);
			
			/*
			//////////////////////////////////////////////////////////
			
			download(ServerInterface serverInt, String path_where_client_saves, String filePathOfServer)
			
			String path_where_client_saves="/home/arjun/Downloads/manali.mp4";
			File f= new File(path_where_client_saves);
			f.createNewFile();
			FileOutputStream out=new FileOutputStream(f, true);


			String filePathOfServer = "/home/arjun/manali.mp4";
			serverInt.setFileForDownload(filePathOfServer);
			int c=0;
			byte[] mydata1 = null;

			while((mydata1=serverInt.fileDownload())!=null ){
				//c+=1024;
				//mydata=serverInt.fileDownload();
				out.write(mydata1, 0, mydata1.length);
			}


			serverInt.closeFileForDownload();;

			out.flush();
			out.close();


			System.out.println("file recieved");

			//////////////////////////////////////////////////////////////

			String path_where_server_saves="/home/arjun/holi.mp3";



			String filePathOfClient = "/home/arjun/Downloads/holi.mp3";
			File f1 = new File(filePathOfClient);
			FileInputStream in = new FileInputStream(f1);
			byte[] mydata2 = new byte[1024];

			serverInt.setFileForUpload(path_where_server_saves);
			int mylen = in.read(mydata2);
			while (mylen > 0) {
				//System.out.println("here");
				serverInt.recieveDataOnServer(mydata2);
				mylen = in.read(mydata2);

			}


			in.close();
			TimeUnit.SECONDS.sleep(1);
			serverInt.closeFileForUpload();


			//////////////////////////////////////////////////////////////

			*/
			request.setAttribute("json", obj);
			request.getRequestDispatcher("/splitter.jsp").forward(request, response);
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String filename=request.getParameter("path");
		
		System.out.println(filename);
		response.setHeader("Content-Type", getServletContext().getMimeType(filename));
		response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");

		OutputStream out = response.getOutputStream();
		String path="/media/arjun/46A6A679A6A668DF/Users/arjun gupta/Downloads/";
		FileInputStream in = new FileInputStream(path+filename);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0){
			out.write(buffer, 0, length);
		}
		out.close();
		in.close();
		out.flush();
		
		
	}

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {}
	//		String name = request.getParameter("name");
	//		String password = request.getParameter("password");
	//
	//		boolean isValidUser = service.validateUser(name, password);
	//
	//		if (isValidUser) {
	//			request.setAttribute("name", name);
	//			request.setAttribute("todos", todoService.retrieveTodos());
	//			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
	//		} else {
	//			request.setAttribute("errorMessage", "Invalid Credentials!!");
	//			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	//		}
	//	}

}
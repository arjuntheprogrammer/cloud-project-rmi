
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.Naming;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.SerializationUtils;

@WebServlet(urlPatterns = "/FileServlet")
public class FileServlet extends HttpServlet  {
	String path = "";
	String stringFromFile = "";
	private static final long serialVersionUID = 1L;
	
	public String rmiServerFileText(ServerInterface serverInt,  String filePathOfServer) throws IOException{
		//path_where_client_saves="/home/arjun/a.txt";
		//filePathOfServer = "/media/arjun/46A6A679A6A668DF/Users/arjun gupta/Downloads/a.txt";
		String text="";
		//File f= new File(path_where_client_saves);
		//f.createNewFile();
		//FileOutputStream out=new FileOutputStream(f, true);


		serverInt.setFileForDownload(filePathOfServer);
		byte[] mydata1 = null;

		while((mydata1=serverInt.fileDownload())!=null ){
			text=text+  (new String(mydata1, 0, mydata1.length, Charset.defaultCharset())).trim();
			//readFile2(mydata1,  Charset.defaultCharset());
			//c+=1024;
			//mydata=serverInt.fileDownload();
			//out.write(mydata1, 0, mydata1.length);
		}


		//serverInt.closeFileForDownload();
		System.out.println("text="+text);
		

		//out.flush();
		//out.close();


		System.out.println("file recieved");
		return text;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Content-Type", "text/plain");

		PrintWriter writer = response.getWriter();
		writer.write("hello");

		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		path = request.getParameter("path");
		System.out.println(path);
		// String stringFromFile = Files.lines(Paths.get("/home/arjun/"+path),
		// StandardCharsets.UTF_8).collect(Collectors.joining());
		// System.out.println(stringFromFile);

		ServerInterface serverInt;
		String url = "rmi://127.0.0.1:1099/dir";
		try {

			// System.setProperty("java.security.policy","Server.policy");
			// System.setProperty("java.rmi.server.hostname","127.0.0.1");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			serverInt = (ServerInterface) Naming.lookup(url);
			
			 //System.out.println("***here***"+serverInt.readFile("/home/arjun/" + path, Charset.defaultCharset()) );
			 
			 
			
			String Path="/media/arjun/46A6A679A6A668DF/Users/arjun gupta/";
			//stringFromFile = readFile(Path + path, Charset.defaultCharset());
			//stringFromFile = (String) SerializationUtils.deserialize(serverInt.readFile(Path + path, Charset.defaultCharset()));
			//byte[] data1 = SerializationUtils.deserialize(serverInt.readFile(Path + path, Charset.defaultCharset()));
			//byte[] data1 =serverInt.readFile(Path + path, Charset.defaultCharset());
			stringFromFile=rmiServerFileText(serverInt, Path + path);
			
			//System.out.println(data1);
			//stringFromFile=new String(data1, Charset.defaultCharset());
			
			//stringFromFile="Arjun";
			
			//sYourObject yourObject = (YourObject) SerializationUtils.deserialize(byte[] data)
			response.setHeader("Content-Type", "text/plain");
			PrintWriter writer = response.getWriter();
			writer.write(stringFromFile);

			writer.close();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	static String readFile(String path, Charset encoding) throws IOException {
//		// System.out.println("here");
//
//		byte[] encoded = Files.readAllBytes(Paths.get(path));
//		return new String(encoded, Charset.defaultCharset());
//	}
	static String readFile2(byte[] encoded, Charset encoding) throws IOException {
		// System.out.println("here");

		//byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, Charset.defaultCharset());
	}

	

}
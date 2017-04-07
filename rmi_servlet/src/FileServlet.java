
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;

@WebServlet(urlPatterns = "/FileServlet")
public class FileServlet extends HttpServlet {
	String path="";
	String stringFromFile="";
	private static final long serialVersionUID = 1L;

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
		
		stringFromFile = readFile("/home/arjun/" + path, Charset.defaultCharset());

		response.setHeader("Content-Type", "text/plain");
		PrintWriter writer = response.getWriter();
		writer.write(stringFromFile);

		writer.close();

	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
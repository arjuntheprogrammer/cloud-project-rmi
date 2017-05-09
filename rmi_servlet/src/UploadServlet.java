
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("c"));
		List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName()))
				.collect(Collectors.toList());
		// Retrieves <input type="file"
		// name="file" multiple="true">
		// Part filePart=request.getPart("file");
		System.out.println("filename= ");
		for (Part filePart : fileParts) {
			// String fileName =
			// Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			// // MSIE
			System.out.println("filename= ");
			String fileName = extractFileName(filePart);
			System.out.println("filename= "+fileName);
			
			InputStream fileContent = filePart.getInputStream();

			// write the inputStream to a FileOutputStream
			OutputStream outputStream = null;
			try {

				// write the inputStream to a FileOutputStream
				String Path = "/media/arjun/46A6A679A6A668DF/Users/arjun gupta/";

				outputStream = new FileOutputStream(new File(Path + request.getParameter("c") + fileName));

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = fileContent.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}

				System.out.println("Done!");

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileContent != null) {
					try {
						fileContent.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (outputStream != null) {
					try {
						// outputStream.flush();
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

			System.out.println(fileName);
		}

		response.sendRedirect("/rmi_servlet/login.do");

	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}

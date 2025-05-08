package content;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/XmlServlet")
public class XmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/xml; charset=UTF-8");
		String xml = "<Board>"
						+ "<title>제목</title>"
						+ "<writer>작성자</writer>"
						+ "<content>내용</content>"
				  + "</Board>";	
				
		PrintWriter writer = response.getWriter();
		writer.println(xml);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}





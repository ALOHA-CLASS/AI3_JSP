package content;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/TextServlet")
public class TextServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET 요청");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
		// 1. 문자 입력 스트림 가져오기
		BufferedReader reader = request.getReader();
		// 2. 텍스트 데이터 읽기
		StringBuilder sb = new StringBuilder();
		String line;
		while( (line = reader.readLine()) != null ) {
			sb.append(line);
		}
		String requestText = sb.toString();
		// 3. 요청 텍스트 확인
		System.out.println("텍스트 : " + requestText);
		
		// 응답
		String responseText = "응답 메시지";
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(responseText);
	}
	

}





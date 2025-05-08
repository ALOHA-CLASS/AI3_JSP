package method;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/method")
public class MethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GET
	// - /method?name=김조은&age=20
	// - 요청 파라미터 : name, age
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 요청
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			System.out.println("name : " + name);
			System.out.println("age : " + age);
			
			// 응답
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<h1>name : " + name + "</h1>");
			writer.println("<h1>age : " + age + "</h1>");
			writer.flush();								// 출력 바로 보내기
		} catch (Exception e) {
			System.err.println("파라미터가 올바르지 않습니다.");
		}
		
	}

	// POST
	// - /method
	// - body : username, password
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<h1>아이디 : " + username + "</h1>");
		writer.println("<h1>비밀번호 : " + password + "</h1>");
	}

	// PUT
	// - /method
	// - 컨텐츠 타입 : JSON
	// - 요청 본문(body)
	//   {"no" : "1", "title" : "제목", "content" : "내용"}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 (JSON -> Map)
		ObjectMapper mapper = new ObjectMapper();
		ServletInputStream sis = request.getInputStream();
		Map<String, Object> map 
			= mapper.readValue(sis, new TypeReference<Map<String, Object>>() {});
		String no = (String) map.get("no");
		String title = (String) map.get("title");
		String content = (String) map.get("content");
		System.out.println("no : " + no);
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<h1>no : " + no + "</h1>");
		writer.println("<h1>title : " + title + "</h1>");
		writer.println("<h1>content : " + content + "</h1>");
		
	}

	// DELETE
	// - /method?no=10
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noString = request.getParameter("no");
		int no = 0;
		try {
			no = Integer.parseInt(noString);
		} catch (Exception e) {
			System.err.println("유효하지 않은 번호입니다.");
		}
		
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(no + "번 글을 삭제하였습니다.");
	}

}






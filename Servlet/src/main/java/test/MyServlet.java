package test;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet")		// 요청 경로를 /MyServlet 매핑한다.
public class MyServlet extends HttpServlet {
	
	// GET방식 요청을 처리하는 메소드
    protected void doGet(
    		HttpServletRequest request		// 요청 객체
    	  , HttpServletResponse response	// 응답 객체
    	  )
            throws ServletException, IOException {
    	// HTML 컨텐츠 타입으로 "Hello, World!" 를 응답한다.
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("Hello, World!");
        response.getWriter().println("<br>");

        // 클라이언트의 요청 메서드를 확인
        String method = request.getMethod();
        response.getWriter().println("HTTP 요청 메서드: " + method);
        response.getWriter().println("<br>");

        // 요청 파라미터 가져오기
        String paramValue = request.getParameter("paramName");
        response.getWriter().println("파라미터 값: " + paramValue);
        response.getWriter().println("<br>");

        // 요청 URI와 URL 가져오기
        String requestURI = request.getRequestURI();
        response.getWriter().println("요청 URI: " + requestURI);
        response.getWriter().println("<br>");
        StringBuffer requestURL = request.getRequestURL();
        response.getWriter().println("요청 URL: " + requestURL.toString());
        response.getWriter().println("<br>");

        // 특정 HTTP 헤더 값 가져오기
        String userAgent = request.getHeader("User-Agent");
        response.getWriter().println("User-Agent 헤더: " + userAgent);
        response.getWriter().println("<br>");

        // 클라이언트 IP 주소 확인
        String remoteAddr = request.getRemoteAddr();
        response.getWriter().println("클라이언트 IP 주소: " + remoteAddr);
        response.getWriter().println("<br>");
    }
}



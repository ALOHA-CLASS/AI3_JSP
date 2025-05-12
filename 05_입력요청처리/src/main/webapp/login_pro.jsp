<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. id, passwd 파라미터 가져오기
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	// 2. 아이디, 비밀번호 일치 여부 확인
	boolean result = id.equals("aloha") && passwd.equals("123456");
	String root = request.getContextPath();
	// 3. 로그인 성공
	// - / 경로로 이동
	if( result ) {
		response.sendRedirect(root + "/");				
	}
	// 4. 로그인 실패
	// - /login.jsp?error=true
	else {
		response.sendRedirect(root + "/login.jsp?error=true");
	}
	


%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>세션 정보 삭제</title>
</head>
<body>
	<div><h4>----- 세션 삭제하기 전 -----</h4></div>
	<%
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		out.println("username : " + username);
		out.println("password : " + password);
		// 세션 속성 제거
		session.removeAttribute("username");
		session.removeAttribute("password");
	%>
	<div><h4>----- 세션 삭제한 후 -----</h4></div>
	<%
		username = (String) session.getAttribute("username");
		password = (String) session.getAttribute("password");
		out.println("username : " + username);
		out.println("password : " + password);
	%>
</body>
</html>








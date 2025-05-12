<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내장 객체 - request</title>
</head>
<body>
	<%
		// * 첫 번째 값만 가져온다.
		String hobby = request.getParameter("hobby");
		// * 하나의 파라미터에 여러 값을 가져오는 경우
		String[] hobbies = request.getParameterValues("hobby");
		for(int i = 0 ; i < hobbies.length ; i++) {
	%>	
		<h5><%= hobbies[i] %></h5>
	<%
		}
	%>
	<hr>
	<%= hobby %>
</body>
</html>









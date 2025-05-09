<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>표현문</title>
</head>
<body>
	<%-- 선언문 --%>
	<%! 
		int a = 100;
		String url = "/Servlet/img";
	%>
	<%-- 스크립틀릿 --%>
	<%
		int a = 10;
		int b = 20;
		int c = 30;
		int sum = a + b + c;
	%>
	<%-- 표현문 --%>
	<%= a + b + c %>
	<%= sum %>
	<h3>sum : <%= sum %></h3>
	<img src="<%= url %>" alt="이미지" width="200px" />
</body>
</html>





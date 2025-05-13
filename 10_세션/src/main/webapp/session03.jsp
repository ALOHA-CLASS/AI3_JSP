<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>세션의 모든 속성 값 확인</title>
</head>
<body>
	<h1>세션의 모든 속성 값 확인</h1>
	<%
		String name;
		String value;
		
		// session.getAttributeNames() : 모든 세션 속성 이름을 반환
		Enumeration<String> en = session.getAttributeNames();
		int i = 0;
		
		while( en.hasMoreElements() ) {
			i++;
			name = en.nextElement().toString();
			value = session.getAttribute(name).toString();
			out.println("세션 속성 이름 [" + i + "] : " + name + "<br>");
			out.println("세션 속성 값 [" + i + "] : " + value + "<br>");
			out.println("-------------------------------------------<br>");
		}
	%>
	<div>
		<a href="<%= request.getContextPath() %>/session04.jsp">session04.jsp</a>
	</div>
	<div>
		<a href="<%= request.getContextPath() %>/session05.jsp">session05.jsp</a>
	</div>
</body>
</html>









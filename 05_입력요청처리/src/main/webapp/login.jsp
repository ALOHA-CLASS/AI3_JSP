<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="login_pro.jsp" method="post">
		<div>
			<label for="id">아이디</label>
			<input type="text" name="id" placeholder="아이디" />
		</div>
		<div>
			<label for="passwd">비밀번호</label>
			<input type="password" name="passwd" placeholder="비밀번호" />
		</div>
		<button type="submit">로그인</button>
	</form>	
	<%
		String error = request.getParameter("error");
		if( error != null && Boolean.parseBoolean(error) ) {
	%>
		<p style="color: red;">아이디 또는 비밀번호가 일치하지 않습니다.</p>	
	<%
		}
	%>
</body>
</html>











<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내장 객체 - request</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="request03_pro.jsp" method="post">
		<div>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" />
		</div>
		<div>
			<label for="pw">비밀번호</label>
			<input type="password" name="pw" id="pw" />
		</div>
		<div>
			<label for="name">이름</label>
			<input type="text" name="name" id="name" />
		</div>
		<div>
			<label for="email">이메일</label>
			<input type="email" name="email" id="email" />
		</div>
		<div>
			<label for="birth">생년월일</label>
			<input type="date" name="birth" id="birth" />
		</div>
		<input type="submit" value="회원가입" />
	</form>
</body>
</html>







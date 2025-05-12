<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내장 객체 - response</title>
</head>
<body>
	<h1>게시글 등록</h1>
	<form action="response_pro.jsp" method="post">
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title" />			
		</div> 
		<div>
			<label for="writer">작성자</label>
			<input type="text" name="writer" id="writer" />			
		</div> 
		<div>
			<label for="content">내용</label>
			<textarea rows="4" cols="20" name="content" id="content"></textarea>			
		</div> 
		<input type="submit" value="등록하기" />
	</form>
</body>
</html>





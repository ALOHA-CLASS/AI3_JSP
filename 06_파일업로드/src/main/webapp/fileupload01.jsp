<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 업로드</title>
</head>
<body>
	<!-- 서블릿으로 요청 -->
<%-- 	<form action="<%= request.getContextPath() %>/upload" method="post" enctype="multipart/form-data"> --%>
	<!-- JSP로 요청 -->
	<form action="<%= request.getContextPath() %>/fileupload01_pro.jsp" method="post" enctype="multipart/form-data">
	  <div>
	  	이 름 : <input type="text" name="name" />
	  </div>
	  <div>
	  	제 목 : <input type="text" name="title" />
	  </div>
	  <div>
	  	파 일 : <input type="file" name="file1" /> <br>
	  	파 일 : <input type="file" name="file2" /> <br>
	  </div>
	  <div>
	  	<input type="submit" value="업로드" />
	  </div>		
	</form>
		 	
</body>
</html>
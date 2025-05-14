<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="DTO.Board"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.SortedMap"%>
<%@page import="org.apache.taglibs.standard.tag.common.sql.ResultImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL - sql</title>
</head>
<body>
	<h1>게시글 조회</h1>
	<!-- 데이터 소스 -->
	<sql:setDataSource var="dataSource" 
		url="jdbc:mysql://localhost:3306/aloha?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false"
		driver="com.mysql.cj.jdbc.Driver"
		user="aloha"
		password="123456"
	/>
	
	<%-- <sql:query var="결과객체" dataSource="${ dataSource }"></sql:query>  --%>
	<sql:query var="resultSet" dataSource="${dataSource}">
		SELECT * FROM board
		WHERE no = ?
		<sql:param value="${ param.no }"/>
	</sql:query>
	
	<!-- resultSet -> Board 객체로 매핑 (JSTL) -->
	<c:set var="row" value="${ resultSet.rows[0] }" />
	<jsp:useBean id="board" class="DTO.Board">
		<jsp:setProperty property="no" name="board" value="${ row.no }"/>
		<jsp:setProperty property="title" name="board" value="${ row.title }"/>
		<jsp:setProperty property="writer" name="board" value="${ row.writer }"/>
		<jsp:setProperty property="content" name="board" value="${ row.content }"/>
<%-- 		<jsp:setProperty property="createdAt" name="board" value="${ row.created_at }"/> --%>
<%-- 		<jsp:setProperty property="updatedAt" name="board" value="${ row.updated_at }"/> --%>
	</jsp:useBean>
	
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일자</th>
		</tr>
		<tr>
			<td><c:out value="${ board.no }"/></td>
			<td><c:out value="${ board.title }"/></td>
			<td><c:out value="${ board.writer }"/></td>
			<td>
				<fmt:formatDate value="${ board.createdAt }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
	</table>
</body>
</html>










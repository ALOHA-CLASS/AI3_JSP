<%@ include file="/layout/jstl.jsp" %>
<%@ include file="/layout/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>project💻 - ALOHA CLASS🌴</title>
	<jsp:include page="/layout/meta.jsp" />
	<jsp:include page="/layout/link.jsp" />
</head>
<body>
	<jsp:include page="/layout/header.jsp" />
	<%-- [Contents] ######################################################### --%>
		
	<main>
		<div class="container">
			<div class="px-4 pt-5 my-5 text-center border-bottom">
				<h1 class="display-4 fw-bold">게시글 목록</h1>
				<div class="col-lg-6 mx-auto">
					<p class="lead mb-4">JSP 게시판 프로젝트 입니다.</p>
				</div>
			</div>
			
			<!-- 게시판 테이블 -->
			<div class="d-grid gap-2 d-sm-flex justify-content-sm-center mb-5">
				<a href="${ root }/board/create.jsp" class="btn btn-success btn-lg px-4 me-sm-3">
					글쓰기
				</a>
			</div>
			<table class="table table-hover">
				<thead class="table-dark">
					<tr class="text-center">
						<th scope="col">#</th>
						<th class="text-left w-50" scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ boardList.isEmpty() }">
	            		<tr>
	            			<td align="center" colspan="5">조회된 데이터가 없습니다.</td>
	            		</tr>
	            	</c:if>
					<c:forEach var="board" items="${boardList}">
	                    <tr class="text-center">
	                        <td>${board.no}</td>
	                        <td class="text-start">
	                        	<a href="${ root }/board/read.jsp?id=${ board.id }">
		                        	${board.title}
	                        	</a>
	                        </td>
	                        <td>${board.user.name}</td>
	                        <td>
	                            <fmt:formatDate value="${board.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                        </td>
	                    </tr>
	                </c:forEach>
				</tbody>
			</table>
		</div>
	</main>
	
	
	<%-- [Contents] ######################################################### --%>
	<jsp:include page="/layout/footer.jsp" />
	<jsp:include page="/layout/script.jsp" />
</body>
</html>
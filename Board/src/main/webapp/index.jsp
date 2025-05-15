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
				<h1 class="display-4 fw-bold">게시판 프로젝트</h1>
				<div class="col-lg-6 mx-auto">
					<p class="lead mb-4">JSP 게시판 프로젝트 입니다.</p>
					<div class="d-grid gap-2 d-sm-flex justify-content-sm-center mb-5">
						<a href="<%= root %>/board/list.jsp" class="btn btn-primary btn-lg px-4 me-sm-3">
							게시글 목록
						</a>	
						<a href="<%= root %>/login.jsp" class="btn btn-outline-secondary btn-lg px-4">
							로그인
						</a>
					</div>
				</div>
				<div class="overflow-hidden">
					<div class="container px-5">
						<img src="https://i.imgur.com/pPxobUQ.jpg" class="img-fluid border rounded-3 shadow-lg mb-4" alt="게시판 프로젝트" width="700" height="500" loading="lazy">
					</div>
				</div>
			</div>
		</div>
	</main>
	
	<%-- [Contents] ######################################################### --%>
	<jsp:include page="/layout/footer.jsp" />
	<jsp:include page="/layout/script.jsp" />
</body>
</html>




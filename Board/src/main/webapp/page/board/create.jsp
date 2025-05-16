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
		<div class="container mt-5">
			<h2 class="mb-4">게시글 등록</h2>
			<form action="${ root }/board/create" method="post">
				<div class="mb-3">
					<label for="title" class="form-label">제목</label>
					<input type="text" class="form-control" id="title" name="title" required>
				</div>
	
				<div class="mb-3">
					<label for="content" class="form-label">내용</label>
					<textarea class="form-control" id="content" name="content" rows="6" required></textarea>
				</div>
	
				<div class="d-grid gap-2 mt-4">
					<button type="submit" class="btn btn-primary">등록</button>
					<a href="${ root }/board/list" class="btn btn-secondary">목록</a>
				</div>
			</form>
		</div>
	</main>
	
	
	<%-- [Contents] ######################################################### --%>
	<jsp:include page="/layout/footer.jsp" />
	<jsp:include page="/layout/script.jsp" />
</body>
</html>
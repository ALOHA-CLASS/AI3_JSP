<%@page import="org.apache.commons.fileupload2.jakarta.*"%>   <%-- Jakarta Servlet을 위한 Commons FileUpload 클래스 임포트 --%>
<%@page import="org.apache.commons.fileupload2.core.*"%>      <%-- Commons FileUpload의 코어 기능 클래스 임포트 --%>
<%@page import="java.nio.charset.StandardCharsets"%>          <%-- 문자 인코딩 상수 사용을 위한 임포트 --%>
<%@page import="java.io.FileOutputStream"%>                   <%-- 파일 저장을 위한 출력 스트림 클래스 --%>
<%@page import="java.io.InputStream"%>                        <%-- 파일 입력을 위한 입력 스트림 클래스 --%>
<%@page import="java.nio.charset.Charset"%>                   <%-- 문자셋 관련 클래스 --%>
<%@page import="java.io.File"%>                               <%-- 파일 및 디렉터리 제어용 클래스 --%>
<%@page import="java.util.List"%>                             <%-- 리스트 자료형 사용을 위한 임포트 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  <%-- JSP 페이지 설정 (언어, 콘텐츠 타입, 인코딩) --%>

<%
    // 파일 업로드가 저장될 경로 설정
	String uploadPath = "C:/upload/";
	File uploadDir = new File(uploadPath);  // 해당 경로에 대한 File 객체 생성

	if( !uploadDir.exists() ) {  // 해당 경로가 존재하지 않으면
		uploadDir.mkdirs();      // 디렉토리 생성
	}

	// 클라이언트 요청의 문자 인코딩 설정
	request.setCharacterEncoding("UTF-8");

	// 임시 파일 저장 경로 설정 (java의 기본 tmp 디렉토리)
	File repository = new File( System.getProperty("java.io.tmpdir") );

	// FileItemFactory 설정: 업로드된 항목을 생성하는 공장 객체
	FileItemFactory factory = DiskFileItemFactory.builder().setFile(repository).get();

    // Servlet 기반 파일 업로드 객체 생성
	JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

	// 현재 요청을 파싱하기 위한 Context 객체 생성
	JakartaServletRequestContext context = new JakartaServletRequestContext(request);
	
	try {
		// 요청에서 form field 및 파일을 파싱하여 리스트로 가져옴
		List<FileItem> items = upload.parseRequest(context);

		for( FileItem item : items ) {  // 리스트의 각 항목에 대해 처리
			// 텍스트 폼 필드인 경우
			if( item.isFormField() ) {
				// UTF-8로 인코딩된 값 출력
				out.println(item.getFieldName() + " : " + item.getString(StandardCharsets.UTF_8) + "<br>");
			}
			// 파일 업로드 필드인 경우
			else {
				String fieldName = item.getFieldName();     // 필드 이름
				String fileName = item.getName();           // 업로드된 파일 이름
				String uploadedName = System.currentTimeMillis() + "_" + item.getName();  // 저장 시 사용할 파일 이름

				// 파일명이 비어있지 않으면
				if( fileName != null && !fileName.isEmpty() ) {

					// 업로드 경로에 저장할 파일 객체 생성
					File uploadedFile = new File(uploadDir, fileName);

					try (
						InputStream is = item.getInputStream();                      // 업로드된 파일의 입력 스트림
						FileOutputStream fos = new FileOutputStream(uploadedFile);  // 파일로 출력하기 위한 스트림
					) {
						is.transferTo(fos);  // 파일 저장 처리

						// 업로드 결과 출력
						out.println("============================================ <br>");
						out.println("필드 이름 : " + fieldName + "<br>");
						out.println("원본 파일명 : " + fileName + "<br>");
						out.println("저장 파일명 : " + uploadedName + "<br>");
						out.println("타입 : " + item.getContentType() + "<br>");
						out.println("경로 : " + uploadedFile.getAbsolutePath() + "<br>");
						out.println("크기 : " + uploadedFile.length() + "<br>");
					} catch( Exception e ) {
						// 파일 저장 중 오류가 발생한 경우 출력
						e.printStackTrace();
					}
				}
			}
		}
	} catch( Exception e ) {
		// 전체 업로드 처리 중 오류 발생 시 출력
		out.println("파일 업로드 중 에러 발생");
		e.printStackTrace();
	}
%>

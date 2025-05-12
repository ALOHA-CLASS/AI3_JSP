<%@page import="org.apache.commons.fileupload2.core.FileItem"%>
<%@page import="org.apache.commons.fileupload2.core.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload2.core.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload2.core.DiskFileItem"%>
<%@page import="org.apache.commons.fileupload2.jakarta.JakartaServletRequestContext"%>
<%@page import="org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.nio.charset.Charset"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파일 업로드 폴더 지정
	String uploadPath = "C:/upload/";
	File uploadDir = new File(uploadPath);
	if( !uploadDir.exists() ) {
		uploadDir.mkdirs();
	}
	// 인코딩 설정
	request.setCharacterEncoding("UTF-8");
	
	// 파일 파라미터 확인
	File repository = new File( System.getProperty("java.io.tmpdir") );
	FileItemFactory factory = DiskFileItemFactory.builder().setFile(repository).get();
    JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
	JakartaServletRequestContext context = new JakartaServletRequestContext(request);
	
	try {
		List<FileItem> items = upload.parseRequest(context);
		for( FileItem item : items ) {
			// 텍스트 데이터
			if( item.isFormField() ) {
				out.println(item.getFieldName() + " : " + item.getString(StandardCharsets.UTF_8) + "<br>");
			}
			// 파일 데이터
			else {
				String fileName = System.currentTimeMillis() + "_" + item.getName();
				if( fileName != null && !fileName.isEmpty() ) {
					File uploadedFile = new File(uploadDir, fileName);
					try(
						InputStream is = item.getInputStream();
						FileOutputStream fos = new FileOutputStream(uploadedFile);
					) {
						is.transferTo(fos); // 파일 저장
					} catch( Exception e ) {
						e.printStackTrace();
					}
				}
			}
		}
	} catch( Exception e ) {
		out.println("파일 업로드 중 에러 발생");
		e.printStackTrace();
	}
	
		
%>







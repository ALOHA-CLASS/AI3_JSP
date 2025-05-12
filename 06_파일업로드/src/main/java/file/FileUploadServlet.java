package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/upload")
@MultipartConfig   // multipart 요청 설정
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("파일 업로드 테스트");

		String uploadPath = "C:/upload/";
		File uploadDir = new File(uploadPath);
		if( !uploadDir.exists() ) {
			uploadDir.mkdirs();
		}
		
		// 텍스트 파라미터 (name, title)
		out.println("<h3>텍스트 파라미터</h3>");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		out.println("<h5> name : " + name + "</h5>");
		out.println("<h5> title : " + title + "</h5>");

//		for( Part part : request.getParts() ) {
//			String name = part.getName();
//			String value = request.getParameter(part.getName());
//			out.println("<h5>" + name + " : " + value + "</h5>");
//		}
		
		// 파일 파라미터 (file1, file2)
		// - 단일 파일 파라미터 가져오기
		Part file1 = request.getPart("file1");
		Part file2 = request.getPart("file2");
		// - 모든 파일 파라미터 가져오기
		Collection<Part> parts = request.getParts();
		out.println("파라미터 개수 : " + parts.size() + "<br>");
		int count = 0;
		for( Part part : parts ) {
			String fieldName = part.getName();
			String fileName = part.getSubmittedFileName();
			
			// 파일 파라미터 확인
			if( fileName != null && !fileName.isEmpty() ) {
				count++;
				out.println("fieldName : " + fieldName  + "<br>");
				out.println("fileName : " + fileName  + "<br>");
				
				// 업로드 파일명 지정 (밀리초시간_파일명)
				String uploadedName = System.currentTimeMillis() + "_" + fileName;
				File file = new File(uploadPath, uploadedName);
				// 파일 데이터 입력 + 파일 데이터 출력 (파일 저장)
				try(
					InputStream is = part.getInputStream();
					FileOutputStream fos = new FileOutputStream(file)
				) {
					is.transferTo(fos); // 파일 저장
				}
				catch (Exception e) {
					out.print("파일 저장 시, 에러 발생");
				}
				// 파일 정보
				out.println("============================================ <br>");
				out.println("필드 이름 : " + fieldName + "<br>");
				out.println("원본 파일명 : " + fileName + "<br>");
				out.println("저장 파일명 : " + uploadedName + "<br>");
				out.println("타입 : " + part.getContentType() + "<br>");
				out.println("경로 : " + file.getAbsolutePath() + "<br>");
				out.println("크기 : " + file.length() + "<br>");
			}
		} // foreach 끝
		out.print("파일 개수 : " + count + "<br>");
	}

}

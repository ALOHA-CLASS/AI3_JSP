package file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
// 파일 업로드 처리를 위한 어노테이션 설정
@MultipartConfig(
	fileSizeThreshold = 1 * 1024 * 1024,	// 파일 초과 시 임시 메모리
	maxFileSize = 10 * 1024 * 1024,			// 10MB 파일당 최대 크기
	maxRequestSize = 50 * 1024 * 1024		// 50MB 요청당 최대 크기
)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 업로드 경로
		String uploadPath = "C:\\fileupload";
		// 업로드 폴더가 없으면 폴더 생성
		File uploadDir = new File(uploadPath);
		if( !uploadDir.exists() ) {
			uploadDir.mkdir();
		}
		
		// 제목 
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		System.out.println("title : " + title);
		
		// 클라이언트에서 전송한 파일 가져오기
		Part filePart = request.getPart("file");	// name="file"
		// 파일 이름
		String fileName = Paths.get(filePart.getSubmittedFileName())
								.getFileName().toString();
		// 업로드할 파일 경로 : C:/fileupload/UID_강아지.jpg
		String filePath = uploadPath 
						+ File.separator 
						+ UUID.randomUUID().toString()
						+ "_"
						+ fileName;
		// 파일 저장
		try {
			InputStream is = filePart.getInputStream();
			// Files.copy( 입력, 출력 )
			long result = Files.copy(is, Paths.get(filePath));
			System.out.println("파일 복사 결과 : " + result);
		} catch (Exception e) {
			System.out.println("파일 복사 중 에러 발생");
			e.printStackTrace();
		}
		PrintWriter writer = response.getWriter();
		writer.println("파일 업로드 <br>");
		writer.println("파일 : " + filePath);
				
	}

}







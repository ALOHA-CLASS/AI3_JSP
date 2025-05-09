package content;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import dto.MapWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import util.XmlMapper;

@WebServlet("/XmlServlet")
public class XmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * curl -X GET http://localhost:8080/Servlet/XmlServlet ^
	 * -H "Content-Type: application/xml" ^
	 * -d "<Users><name>ALOHA</name><age>20</age></Users>"
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/xml; charset=UTF-8");
		// 요청
		// xml --> Map
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while( (line = reader.readLine() ) != null ) {
			stringBuilder.append(line);
		}
		String xml = stringBuilder.toString();
		Map<String, Object> map = null;
		try {
			map = XmlMapper.toMap(xml);
		} catch (Exception e) {
			System.err.println("XML->Map 변환 중 에러 발생");
		}
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		
		// 응답
//		String xml = "<Board>"
//						+ "<title>제목</title>"
//						+ "<writer>작성자</writer>"
//						+ "<content>내용</content>"
//				  + "</Board>";
		
		// map --> xml
		JAXBContext context = null;
		try {
			// Map 을 MapWrapper 로 포함
			MapWrapper wrapper = new MapWrapper(map);
			// JAXBContext 객체 생성
			context = JAXBContext.newInstance(MapWrapper.class);
			// 마샬링 	: 객체 --> XML
			// 언마샬링	: XML --> 객체
			// 객체를 XML 로 변환해주는 객체, 마샬러 생성
			Marshaller marshaller = context.createMarshaller();
			// XML 태그를 포맷팅하는 옵션 지정 (들여쓰기 등)
			marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT , true );
			StringWriter stringWriter = new StringWriter();
			// 객체 --> xml 로 변환
			// marshaller.marshal( 변환할클래스, StringWriter객체 );
			marshaller.marshal(wrapper, stringWriter);
			xml = stringWriter.toString();
		} catch (JAXBException e) {
			System.err.println("map --> xml 변환 시 에러");
		}
		System.out.println("map --> xml : ");
		System.out.println(xml);
		PrintWriter writer = response.getWriter();
		writer.println(xml);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}





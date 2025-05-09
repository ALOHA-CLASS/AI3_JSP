package util;

import java.io.StringReader;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class XmlMapper {
	
	// xml -> Map
	public static Map<String, Object> toMap(String xml) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringReader reader = new StringReader(xml);
		Document doc = builder.parse(new InputSource(reader)); // 문자열XML-->문서객체로 변환
		
		Element root = doc.getDocumentElement();	// 루트 요소
		NodeList nodeList = root.getChildNodes();	// 자식 요소 리스트
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);			// i번째 요소 : <name>, <age>
			if( node instanceof Element ) {
				// name : ALOHA, age : 20
				map.put(node.getNodeName(), node.getTextContent());
			}
		}
		return map;
	}

}









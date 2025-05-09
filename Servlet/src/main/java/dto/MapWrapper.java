package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "map")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapWrapper {
	
	@XmlElement(name = "entry")
	private List<Entry> entries = new ArrayList<>();
	
	// 내부 Entry 클래스
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Entry {
		private String key;
		private Object value;
		
		public Entry() {
			
		}

		public Entry(String key, Object value) {
			this.key = key;
			this.value = value;
		}
	}

	public MapWrapper() {
		
	}
	
	public MapWrapper(Map<String, Object> map) {
		for (Map.Entry<String, Object> e : map.entrySet()) {
			System.out.println("key : " + e.getKey());
			System.out.println("value : " + e.getValue());
			entries.add(new Entry(e.getKey(), e.getValue()));
		}
	}

	public MapWrapper(List<Entry> entries) {
		this.entries = entries;
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Entry e : entries) {
			map.put(e.key, e.value);
		}
		return map;
	}
	

}







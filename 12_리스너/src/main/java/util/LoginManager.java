package util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * 로그인 계정 관리 클래스
 * - 싱글톤 패턴
 * - 인증된 사용자 정보
 */
public class LoginManager {
	private static LoginManager loginManager = null;
	private static Hashtable<String, String> loginUsers = new Hashtable<>();
	
	private LoginManager() {
		
	}
	
	public static synchronized LoginManager getInstance() {
		if( loginManager == null ) {
			loginManager = new LoginManager();
		}
		return loginManager;
	}
	
	// 사용중
	public boolean isUsing(String userId) {
		return loginUsers.containsKey(userId);
	}
	
	// 사용자 추가
	public void addUser(String userId, String sessionId) {
		loginUsers.put(userId, sessionId);
	}
	
	// 사용자 제거
	public void removeUser(String sessionId) {
		List<String> userIdList = new ArrayList<String>( loginUsers.keySet() );
		for (int i = 0; i < userIdList.size(); i++) {
			String id = userIdList.get(i);
			String sid = loginUsers.get(id);
			if( sessionId.equals(sid) ) {
				loginUsers.remove(id);
			}
		}
	}
	
	// SESSIONID 로 userId 확인
	public String getUserId(String sessionId) {
		List<String> userIdList = new ArrayList<String>( loginUsers.keySet() );
		for (String key : userIdList) {
			if(loginUsers.get(key).equals(sessionId)) {
				return key;
			}
		}
		return null;
	}
	
	public void allUsers() {
		List<String> userIdList = new ArrayList<String>( loginUsers.keySet() );
		System.out.println("접속자 수 : " + userIdList.size());
		for (String key : userIdList) {
			String sessionId = loginUsers.get(key);
			System.out.println(key + " : " + sessionId);
		}
		
	}

}






